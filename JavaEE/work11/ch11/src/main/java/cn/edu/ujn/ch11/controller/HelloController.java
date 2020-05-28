package cn.edu.ujn.ch11.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.edu.ujn.ch11.model.User;

@Controller
public class HelloController {
	@RequestMapping("/index")
	public String index() {
		return "index";
	}

	@RequestMapping("/login")
	public String login(User user) {
		System.out.println(user);
		return "ok";
	}

}
