package service;
import java.util.List;

import dao.bean.News;

public interface NewsService {
	 // 查询所有新闻
	public List<News> queryAll();
}
