### 패키지 / 클래스
| 패키지 또는 클래스                        | 역할 및 설명                                   |
| ------------------------------------ | ---------------------------------------------- |
| com.poscodx.web.mvc.ActionFactory    | 웹 애플리케이션의 액션 객체를 생성하는 팩토리 클래스     |
| com.poscodx.web.mvc.Action           | 각각의 액션 클래스가 구현해야 하는 인터페이스          |
| com.poscodx.mysite.controller.MainController    | 메인 화면과 관련된 요청을 처리하는 컨트롤러          |
| com.poscodx.mysite.controller.UserController    | 사용자와 관련된 요청을 처리하는 컨트롤러            |
| com.poscodx.mysite.controller.GuestbookController | 방명록과 관련된 요청을 처리하는 컨트롤러           |
| com.poscodx.mysite.dao.GuestbookDao     | 방명록 데이터베이스와 상호작용하는 DAO 클래스        |
| com.poscodx.mysite.dao.UserDao          | 사용자 데이터베이스와 상호작용하는 DAO 클래스         |
| com.poscodx.mysite.vo.GuestbookVo       | 방명록 데이터를 담는 VO (Value Object) 클래스         |
| com.poscodx.mysite.vo.UserVo            | 사용자 정보를 담는 VO 클래스                            |
| com.poscodx.mysite.mvc.main.MainActionFactory | 메인 화면과 관련된 액션 객체를 생성하는 팩토리 클래스 |
| com.poscodx.mysite.mvc.main.MainAction   | 메인 화면과 관련된 액션 클래스                         |
| com.poscodx.mysite.mvc.user.JoinAction   | 사용자 회원가입 요청을 처리하는 액션 클래스            |
| com.poscodx.mysite.mvc.user.JoinFormAction | 회원가입 폼을 표시하는 액션 클래스                    |
| com.poscodx.mysite.mvc.user.JoinSuccessAction | 회원가입 성공 후 처리를 담당하는 액션 클래스          |
| com.poscodx.mysite.mvc.user.LoginAction  | 로그인 요청을 처리하는 액션 클래스                     |
| com.poscodx.mysite.mvc.user.LoginFormAction | 로그인 폼을 표시하는 액션 클래스                     |
| com.poscodx.mysite.mvc.user.LogoutAction | 로그아웃 요청을 처리하는 액션 클래스                   |
| com.poscodx.mysite.mvc.user.UpdateAction | 사용자 정보 업데이트 요청을 처리하는 액션 클래스       |
| com.poscodx.mysite.mvc.user.UpdateFormAction | 사용자 정보 업데이트 폼을 표시하는 액션 클래스       |
| com.poscodx.mysite.mvc.user.UpdateSuccessAction | 정보 업데이트 성공 후 처리를 담당하는 액션 클래스   |
| com.poscodx.mysite.mvc.guestbook.AddAction | 방명록 추가 요청을 처리하는 액션 클래스               |
| com.poscodx.mysite.mvc.guestbook.DeleteAction | 방명록 삭제 요청을 처리하는 액션 클래스             |
| com.poscodx.mysite.mvc.guestbook.DeleteFormAction | 방명록 삭제 폼을 표시하는 액션 클래스             |
| com.poscodx.mysite.mvc.guestbook.GuestbookActionFactory | 방명록과 관련된 액션 객체를 생성하는 팩토리 클래스 |
| com.poscodx.mysite.mvc.guestbook.IndexAction | 방명록 목록을 표시하는 액션 클래스                   |
| com.poscodx.web.filter.EncodingFilter | 인코딩 필터 클래스, HTTP 요청/응답의 인코딩 설정을 처리 |
| com.poscodx.web.util.MvcUtil          | 웹 애플리케이션에서 유틸리티 기능을 제공하는 클래스    |

### XXXAction 이 수행하는 일 
```agsl
public interface Action {
    // Action 인터페이스 정의
    public void execute(
        HttpServletRequest request, 
        HttpServletResponse response) throws ServletException, IOException;
    // execute 메서드는 HTTP 요청을 처리하고 응답을 생성하는 역할을 합니다.
    // request: HTTP 요청에 관련된 정보를 가진 객체로, 클라이언트가 보낸 데이터 및 요청 헤더 등을 포함합니다.
    // response: HTTP 응답을 생성하고 클라이언트에게 전송하는 데 사용되는 객체로, 작업 결과를 클라이언트에게 반환합니다.
}
```

### Listener 이 수행하는 일
| 역할                       | 설명                                                                                                         |
|----------------------------|--------------------------------------------------------------------------------------------------------------|
| `ServletContextListener`   | 웹 애플리케이션의 생명주기 이벤트를 감지하고 처리하는 리스너 인터페이스입니다.                            |
| `contextInitialized` 메서드 | 웹 애플리케이션이 초기화될 때 호출되며, 주로 초기화 작업을 수행하는 데 사용됩니다.                         |
| `contextDestroyed` 메서드   | 웹 애플리케이션이 종료될 때 호출되며, 주로 애플리케이션의 정리 작업을 수행하는 데 사용됩니다.             |
| `ServletContextEvent` 객체  | 웹 애플리케이션의 `ServletContext` 객체와 함께 이벤트를 전달하며, 이를 통해 웹 애플리케이션 정보에 접근할 수 있습니다. |
| 설정 정보 읽기 및 초기화    | `contextInitialized` 메서드에서 `ServletContext`를 통해 초기화 매개변수를 읽고 애플리케이션 초기화 작업을 수행합니다. |
| 로깅 및 메시지 출력        | 리스너는 웹 애플리케이션의 시작 및 종료와 같은 이벤트에 대한 로깅 및 메시지 출력을 수행하여 애플리케이션 상태를 모니터링하고 디버깅합니다. |

### 