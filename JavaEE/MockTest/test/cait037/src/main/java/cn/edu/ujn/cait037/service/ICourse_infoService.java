package cn.edu.ujn.cait037.service;

import java.util.List;

import cn.edu.ujn.cait037.dao.Course_info;

public interface ICourse_infoService {
	public int addCourse(Course_info course_info);

	public int deleteCourse(int id);

	public Course_info findCourseById(int id);

	public int changeCourse(Course_info course_info);

	public List<Course_info> findAllCourses();
}
