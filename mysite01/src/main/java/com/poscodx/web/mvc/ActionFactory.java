package com.poscodx.web.mvc;

public abstract class ActionFactory {
    // ActionFactory 추상 클래스 정의
    public abstract Action getAction(String actionName);
    // getAction 메서드는 actionName을 기반으로 Action 객체를 생성하고 반환하는 역할을 합니다.
    // 실제 작업 객체의 생성 로직은 하위 클래스에서 구현해야 합니다.
    // 이 클래스는 작업 객체를 생성하고 관리하기 위한 추상적인 설계를 제공합니다.
}