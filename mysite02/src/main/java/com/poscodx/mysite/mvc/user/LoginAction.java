package com.poscodx.mysite.mvc.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.poscodx.mysite.dao.UserDao;
import com.poscodx.mysite.vo.UserVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.util.MvcUtil;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		UserVo authUser = new UserDao().findByEmailAndPassword(email, password);
		
		if(authUser == null) {
			request.setAttribute("result", "fail");
			request.setAttribute("email", email);
			MvcUtil.forward("user/loginform", request, response);
			return;
		}
		
		// 인증 처리(Session 처리)
		HttpSession session =  request.getSession(true);
		session.setAttribute("authUser", authUser);
		MvcUtil.redirect(request.getContextPath(), request, response);
		 
	}

}
