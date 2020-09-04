package cn.edu.ujn.ch15.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import cn.edu.ujn.ch15.model.User;

public class LoginInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// login这个请求不要拦截，不然没办法登录
		String url = request.getRequestURI();
		// 判断url中是否有“login”
		if (url.indexOf("/login") >= 0)
			return true;
		HttpSession session = request.getSession();
		// 获取User对象
		User user = (User) session.getAttribute("USER_SESSION");
		// 判断user是否为空
		if (user != null)
			return true;
		// 为空就显示提示信息
		request.setAttribute("msg", "被拦截，您还没有登录，不能访问页面！");
		// 转发到login.jsp页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}
}
