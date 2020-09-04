package cn.edu.ujn.ch15.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
	@RequestMapping("/hello")
	public String hello() {
		System.out.println("hello!");
		return "success";
	}

	@RequestMapping("/find")
	public String find() {
		System.out.println("Find!");
		return "success";

	}
}
