package cn.edu.ujn.caitao.lab2.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ujn.caitao.lab2.dao.User;
import cn.edu.ujn.caitao.lab2.dao.UserMapper;

// 添加Service层注解
@Service("userService")
public class UserServiceImpl implements IUserService {
	// 注入UserMapper类，用来实现对User表的增删改查
	@Autowired
	private UserMapper userMapper;

	// 查找用户
	@Override
	public User selectByPrimaryKey(Integer id) {
		User user = userMapper.selectByPrimaryKey(id);
		return user;
	}

	// 删除用户
	@Override
	public int deleteByPrimaryKey(Integer id) {
		return this.userMapper.deleteByPrimaryKey(id);
	}

	// 添加用户
	@Override
	public int insert(User record) {

		return this.userMapper.insertSelective(record);
	}

	// 更新用户
	@Override
	public int updateByPrimaryKey(User record) {

		return this.userMapper.updateByPrimaryKeySelective(record);
	}

	// 查找所有的用户
	@Override
	public List<User> findAllUser() {
		List<User> allUser = userMapper.findAllUser();
		return allUser;
	}

}
