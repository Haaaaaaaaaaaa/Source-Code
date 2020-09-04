package cn.edu.ujn.ch14.c;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.ujn.ch14.m.User;

@Controller
public class UserController {
	@RequestMapping("/hello")
	@ResponseBody
	public User hello() {
		User u = new User();
		u.setUsername("ct");
		u.setPassword("1111");
		return u;
	}

	@RequestMapping("/testJson")
	@ResponseBody
	public User testJson(@RequestBody User user) {
		System.out.println(user);
		user.setUsername("hello" + user.getUsername());
		return user;
	}

	@RequestMapping("/user/{id}")
	@ResponseBody
	public User selectUser(@PathVariable("id") String id) {
		System.out.println(id);
		User user = new User();
		if ("123".equals(id)) {
			user.setUsername("ct");
			user.setPassword("111");
		}
		return user;

	}
}
