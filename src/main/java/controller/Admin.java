/*
 * 本程序处理后台管理员登录
 * 登录成功时，请求将重定向（而不是转发！）至后台主页admin/adminIndex.jsp
 */
package controller;
import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.utils.MyDb;

@WebServlet("/Admin")
public class Admin extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)	throws ServletException, IOException {
		try {
			String pw = req.getParameter("pwd");  //获取表单提交参数值
			
			if (pw.trim().length() > 0) { // 必须输入表单元素username的值才进行数据库查询
				//库memmana3表admin里只有一条记录。其中，字段password值为字符串admin的md5值
				String sql = "select * from admin where  password=md5(?)"; //参数式查询
				ResultSet rs = MyDb.getMyDb().query(sql, pw); // 预编译，带缓冲的选择查询
				if (rs.next()) { //输入密码正确时
					System.out.println("登录成功");
					//会话跟踪
					req.getSession().setAttribute("admin", rs.getString(1)); 
					//重定向至主控制器
					resp.sendRedirect("views/admin/adminIndex.jsp");
				} else {  // 输入密码错误时
					req.setAttribute("msg", "密码错误!");
					req.getRequestDispatcher("views/adminLogin.jsp").forward(req, resp);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
