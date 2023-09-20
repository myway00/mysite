### 웹 애플리케이션 컨텍스트 ( Web Application Context  )

1. web.xml 서블릿 매핑 설정의 <servlet-name>에 ‘-servlet.xml’ 를 붙힌 이름의 파일을 WEB-INF에서 찾아 컨테이너에    
   Bean을 생성하고 초기화 

```<servlet-name>spring</servlet-name>
<servlet-class> org.springframework.web.servlet.DispatcherServlet </servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>spring</servlet-name>
    <url-pattern>/</url-pattern>
</servlet-mapping> 
```

2. <servlet-name> '-servlet.xml'  설정파일

```
<context:annotation-config />

<context:component-scan base-package="com.example.controller"/>

```
- Controller 빈을 등록하고 빈의 이름(URL)로 핸들러가 매핑된다.

- @MVC 기반에서 빈의 생성은 어노테이션 기반의 컴포넌트 스캐닝을 통해 생성되고  메서드가 핸들러 매핑과
       어댑터의 대상이 된다.

2. <servlet-name> '-servlet.xml'  설정파일

```
<bean  id="urlMapping" class="org.springframework.web.servlet.handler.SimpleUrlHandlerMapping" >
     <property name="mappings">
           <props>
	  <prop key="/member">memberController</prop>
           </props>
     </property>
</bean>

<bean id=" memberController" class= "com.example.controller.MemberController"> 

```
- 핸들러 어댑터의 대상이 객체이고 객체의 handleRequest( HttpServletRequest req, HttpServletResponse resp )
       메소드 하나만이 url대상이 된다.

### 루트 애플리케이션 컨텍스트 ( Root Application Context  )

1. 리스너를 등록해 두면, 루트 컨텍스트가 생성되게 되며, 설정 파일은 디폴트로  /WEB-INF/applicationContext.xml   
   이다.

```
<listener-class> org.springframework.web.context.ContextLoaderListener </listener-class>
</listener>

<context-param>
     <param-name> contextConfigLocation </param-name>
     <param-value>/WEB-INF/applicationContext.xml</param-value>
</context-param> 	

```

2. 서비스 계층, 데이터 액세스 계층을 포함해서 웹 환경과 직접 관련이 없는 모든 빈은 여기에 등록한다.
