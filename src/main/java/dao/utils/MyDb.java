/*
 * 改进的访问MySQL数据库的通用Java类MyDb
 * 在使用本类的程序里，不需要创建MyDb的实例对象，避免了对服务器的重复连接
 * 定义了一个获取MyDb实例对象的静态方法getMyDb()
 * 两个主要的支持参数式查询的方法：选择查询方法query()和操作查询方法cud()
 * 
 * 与之前的工具类相比，增加了分页查询方法 ：
 * queryAllWithPage(String sql, Integer page, int pageSize,HttpServletRequest request, Object... args)，返回值类似为Pager
 */

package dao.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import dao.bean.Pager;

public class MyDb { // 类定义
	private Connection conn = null; //
	private PreparedStatement pst = null; // 参数式查询必须
	private static MyDb mydb = null;  
	
	private MyDb() throws Exception {    //私有的构造方法，外部不能创建实例
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/dbtest?characterEncoding=utf-8";
		String username = "root";
		String password = "123456";
		conn = DriverManager.getConnection(url, username, password); // 创建链接对象
	}

	public static MyDb getMyDb() throws Exception{   
		if(mydb==null)   //单例
			mydb=new MyDb();  //单例模式避免了对数据库服务器重复的连接
		return  mydb;
	}

	public ResultSet query(String sql, Object... args) throws Exception { // 选择查询方法
		// SQL命令中含有通配符，使用可变参数使得可以传离散或数组两种方式的参数
		pst = conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pst.setObject(i + 1, args[i]);
		}
		return pst.executeQuery();
	}
	public boolean cud(String sql, Object... args) throws Exception { // 增加_c，修改_u，删除_d
		pst = conn.prepareStatement(sql);
		for (int i = 0; i < args.length; i++) {
			pst.setObject(i + 1, args[i]);
		}
		return pst.executeUpdate() >= 1 ? true : false; // 返回操作查询是否成功？
	}

	public void closeConn() throws Exception { // 关闭数据库访问方法
		if (pst != null && !pst.isClosed())
			pst.close();
		if (conn != null && !conn.isClosed())
			conn.close();
	}
	
    // JDBC+Servlet环境下的分页方法，SQL命令里包含的占位参数个数任意
    public Pager queryAllWithPage(String sql, Integer page, int pageSize,
            HttpServletRequest request, Object... args) {
        ResultSet rs = null;
        Integer recordsNum = 0; 
        try {
            ResultSet totalRs = query(sql, args);
            totalRs.last();
            recordsNum = totalRs.getRow(); // 得到记录集rs的总记录数
            Object[] newArgs = new Object[args.length + 2];
            for (int i = 0; i < args.length; i++) {
                newArgs[i]= args[i];
            }
            newArgs[args.length] = (page-1)*pageSize; // 增加2个参数
            newArgs[args.length + 1] = pageSize;
            rs = query(sql + " limit ?,?", newArgs);  //查询指定的page页
            //返回分页实体对象
            return new Pager(rs, recordsNum, pageSize, page, request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
