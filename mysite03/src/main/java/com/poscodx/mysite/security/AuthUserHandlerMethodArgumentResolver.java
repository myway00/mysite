package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.poscodx.mysite.vo.UserVo;

public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
	@Override
	public Object resolveArgument(
		MethodParameter parameter,
		ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest,
		WebDataBinderFactory binderFactory) throws Exception {
		
		if(!supportsParameter(parameter)) {
			return WebArgumentResolver.UNRESOLVED;
		}
		
		HttpServletRequest request = (HttpServletRequest)webRequest.getNativeRequest();
		HttpSession session = request.getSession();
		return session.getAttribute("authUser");
	}

	/**
	 * 이 코드는 Spring MVC의 컨트롤러 메소드에서
	 * @AuthUser 어노테이션이 붙은 파라미터에 대해
	 * 사용자 정보를 추출하여 제공하는 역할
	 * @param parameter
	 * @return
	 */
	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		AuthUser authUser = parameter.getParameterAnnotation(AuthUser.class);
		
		// @AuthUser가 안 붙어 있으면,
		if(authUser == null) {
			return false;
		}
		
		// Parameter Type이 UserVo가 아니면,
		if(!parameter.getParameterType().equals(UserVo.class)) {
			return false;
		}
		
		return true;
	}
	
}
