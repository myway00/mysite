package com.poscodx.intercepter;

import com.poscodx.mysite.repository.SiteRepository;
import com.poscodx.mysite.vo.SiteVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SiteInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private SiteRepository siteRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        SiteVo siteVo = (SiteVo) request.getServletContext().getAttribute("siteVo");
        if (siteVo == null) {
            siteVo = siteRepository.find();
            request.getServletContext().setAttribute("siteVo", siteVo);
        }

        return true;
    }

}
