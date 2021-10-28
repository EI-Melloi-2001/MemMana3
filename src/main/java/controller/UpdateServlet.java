package controller;
/*
 * 更新记录
 */
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.User;
import dao.utils.MyDb;

@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("utf8");  //在Servlet程序里，当接收请求参数（表单元素）里含有中文时必须使用
		User user = new User();
		user.setPassword(req.getParameter("password"));
		user.setRealname(req.getParameter("realname"));
		user.setMobile(req.getParameter("mobile"));
		String age = req.getParameter("age");
		if(!"".equals(age.trim())){
			user.setAge(Integer.valueOf(age));  //类型转型：String
		}
		String un = (String)req.getSession().getAttribute("username");  //得到会话信息
		
		try {
			Object[] objects = new Object[]{user.getPassword(), user.getRealname(), user.getMobile(), user.getAge(), un};
			MyDb.getMyDb().cud("update user set password=?,realname=?,mobile=?,age=? where username=?", objects);
			req.setAttribute("message","修改成功");
			req.getRequestDispatcher("views/public/message.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
