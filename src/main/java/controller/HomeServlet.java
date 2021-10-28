package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.bean.News;
import service.NewsService;
import service.imp.NewsServiceImp;

@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//控制层调用服务层
		NewsService newsServiceImp = new NewsServiceImp(); 
		//控制层调用服务层接口方法
		List<News> newsList = newsServiceImp.queryAll(); 
		System.out.println("在控制层输出从数据库查询到的"+newsList.size()+"条技术文档记录"); 
		//设置转发数据
		request.setAttribute("newsList", newsList); 
		//请求转发，地址栏里的url不变,没有产生新的请求对象
		request.getRequestDispatcher("/views/index.jsp").forward(request, response); 
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
