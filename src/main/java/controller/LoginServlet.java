package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import service.imp.UserServiceImp;
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String un=request.getParameter("username");
		String pwd=request.getParameter("password");

		UserService userServiceImp = new UserServiceImp();
		if(userServiceImp.isMember(un,pwd)){
			HttpSession session = request.getSession();
			session.setAttribute("username", un);  //设置会话
			response.sendRedirect("HomeServlet"); //重定向到主控制器
		}else{
			request.setAttribute("message", "用户名或密码错误!"); //属性设置,向请求页面传参
			request.getRequestDispatcher("/views/public/message.jsp").forward(request, response);
		}
	}
}
