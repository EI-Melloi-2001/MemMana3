package dao;
/*
 * 本接口定义与user表相关的操作方法
 */
import java.util.List;

import dao.bean.User;

public interface UserDao {
	public boolean isMember(String un, String pwd); //是否为会员？
	public List<User> queryAll();  //查询所有用户
	public User queryOne(String un);  //根据主键查找
	public User queryUserByUsernameAndPassword(String un, String pwd); //根据用户名和密码查询
	public int updateUser(User user); //根据用户名和密码查询
}
