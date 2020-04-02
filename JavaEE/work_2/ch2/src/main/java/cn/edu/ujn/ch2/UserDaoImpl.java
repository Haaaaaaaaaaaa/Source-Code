package cn.edu.ujn.ch2;

import org.springframework.stereotype.Repository;
//仓库
//相当于<bean name="userDao" class=""></>
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	@Override
	public void save() {
		// TODO Auto-generated method stub
		System.out.println("UserDaoImpl saving........");
	}

}
