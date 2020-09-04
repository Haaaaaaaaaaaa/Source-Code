package cn.edu.ujn.ch15.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import cn.edu.ujn.ch15.model.User;

public class LoginInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// login�������Ҫ���أ���Ȼû�취��¼
		String url = request.getRequestURI();
		// �ж�url���Ƿ��С�login��
		if (url.indexOf("/login") >= 0)
			return true;
		HttpSession session = request.getSession();
		// ��ȡUser����
		User user = (User) session.getAttribute("USER_SESSION");
		// �ж�user�Ƿ�Ϊ��
		if (user != null)
			return true;
		// Ϊ�վ���ʾ��ʾ��Ϣ
		request.setAttribute("msg", "�����أ�����û�е�¼�����ܷ���ҳ�棡");
		// ת����login.jspҳ��
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		return false;
	}
}
