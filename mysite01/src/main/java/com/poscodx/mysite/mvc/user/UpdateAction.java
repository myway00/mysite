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

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/* 접근 제어 해야 함 */
		//	 Access Control
		HttpSession session = request.getSession();
		
		Long no =  Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String gender = request.getParameter("gender");
		
		UserVo vo = new UserVo();
		vo.setName(name);
		vo.setEmail(email);
		vo.setPassword(password);
		vo.setGender(gender);
		vo.setNo(no);
		
		new UserDao().updateByNo(vo);
		session.removeAttribute("authUser");
		
		UserVo authUser = new UserDao().findByEmailAndPassword(email, password);
		session.setAttribute("authUser", authUser);
		MvcUtil.redirect(request.getContextPath() + "/user?a=updatesuccess", request, response);
	}

}
