package controller_admin;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.Pager;
import dao.bean.User;
import dao.utils.MyDb;

@WebServlet("/MemListServlet")
public class MemListServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			String sql = "select * from user order by password asc"; 
			int pageSize=3; //设定每页记录数
			String parameter=req.getParameter("p"); //导航条传来的页码
			Integer page=(parameter!=null)?Integer.valueOf(parameter):1; //初始指定第1页
			Pager pager=MyDb.getMyDb().queryAllWithPage(sql, page, pageSize, req);
			req.setAttribute("pageNav", pager.getPageNav());  //转发记录导航
			ResultSet rs = pager.getRs();  //记录
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setRealname(rs.getString("realname"));
				user.setMobile(rs.getString("mobile"));
				String age = rs.getString("age");
				if (age != null || !"".equals(age)) {
					user.setAge(Integer.parseInt(age));
				}
				users.add(user);
			}
			req.setAttribute("users", users); // 转发记录
			
			// 设置属性delete减少了在MemDeleteServlet里创建List类型的列表数据的代码
			Object isDeleteFlag = req.getAttribute("delete");
			if (isDeleteFlag != null) {
				req.getRequestDispatcher("views/admin/memDelete.jsp").forward(req, resp);
			} else {
				req.getRequestDispatcher("views/admin/memInfo.jsp").forward(req, resp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
