package com.poscodx.web.mvc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
    // Action 인터페이스 정의
    public void execute(
        HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    // execute 메서드는 HTTP 요청을 처리하고 응답을 생성하는 역할을 합니다.
    // request: HTTP 요청에 관련된 정보를 가진 객체로, 클라이언트가 보낸 데이터 및 요청 헤더 등을 포함합니다.
    // response: HTTP 응답을 생성하고 클라이언트에게 전송하는 데 사용되는 객체로, 작업 결과를 클라이언트에게 반환합니다.
}
