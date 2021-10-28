package controller;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.User;
import dao.utils.MyDb;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = new User();
		req.setCharacterEncoding("utf8");  //按照utf-8编码格式接受数据
		user.setUsername(req.getParameter("username"));
		user.setPassword(req.getParameter("password"));
		
		if("".equals(user.getUsername().trim()) || "".equals(user.getPassword().trim())){  //防止空提交
			req.setAttribute("message", "用户名和密码不能为空!");
			req.getRequestDispatcher("views/public/message.jsp").forward(req, resp);
		}else{
			String un=req.getParameter("username");  //准备查重
			String sql=null; ResultSet rs=null;
			try {
				sql="select * from user where username=?";
				rs=MyDb.getMyDb().query(sql, un);
				if(rs.next()){   //rs!=null
					req.setAttribute("message", "该用户名已经存在!");
					req.getRequestDispatcher("views/public/message.jsp").forward(req, resp);
				}else{
					user.setRealname(req.getParameter("realname"));
					user.setMobile(req.getParameter("mobile"));
					String age = req.getParameter("age");
					if(!"".equals(age.trim())){
						user.setAge(Integer.valueOf(age));  //类型转型：String
					}
					Object[] objects = new Object[]{un,user.getPassword(),user.getRealname(),user.getMobile(),user.getAge()};
					MyDb.getMyDb().cud("insert into user (username,password,realname,mobile,age) values(?,?,?,?,?)", objects);
					req.setAttribute("message","注册成功！"); //参数设置
					req.getRequestDispatcher("views/public/message.jsp").forward(req, resp);  //转向控制
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
