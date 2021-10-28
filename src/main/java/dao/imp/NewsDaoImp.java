package dao.imp;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.bean.News;
import dao.NewsDao;
import dao.utils.MyDb2;

public class NewsDaoImp implements NewsDao {
	ResultSet rs = null; //JDBC内容记录集
	List<News> newsList = null;  //Java的List集合
	//MyBatis框架不需要手工转，它是一个ORM（Object Relationship Mapping）产品
	@Override
	public List<News> queryAll() {
		try {
			String sqlString = "select * from news order by contentTitle asc";
			//rs = MyDb.getMyDb().query(sqlString);
			rs = MyDb2.query(sqlString);
			newsList = new ArrayList<News>(); //创建Java集合对象
			while (rs.next()) {  //将记录集封装成Java集合对象
				News news = new News();  //创建实体类对象
				news.setContentTitle(rs.getString("contentTitle"));
				news.setContentPage(rs.getString("contentPage"));
				newsList.add(news);  //添加到List集合
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return newsList;
	}
}
