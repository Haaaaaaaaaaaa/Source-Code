package cn.edu.ujn.ch3.aop;

import org.springframework.stereotype.Repository;
//取个名字叫userDao
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Override
	public void addUser() {
		// TODO Auto-generated method stub
		System.out.println("添加用户");
	}

	@Override
	public void deleteUser() {
		// TODO Auto-generated method stub
		System.out.println("删除用户");
	}

}
