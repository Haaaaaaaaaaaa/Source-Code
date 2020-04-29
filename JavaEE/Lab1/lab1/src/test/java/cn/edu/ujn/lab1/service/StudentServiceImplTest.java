package cn.edu.ujn.lab1.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.ujn.lab1.model.Student;
import cn.edu.ujn.lab1.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class StudentServiceImplTest {
	@Autowired
	private Student stu;
	@Autowired
	private IStudentService studentService;

//	测试添加学生信息
	@Test
	public void addStudentTest() {
		stu.setUid(1);
		stu.setStuname("蔡涛");
		stu.setStunumber("202001");
		int addStudent = this.studentService.addStudent(stu);
		System.out.println("插入成功");
	}

//	测试查询所有学生信息
	@Test
	public void findAllStudentTest() {
		List<Student> stu = this.studentService.findAllStudent();
		// 循环输出集合中的对象
		for (Student st : stu) {
			System.out.println(st);
		}
	}

//	修改学生信息
	@Test
	public void updateStudentTest() {
		Student student = new Student();
		student.setUid(1);
		student.setStunumber("202001");
		student.setStuname("蔡涛");

		int num = this.studentService.updateStudent(student);
		if (num > 0) {
			System.out.println("成功修改了" + num + "条数据！");
		} else {
			System.out.println("修改操作执行失败！");
		}

	}

//	删除学生信息
	@Test
	public void deleteStudenttTest() {
		int num = this.studentService.deleteStudent(2);
		if (num > 0) {
			System.out.println("成功删除了" + num + "条数据！");
		} else {
			System.out.println("删除操作执行失败！");
		}
	}
}
