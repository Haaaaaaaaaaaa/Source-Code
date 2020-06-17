package cn.edu.ujn.cait037.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.edu.ujn.cait037.dao.Course_info;
import cn.edu.ujn.cait037.dao.Course_infoMapper;


@Service
public class Course_infoServiceImpl implements ICourse_infoService {
	@Autowired
	private Course_infoMapper coursr_infoMapper;

	@Override
	public int addCourse(Course_info course_info) {
		// TODO Auto-generated method stub
		int selective = this.coursr_infoMapper.insertSelective(course_info);
		return selective;
	}

	@Override
	public int deleteCourse(int id) {
		int key = this.coursr_infoMapper.deleteByPrimaryKey(id);
		return key;
	}

	@Override
	public Course_info findCourseById(int id) {
		Course_info course = this.coursr_infoMapper.selectByPrimaryKey(id);
		return course;
	}

	@Override
	public int changeCourse(Course_info course) {
		int selective = this.coursr_infoMapper.updateByPrimaryKeySelective(course);
		return selective;
	}

	@Override
	public List<Course_info> findAllCourses() {
		 
		return this.coursr_infoMapper.findAll();
	}

}
