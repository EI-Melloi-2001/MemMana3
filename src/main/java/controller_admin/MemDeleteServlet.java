package controller_admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.utils.MyDb;

@WebServlet("/MemDeleteServlet")
public class MemDeleteServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String un=(String)req.getParameter("un"); //获取GET请求传参 
			if(un!=null){ 
				MyDb.getMyDb().cud("delete from user where username=?",un); //删除会员记录
			}
			req.setAttribute("delete", "true"); //设置转发属性：显示删除链接
			req.getRequestDispatcher("MemListServlet").forward(req, resp);  //请求动作转发，并非转发至视图
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
