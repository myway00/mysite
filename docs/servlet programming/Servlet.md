## 자바 EE, Servlet, ServletContext
| 개념                                 | 설명                                                                                                  |
|------------------------------------|-----------------------------------------------------------------------------------------------------|
| Java EE                            | 자바를 사용한 서버 측 개발을 위한 플랫폼으로, Java EE 스펙에 따라 구현된 제품을 WAS(WEB Application Server)라고 함.                  |
| Java Servlet                       | 자바로 웹 페이지를 동적으로 생성하기 위한 서버 측 프로그램 또는 사양. Java EE 사양의 일부.                                            |
| Servlet 생명주기                       | 1. 서블릿 컨테이너가 초기화를 위해 init() 메서드 호출.                                                                 |
|                                    | 2. 초기화 후에는 요청마다 초기화 생략 (싱글톤 패턴 사용).                                                                 |
|                                    | 3. 클라이언트 요청 처리를 위해 service() 메서드 호출.                                                                |
|                                    | 4. service()에서 HTTP 응답을 생성.                                                                         |
|                                    | 5. service()는 일반적으로 doGet() 또는 doPost()에 요청 처리 위임.                                                  |
|                                    | 6. 서블릿 컨테이너 판단에 따라 destroy()를 호출하여 서블릿 해제.                                                          |
| Servlet 컨테이너                       | 서블릿 컨테이너는 **소켓 생성, 포트 리스닝, 스트림 생성** 등 **웹 서버와 통신을 단순화**시켜줌.                                         | 
|                                    | 생명주기 관리와 요청 처리를 담당하며, 요청마다 새로운 자바 스레드를 생성.                                                          |
|                                    | 대표적인 Servlet Container로는 Tomcat 등이 있음.                                                              |
| Client - Servlet Container 동작 과정   | 1. 클릭된 URL을 통해 HTTP 요청을 서블릿 컨테이너에 전송.                                                               |
|                                    | 2. Servlet Container에서 HttpServletRequest와 HttpServletResponse 객체 생성.                               |
|                                    | 3. 요청된 URL을 분석하여 해당 서블릿 찾아냄.                                                                        |
|                                    | 4. 컨테이너는 서블릿의 service() 메서드 호출, GET 또는 POST 여부에 따라 doGet() 또는 doPost() 호출.                          |
|                                    | 5. doGet() 또는 doPost()에서 동적 페이지 생성 후 HttpServletResponse 객체로 응답 전송.                                 |
|                                    | 6. 응답 완료 후 HttpServletRequest와 HttpServletResponse 객체 소멸.                                           |
| ServletContext (웹 애플리케이션의 등록 정보) | 서블릿 컨텍스트는 웹 애플리케이션 내에서 정보 공유 및 관리를 위한 클래스.                                                          |
|                                    | 하나의 web application 내에 하나의 컨텍스트가 존재합니다. web application내에 있는 모든 서블릿들을 관리하며 정보공유할 수 있게 도와 주는 역할을 담당  |
|                                    | 하나의 웹 애플리케이션에 하나의 컨텍스트가 존재하며, 필터 및 리스너 등을 등록하여 정보 공유 가능.                                            |
| ServletContext 얻는 방법               | ServletConfig의 getServletContext() 메서드 사용 가능.                                                       |
| Servlet Listener                   | 웹 애플리케이션에서 발생하는 주요 이벤트 감지 및 이벤트에 대한 작업 수행에 사용됨.                                                     |
|                                    | 컨텍스트 라이프사이클 이벤트, 컨텍스트 애트리뷰트 변경 이벤트, 세션 라이프사이클 이벤트, 세션 애트리뷰트 변경 이벤트 등이 있음.                           |
| ContextLoaderListener              | ServletListener의 구현체로, WebApplicationContext를 생성하고 서블릿 컨텍스트 라이프사이클에 등록/소멸시킴.                        |
|                                    | 서블릿에서 IoC 컨테이너를 ServletContext를 통해 사용 가능.                                                           |

## Servlet 작성 방법 
| 단계                          | 내용                                                                                   |
|-----------------------------|----------------------------------------------------------------------------------------|
| 1. 서블릿 클래스 작성             | HttpServlet 클래스를 상속받는 서블릿 클래스를 작성한다.                                   |
| 2. 패키지 임포트                  | javax.servlet, javax.servlet.http, 그리고 java.io 패키지를 임포트한다.                    |
| 3. doGet 메소드 재정의             | HttpServlet 클래스의 doGet 메소드를 재정의하고, GET 방식을 사용하는 HTTP 요청을 처리한다.     |
| 4. doPost 메소드 재정의            | HttpServlet 클래스의 doPost 메소드를 재정의하고, POST 방식을 사용하는 HTTP 요청을 처리한다.   |
| 5. HttpServletRequest 객체 받기 | doGet과 doPost 메소드는 HttpServletRequest 객체(request 객체)를 인자로 받는다.               |
| 6. HttpServletResponse 객체 받기  | doGet과 doPost 메소드는 HttpServletResponse 객체(response 객체)를 인자로 받는다.             |
| 7. 응답 콘텐트 타입 설정           | response 객체의 setContentType 메소드를 사용하여 응답의 콘텐트 타입(content type)을 설정한다. |
| 8. PrintWriter 객체 생성           | getWriter 메소드를 사용하여 브라우저에 HTML 코드를 보내는 PrintWriter 객체를 생성한다.         |
| 9. 콘텐트 타입 설정 전 PrintWriter 객체 생성 | PrintWriter 객체를 생성하기 전에 콘텐트 타입을 설정해야 한다.                            |

## JSP가 서블릿으로 변환되고 컴파일 되는 방식
- JSP 페이지가 처음 요청되면 JSP 엔진은 페이지를 서블릿으로 변역하고 컴파일 
- JSP 엔진은 모든 JSP 스크립틀릿을 서블릿의 service 메소드에 위치
- 결과적으로 스크립틀릿에 정의된 모든 변수들은 service 메소드의 지역 변수가 됨 
- 서블릿 클래스가 컴파일 되고 나면 클래스의 인스턴스가 생성되고 새로운 스레드가 만들어짐 & service 메소드 호출 
- 다른 사용자가 JSP를 요청하면 서블릿의 인스턴스로부터  새로운 스레드가 만들어지고, service 메소드가 호출된
- service 메소드는 각 스레드에 대한 지역 변수의 복사본을 전달  
 

### Reference 
[MVC Servlet](https://jordy-torvalds.tistory.com/14)