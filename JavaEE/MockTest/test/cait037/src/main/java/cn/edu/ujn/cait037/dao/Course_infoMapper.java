package cn.edu.ujn.cait037.dao;

import java.util.List;

import cn.edu.ujn.cait037.dao.Course_info;

public interface Course_infoMapper {
	int deleteByPrimaryKey(Integer id);

	int insert(Course_info record);

	int insertSelective(Course_info record);

	Course_info selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Course_info record);

	int updateByPrimaryKey(Course_info record);

	List<Course_info> findAll();
}