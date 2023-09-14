package com.poscodx.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.web.mvc.Action;
import com.poscodx.web.util.MvcUtil;

public class LoginOutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*	로그아웃 처리 */
		HttpSession session = request.getSession();
		session.removeAttribute("authUser");
		session.invalidate();
		 
		MvcUtil.redirect(request.getContextPath(), request, response);
	}

}
