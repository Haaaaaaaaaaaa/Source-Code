package cn.edu.ujn.ch10.dao;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class CustomerDaoImpl extends SqlSessionDaoSupport implements ICustomerDao {
	// 依赖注入
	@Autowired
	@Override
	// 复写父类的setter方法
	public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
		// TODO Auto-generated method stub
		super.setSqlSessionFactory(sqlSessionFactory);
	}

	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		// 使用statement
		int update = this.getSqlSession().update("cn.edu.ujn.ch10.dao.CustomerMapper.insert", customer);
		System.out.println("----添加了" + update + "条数据。");
		// 人为添加异常
//		int i = 1 / 0;
		// 使用mapper接口
		CustomerMapper mapper = this.getSqlSession().getMapper(CustomerMapper.class);
		int insert = mapper.insert(customer);
		System.out.println("----mapper增加了" + insert + "条数据");

	}

}
