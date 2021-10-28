package service.imp;
/*
 * 
 */
import java.util.List;

import dao.bean.User;
import dao.UserDao;
import dao.imp.UserDaoImp;
import service.UserService;

public class UserServiceImp  implements UserService {
	
	@Override
	public boolean isMember(String un,String pwd) {
		// TODO Auto-generated method stub
		UserDao userDaoImp = new UserDaoImp();
		return userDaoImp.isMember(un, pwd);
	}
	
	@Override
	public User queryUserByUsername(String username) {
		return null;
	}

	@Override
	public User queryUserByUsernameAndPassword(String un, String pwd) {
		UserDao userDaoImp = new UserDaoImp();
		return userDaoImp.queryUserByUsernameAndPassword(un, pwd);
	}

	@Override
	public void updateUser(User user) {
		//this.update(user);
	}

	@Override
	public void saveUser(User user) {
		//this.add(user);
	}

	@Override
	public List<User> queryAll() {
		return null;
	}

	@Override
	public void deleteUser(User user) {
		//this.delete(user);
	}


}
