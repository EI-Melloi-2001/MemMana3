/*
 * 获取当前登录用户信息，转发至更新表单页面
 */
package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.bean.User;
import dao.utils.MyDb;

@WebServlet("/UpdateServlet0")
public class UpdateServlet0 extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//先检查用户是否登录？即登录用户才能使用本功能
		HttpSession session = request.getSession();
		if(session.getAttribute("username")==null){
			request.setAttribute("message","尚未登录！");
			request.getRequestDispatcher("views/public/message.jsp").forward(request, response);
		}else {
			//先查询登录用户信息，转发给视图页面 mUpdate.jsp
			String un=(String)session.getAttribute("username");
			ResultSet rs;
			try {
				rs = MyDb.getMyDb().query("select * from user where username=?", un);
				rs.next(); // 定位该会员所的记录
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setMobile(rs.getString("mobile"));
				user.setAge(rs.getInt("age"));
				request.setAttribute("user",user);  //转发至修改的表单页面的数据
				//System.out.println(user); //控制台测试
				request.getRequestDispatcher("views//mUpdate.jsp").forward(request, response);
				//转发至修改的表单页面
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
