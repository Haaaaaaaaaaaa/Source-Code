package cn.edu.ujn.ch15.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.edu.ujn.ch15.model.User;

@Controller
public class UserController {
	// ��GET��ʽ����
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String toLogin() {
		return "login";
	}

	// ��POST��ʽ����
	// User����ҳ�洫�������û���������
	// Model��ҳ�淵�ش�����Ϣ��
	// ��¼�ɹ�����Ҫ��session��д��User����
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(User user, Model model, HttpSession session) {
		// �û���ΪA
		if ("A".equals(user.getUsername())) {
			session.setAttribute("USER_SESSION", user);
			return "redirect:main";
		}
		model.addAttribute("msg", "�û�����������������µ�¼��");
		return "login";
	}

	@RequestMapping("/main")
	public String toMain() {
		return "main";
	}

	// �˳������session
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		// ���session
		session.invalidate();
		return "redirect:login";
	}
}
