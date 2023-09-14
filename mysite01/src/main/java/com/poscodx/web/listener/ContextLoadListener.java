package com.poscodx.web.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ContextLoadListener implements ServletContextListener {
    // 웹 애플리케이션 시작 시 호출되는 메서드
    public void contextInitialized(ServletContextEvent sce) {
        // ServletContextEvent로부터 ServletContext 객체를 얻습니다.
        ServletContext sc = sce.getServletContext();
        
        // 웹 애플리케이션 초기화 매개변수로부터 설정 정보를 가져옵니다.
        String contextConfigLocation = sc.getInitParameter("contextConfigLocation");
        
        // 애플리케이션 초기화 메시지를 출력합니다.
        System.out.println("Application (mysite01) Starts... : " + contextConfigLocation);
    }

    // 웹 애플리케이션 종료 시 호출되는 메서드
    public void contextDestroyed(ServletContextEvent sce) {
        // 애플리케이션 종료 메시지를 출력합니다.
        System.out.println("Application (mysite01) Stops...");
    }
}
