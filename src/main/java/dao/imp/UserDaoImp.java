package dao.imp;


import java.sql.ResultSet;
import java.util.List;

import dao.bean.User;
import dao.UserDao;
import dao.utils.MyDb;

public class UserDaoImp implements UserDao {

	public boolean isMember(String un,String pwd) {
		// TODO Auto-generated method stub
		try {
			ResultSet rs=MyDb.getMyDb().query("select * from user where username=? and password=?",un,pwd);
			if(rs.next())  return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<User> queryAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public User queryOne(String un) {
		// TODO Auto-generated method stub
		return null;
	}

	public User queryUserByUsernameAndPassword(String un, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}


}
