package cn.edu.ujn.ch1;

public class UserServiceImpl implements IUserService {

	private IUserDao userDao;
//	需要一个setter方法
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public void say() {
		// TODO Auto-generated method stub
		System.out.println("userservice say Hello World!");
//		Spring 注入
		userDao.say();
	}


}
