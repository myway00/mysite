package com.poscodx.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poscodx.mysite.repository.SiteRepository;
import com.poscodx.mysite.vo.SiteVo;

import javax.servlet.ServletContext;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	@Autowired
	private ServletContext servletContext;

	public boolean update(SiteVo siteVo) {
		// ServletContext에 "siteVo"라는 이름으로 siteVo 객체 저장
		// therefore, 웹 애플리케이션 전체에서 해당 객체에 접근 가능
		servletContext.setAttribute("siteVo", siteVo);
		return siteRepository.update(siteVo);
	}

	public SiteVo getContents() {
		return siteRepository.find();
	}
}
