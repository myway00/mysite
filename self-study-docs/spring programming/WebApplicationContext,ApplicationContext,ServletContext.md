- ApplicationContext
   - Spring에서 만든 인터페이스로, 애플리케이션에 대한 context를 가지고 있다.

- WebApplicationContext
    - Spring의 ApplicationContext를 확장한 인터페이스로, 웹 애플리케이션에서 필요한 몇 가지 기능을 추가한 인터페이스
    - 예를 들면 WebApplicationContext의 구현체는 getServletContext라는 메소드를 통해 ServletContext를 얻을 수 있다.
 
- ServletContext 
    - 서블릿 컨텍스트(ServletContext)란 하나의 서블릿이 서블릿 컨테이너와 통신하기 위해서 사용되는 메서드들을 가지고 있는 클래스 
    - 여러 서블릿에서 공유할 수 있는 정보를 담는 객체
    - Spring Web MVC에서는 ServletContext가 WebApplicationContext를 가지고 있다. 
    - 아래의 그림과 같이 Serlvet Context가 WebApplication을 감싸고 있다. 
    - 아래에서 Root Applicaition Context와 Servlet Application Context 1~3 모두 WebApplicationContext 
    ![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FcCIMD8%2FbtqCtYkbnJu%2FKc3AMOZ2mBjkB3J1JcOQW0%2Fimg.png)

(+) 서블릿 컨테이너 복습 == 서블릿들을 보관하는 그릇(컨테이너)
- 서버에 만들어진 서블릿이 스스로 작동하는 것이 아니라, 서블릿을 관리 해주는 것이 필요한데, 이러한 역할을 하는 것이 바로 서블릿 컨테이너 (톰캣)
1. 웹서버와의 통신 지원
- 서블릿 컨테이너는 서블릿과 웹서버가 손쉽게 통신할 수 있게 해주어, 소켓을 만들고 listen, accept 등을 API로 제공하여 복잡한 과정을 생략할 수 있게 해준다.
2. 서블릿 생명주기(Life Cycle) 관리 
- 서블릿 컨테이너는 서블릿의 탄생과 죽음을 관리한다.
- 서블릿 클래스를 로딩하여 인스턴스화
- 초기화 메소드를 호출
- 요청이 들어오면 적절한 서블릿 메소드를 호출합니다.
서블릿 소멸 시 Garbage Collection(가비지 컬렉션)을 진행
3. 멀티쓰레드 지원 및 관리
- 서블릿 컨테이너는 요청이 올 때 마다 새로운 자바 쓰레드를 하나 생성
- HTTP 서비스 메소드를 실행하고 나면, 쓰레드는 자동으로 소멸
- 원래는 쓰레드를 관리해야 하지만 서버가 다중 쓰레드를 생성 및 운영해주니 쓰레드의 안정성에 대해서 걱정하지 않아도 된다.
4. 선언적인 보안 관리 

<사용자의 요청이 일어났을 때 하나의 라이프 사이클>
![image](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FoXx2P%2FbtrkjXbwMwz%2FFlLyfDeValXnlRzO4iPwp0%2Fimg.png)
1. 사용자가 서블릿에 대한 링크를 클릭한다.(HTTP 요청)
2. 컨테이너는 들어온 요청에 대해서 HttpServletRequest와 HttpServletResponse를 생성
3. 사용자가 보낸 URL를 분석해서 어떤 서블릿에 대한 요청인지 알아내고 스레드에 서블릿을 할당해서 실행하고 Request와 Response를 인자로 넘깁니다.
4. 컨테이너는 서블릿 Service()를 호출하고 Http메서드에 따라 doGet(), doPost()등을 호출
5. 그러면 서블릿은 요청 처리에 대한 응답을 생성해서 Response객체와 같은 곳에 실어서 응답을 보냅니다.
6. 응답이 이뤄지면 모든 작업이 끝난거고 컨테이너는 사용했던 request, response 객체를 소멸시키거나 쓰레드에 할당했던 서블릿을 해제하는 등의 일을 합니다.
- 서블릿 컨테이너에서 요청을 처리 할 서블릿을 찾아서 요청을 보내거나 파일의 MIME TYPE을 가져오거나 할 때 서블릿 컨텍스트의 메서드를 사용 
- 하나의 web application 내에 하나의 컨텍스트가 존재
- web application내에 있는 모든 서블릿들을 관리하며 정보 공유할 수 있게 도와 주는 역할을 담당하는 것이 바로 ServletContext 

> DispatcherServlet은 WebApplicationContext를 이용하여 자신을 설정
> - DispatcherServlet도 결국 Servle
> - 다른 Servlet과 마찬가지로 자바 설정이나 web.xml에 있는 설정에 따라 정의되어야 하고 매핑
> - DispatcherServlet은 WebApplicationContext를 이용하여 자신을 설정 

< Spring에서 Application Context와 Servlet Context를 나누는 기준 >

| Application Context (공통 기능을 위한 설정)             | Servlet Context (Servlet 구성에 필요한 설정)                  |
|-------------------------------------------------------|----------------------------------------------------------------|
| - 공통 기능을 수행하는 Bean 설정 (e.g., Datasource, Service 등) | - 각 Servlet에서 사용하는 Bean 설정 (e.g., Controller, Interceptor) |
| - 여러 Servlet에서 공유할 수 있는 Bean 정의               | - Servlet 단위로 구성된 Bean 정의                                |
| - 공통 설정 정보 및 서비스를 제공하는 부분                 | - Servlet-specific 설정 정보 및 요청 처리를 담당하는 부분         |

- Application Context는 애플리케이션 전체에서 공통으로 사용되는 기능과 데이터를 관리
- Servlet Context는 서블릿 단위로 요청을 처리하는 데 필요한 구성 요소를 정의 

| 개념                              | Application Context                                   | Servlet-Context (servlet-context.xml)                 |
|-----------------------------------|--------------------------------------------------------|-------------------------------------------------------|
| 위치                              | Web Application 최상단에 위치                           | Servlet 단위로 생성됨                                |
| 상속 관계                        | BeanFactory를 상속받고 있는 Context                    | Application Context를 부모로 사용                   |
| 설정 파일                        | root-context.xml, applicationContext.xml 등의 파일   | servlet-context.xml 파일                             |
| 역할                              | Bean 선언, IoC Container, 공통 Bean 선언             | DispatcherServlet 설정, URL 설정, Interceptor 설정 등 |
| 사용 예                          | - @Service, @Repository, @Configuration, @Component 선언 - 여러 Servlet에서 공유하는 Bean 선언 | - @Controller, Interceptor 설정 등                   |
| Bean 충돌 시 해결 순서         | 1. Servlet Context에서 먼저 찾음                      | 2. Servlet Context에서 못 찾을 경우 Application Context에서 찾음 |

이 표를 통해 Application Context와 Servlet-Context 간의 차이와 역할을 비교하고, Bean 충돌 시의 우선 순위를 이해할 수 있습니다. Application Context는 주로 공통적인 설정과 공유해야 하는 Bean을 정의하고, Servlet-Context는 서블릿 단위로 더 구체적인 설정을 할 때 사용됩니다.

[reference](https://live-everyday.tistory.com/164)