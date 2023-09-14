## 세션 ( Session )
- 서블릿 엔진(톰캣)에서 세션을 유지하는 방법
![image](https://velog.velcdn.com/images/myway00/post/20792428-0fe5-4311-8aee-d9997ef1956c/image.png)

### 세션객체(HttpSession) 속성 객체 저장 
- 세션 객체 얻기 (request 객체의 메소드)
```
public HttpSession getSession()
```

- 세션 객체의 기본 메소드
```
public void setAttribute(String name, Object value)
public Object getAttribute(String name)
public void removeAttribute(String name)
```
