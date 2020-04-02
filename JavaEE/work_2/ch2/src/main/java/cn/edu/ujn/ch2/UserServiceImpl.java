package cn.edu.ujn.ch2;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {
	
	public UserServiceImpl() {
//		super();
		// TODO Auto-generated constructor stub
		System.out.println("UserServiceImpl构造方法被调用");
	}
//@Resource(name="userDao")//手动注解
	@Autowired//自动的，没有参数
	private IUserDao userDao;
//	这里只需用set方法
//	public void setUserDao(IUserDao userDao) {
//		this.userDao = userDao;
//	}

	@Override
	public void save() {
		// TODO Auto-generated method stub
		this.userDao.save();
		System.out.println("UserServiceImpl saving......");
	}
	private void init() {
		
		System.out.println("UserServiceImpl 初始化了");
	}
	private void dest() {
		System.out.println("UserServiceImpl 被销毁了");
		
	}

}
