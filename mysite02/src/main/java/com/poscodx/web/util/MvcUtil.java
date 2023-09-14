package com.poscodx.web.util;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MvcUtil {
    // 지정된 JSP 페이지로 포워딩하는 유틸리티 메서드
    public static void forward(
        String path,  // 포워딩할 JSP 페이지의 경로
        HttpServletRequest request,  // HTTP 요청 객체
        HttpServletResponse response) throws ServletException, IOException {
        
        // RequestDispatcher를 생성하여 지정된 JSP 페이지로 포워딩합니다.
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/" + path + ".jsp");
        rd.forward(request, response);
    }
    
    // 지정된 URL로 리다이렉트하는 유틸리티 메서드
    public static void redirect(
        String url,  // 리다이렉트할 URL
        HttpServletRequest request,  // HTTP 요청 객체
        HttpServletResponse response) throws ServletException, IOException {
        
        // 지정된 URL로 클라이언트를 리다이렉트합니다.
        response.sendRedirect(url);
    }
}
