package com.poscodx.mysite.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.poscodx.mysite.vo.UserVo;

public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //1. handler 종류 확인
        if (!(handler instanceof HandlerMethod)) {
            // DefaultServletHanlder가 처리하는 경우(정적 자원, /assets/**)
            return true;
        }

        //2. casting
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        //3-1. Handler Method의 @Auth 가져오기
        // 메소드가 지정하는 권한
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        //3-2. Handler Method의 @Auth가 없는 경우, Type(Class)의 @Auth 가져오기
        if (auth == null) {
            //handlerMethod.
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
        }

        //4. @Auth 가 없는 경우
        // 따로 권한이 없는 메소드와 클래스이므로 허용
        if (auth == null) {
            return true;
        }

        //5. @Auth 가 붙어 있는 경우, 인증(Authenfication) 여부 확인
        // 5-1. 세션이 없는 경우 -> 로그인
        HttpSession session = request.getSession();
        if (session == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        // 5-2. 세션이 있는 경우
        // 유효한 세션 얻기 위해 -> 로그인
        UserVo authUser = (UserVo) session.getAttribute("authUser");
        if (authUser == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }

        //6. 권한(Authorization) 체크를 위해서 @Auth의 Role 가져오기("USER", "ADMIN")
        // 메소드가 허용하는 권한
        String role = auth.Role();
        if ("USER".equals(role)) {
            return true;
        }

        // 현재 유저의 권한
        String authUserRole = authUser.getRole()
        if ("ADMIN".equals(authUserRole) == false) {
            response.sendRedirect(request.getContextPath());
            return false;
        }

        //6. 인증 확인!!!
        //	@Auth의 role: "ADMIN"
        //	authUser의 role : "ADMIN"
        return true;
    }

}
