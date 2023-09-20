### Annotation 이란?
1. Java5부터 지원
2. Class, Method, Parameter에 메타정보를  삽입한다.
3. **컴파일** 할 때, 또는 실행시  해당타켓에  코드를 추가적으로 실행할 수 있게 한다.
4. 코드의 가독성을 향상시키고 체계적인 코드를 구성할 수 있다.
5. 정의
```java

@Target( ElemenType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface Auth {

} 
```
    - @Target :  어노테이션의 타켓을 지정 ( FIELD, METHOD, PARAMETER, TYPE)
    - @Retention: 어노테이션의 지속 (보존) 기간을 지정(  RUNTIME, SOURCE )  
 