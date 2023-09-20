
![image](https://velog.velcdn.com/images/myway00/post/b5372de7-b6b1-4db5-ae1f-31c163b7312f/image.png)

1. 사용자의 요청을 DispatcherServlet이 받는 다.

2. 요청을 처리해야 하는 컨트롤을  찾기 위해 HandlerMapping에게 질의를 하고 HandlerMapping은 컨트롤 객체에
   매핑되어 있는 URL를 찾아낸다.

3. DispatcherServlet은 찾은 컨트롤에게 요청을 전달하고 Controller는 서비스 계층의 인터페이스를 호출하여 적절한
   비지니스를 수행한다.

4. 컨트롤러는 비지니스 로직의 수행결과로 받아낸 도메인 모델 객체와 함께 뷰이름을 ModelAndView 객체에 저장하여
   반환한다.

5. DispatcherServlet은 응답할 View를 찾기 위해 ViewResolver에게 질의를 한다.

6. DispatcherServlet은 찾아낸 View 객체에게 요청을 전달한다.
