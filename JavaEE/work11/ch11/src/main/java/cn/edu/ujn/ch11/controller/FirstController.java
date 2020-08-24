package cn.edu.ujn.ch11.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class FirstController implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String name = request.getParameter("name");
		System.out.println("name+" + name);
		ModelAndView mav = new ModelAndView();
		mav.addObject("msg", "这是我的第一个SpringMVC程序！"+name);
		mav.setViewName("/WEB-INF/jsp/first.jsp");
		return mav;
	}

}
