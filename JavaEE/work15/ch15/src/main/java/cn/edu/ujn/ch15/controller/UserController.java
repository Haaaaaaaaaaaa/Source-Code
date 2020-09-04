package cn.edu.ujn.ch15.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.ujn.ch15.model.User;

@Controller
public class UserController {
	// 以GET方式请求
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	// 以POST方式请求
	// User接受页面传递来的用户名和密码
	// Model向页面返回错误信息的
	// 登录成功后需要向session中写入User对象
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, Model model, HttpSession session) {
		// 用户名为A
		if ("A".equals(user.getUsername())) {
			session.setAttribute("USER_SESSION", user);
			return "redirect:main";
		}
		model.addAttribute("msg", "用户名或密码错误，请重新登录！");
		return "login";
	}

	@RequestMapping("/main")
	public String toMain() {
		return "main";
	}

	// 退出则清空session
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// 清空session
		session.invalidate();
		return "redirect:login";
	}
}
