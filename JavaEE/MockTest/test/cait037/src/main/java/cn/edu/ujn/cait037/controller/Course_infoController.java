package cn.edu.ujn.cait037.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ujn.cait037.dao.Course_info;
import cn.edu.ujn.cait037.service.ICourse_infoService;

@Controller
public class Course_infoController {
	@Autowired
	private ICourse_infoService course_infoService;

	// 添加课程
	@RequestMapping("/add")
	public String add() {
		return "addCourse";
	}

	@RequestMapping("/addACourse")
	public String addACourse(Course_info course_info, Model model) {
		// 添加课程的时间
		Date reDate = new Date(System.currentTimeMillis());
		course_info.setAddtime(reDate);
		int addCourse = this.course_infoService.addCourse(course_info);
		model.addAttribute("c", course_info);
		return "course";
	}

	// 返回列表
	@RequestMapping("/findAllCourses")
	public String findAllCourses(Model model) {
		List<Course_info> courses = this.course_infoService.findAllCourses();
		model.addAttribute("list", courses);
		return "listCourse";
	}

	// 删除
	@RequestMapping("/delete")
	public String deleteCourse(int id, Model model) {
		Course_info course = this.course_infoService.findCourseById(id);
		int deleteCourse = this.course_infoService.deleteCourse(id);
		List<Course_info> courses = this.course_infoService.findAllCourses();
		model.addAttribute("list", courses);
		return "listCourse";
	}
}
