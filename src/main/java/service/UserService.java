package service;
/*
 *  本接口定义了与用户相关的操作方法
 */
import java.util.List;

import dao.bean.User;

public interface UserService {
	
	public boolean isMember(String un, String pwd); //是否为会员？
	
	// 根据用户名查询用户信息
	public User queryUserByUsername(String username);
	
	//根据用户名和密码查询用户信息
	public User queryUserByUsernameAndPassword(String username, String password);
	
	//更新用户
	public void updateUser(User user);
	
	// 保存用户
	public void saveUser(User user);
	
	// 查询所有用户信息
	public List<User> queryAll();
	
	// 删除用户
	public void deleteUser(User user);
}