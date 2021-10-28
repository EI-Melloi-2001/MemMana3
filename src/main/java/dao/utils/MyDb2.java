package dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyDb2 { // 类定义
    private static Connection conn = null; //
    private static PreparedStatement pst = null; // 参数式查询必须

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");  //IDEA里不可省略
            //useUnicode=true&characterEncoding=utf-8实现中西文兼容
            String url = "jdbc:mysql://localhost:3306/dbtest?useUnicode=true&characterEncoding=utf-8";
            String username = "root";
            String password = "123456";
            conn = DriverManager.getConnection(url, username, password); // 创建链接对象
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet query(String sql, Object... args) throws Exception { // 选择查询方法
        // SQL命令中含有通配符，使用可变参数使得可以传离散或数组两种方式的参数
        pst = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst.executeQuery();
    }

    public static boolean cud(String sql, Object... args) throws Exception { // 增加_c，修改_u，删除_d
        pst = conn.prepareStatement(sql);
        for (int i = 0; i < args.length; i++) {
            pst.setObject(i + 1, args[i]);
        }
        return pst.executeUpdate() >= 1 ? true : false; // 返回操作查询是否成功？
    }

    public static void closeConn() throws Exception { // 关闭数据库访问方法
        if (pst != null && !pst.isClosed())
            pst.close();
        if (conn != null && !conn.isClosed())
            conn.close();
    }
}
