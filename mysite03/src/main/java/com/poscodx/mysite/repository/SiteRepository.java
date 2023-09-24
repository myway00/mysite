package com.poscodx.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.poscodx.mysite.vo.SiteVo;

@Repository
public class SiteRepository {
    @Autowired
    private SqlSession sqlSession;

    public boolean update(SiteVo siteVo) {
        return sqlSession.update("site.update", siteVo)==1;
    }

    public SiteVo find() {
        SiteVo sitevo = sqlSession.selectOne("site.find");
        return sitevo;
    }

}
