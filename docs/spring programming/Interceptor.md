- Spring에서 HTTP Request와  HTTP Response를  Controller 앞과 뒤에서 가로채는 역할을 한다. 

- Servlet의 앞과 뒤에서 HTTP Request와  HTTP Response를 가로채는 필터와 유사
       하다.
   
- Interceptor를 구현하기 위해서는 HandlerInterceptor 인터페이스를 구현하여야 한다. 

   
### Filter 와 차이
![image](https://velog.velcdn.com/images/myway00/post/5215de0e-a767-4215-9db6-5830fa7ebc8a/image.png)

### MyInterceptor 구현

```java

public interface HandlerInterceptor {

   boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler)
       throws Exception;
    
   void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
        throws Exception;

   void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception;


```

HandlerInterceptor 인터페이스의 3개의 메소드를 구현한다. 

| 메소드            | 설명                                                                                           | 반환 값  |
|-------------------|------------------------------------------------------------------------------------------------|---------------|
| `preHandle()`     | 컨트롤러 메소드가 호출되기 전에 실행됩니다.                                                    | `true` - 핸들러 체인 계속 진행, `false` - 중단 및 인터셉터와 컨트롤러 실행 중지 |
| `postHandle()`    | 컨트롤러 메소드가 실행된 후에 호출됩니다.                                                     | -             |
| `afterCompletion()`| 모든 처리가 완료되고 최종 결과가 생성된 뒤 실행됩니다.                                     | -             |

`preHandle()` 메소드는 요청이 컨트롤러 메소드로 진행되기 전에 실행되며, `true`를 반환하면 핸들러 체인이 계속 진행되고, `false`를 반환하면 중단하고 남은 인터셉터와 컨트롤러가 실행되지 않습니다.

`postHandle()`와 `afterCompletion()` 메소드는 반환 값이 없으며, 컨트롤러 메소드 실행 이후에 뷰 렌더링 전후 또는 모든 처리가 완료된 이후에 다양한 후처리 또는 정리 작업을 수행하는 데 사용됩니다. 이러한 메소드는 Spring MVC의 인터셉터 프레임워크의 일부로 다양한 요청 처리 작업을 수행하는 데 활용됩니다.