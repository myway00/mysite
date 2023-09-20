### ExceptionHandler 에서의 예외 처리 

- Bean 에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능
- @ExceptionHandler 에 설정한 예외가 발생하면 handler가 실행

```java

@ExceptionHandler( UserDaoException.class )
public String handleUserDaoException() {
	return "/WEB-INF/views/error/exception.jsp";
}

```
- @ExceptionHandler 를 사용해서 Exception 과 핸들러를 매핑한다.

- Controller의 개별 핸들러 메소드에서 예외를 매핑하는 것보다 컨트롤러 어드바이스를 사용해서 애플리케이션의 같은
  종류의 예외를 처리하는 것이  효과적 

### ControllerAdvice 예외 처리 (spring 3.2 부터 지원)
- 모든 예외를 핸들링
- @ControllerAdvice 어노테이션으로 모든 예외를 한 곳에서 처리 
   - @Controller 어노테이션이 있는 모든 곳에서의 예외를 잡을 수 있도록 해준다.
```java
@ControllerAdvice
public class ApplicationExceptionHandler {

	@ExceptionHandler( UserDaoException.class )
	public String handleDaoException( Exception e ) {
		return "/WEB-INF/views/error/exception.jsp";
	}
}
```

- @ControllerAdvice 는 @Component 를 상속한 어노테이션 이기 때문에 컴포넌트 스캐닝 을 통해 선택된다.

- 실용적인 방법은 @ExceptionHandler를 사용해서 하나의 예외에 하나의 예외 핸들러를 묶는 방식이다.

- 핸들러에 @ResponseStatus 를 사용하여 클라이언트의 응답 코드를 지정할 수 있다. 

### Controller Advice 
```java
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     *  javax.validation.Valid or @Validated 으로 binding error 발생시 발생한다.
     *  HttpMessageConverter 에서 등록한 HttpMessageConverter binding 못할경우 발생
     *  주로 @RequestBody, @RequestPart 어노테이션에서 발생
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("handleMethodArgumentNotValidException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * @ModelAttribut 으로 binding error 발생시 BindException 발생한다.
     * ref https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-modelattrib-method-args
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<ErrorResponse> handleBindException(BindException e) {
        log.error("handleBindException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INVALID_INPUT_VALUE, e.getBindingResult());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * enum type 일치하지 않아 binding 못할 경우 발생
     * 주로 @RequestParam enum으로 binding 못했을 경우 발생
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("handleMethodArgumentTypeMismatchException", e);
        final ErrorResponse response = ErrorResponse.of(e);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * 지원하지 않은 HTTP method 호출 할 경우 발생
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.METHOD_NOT_ALLOWED);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    /**
     * Authentication 객체가 필요한 권한을 보유하지 않은 경우 발생합니다.
     */
    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("handleAccessDeniedException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.HANDLE_ACCESS_DENIED);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ErrorCode.HANDLE_ACCESS_DENIED.getStatus()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponse> handleBusinessException(final BusinessException e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorCode errorCode = e.getErrorCode();
        final ErrorResponse response = ErrorResponse.of(errorCode);
        return new ResponseEntity<>(response, HttpStatus.valueOf(errorCode.getStatus()));
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);
        final ErrorResponse response = ErrorResponse.of(ErrorCode.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

### Reference
[exception reference](https://github.com/cheese10yun/spring-guide/blob/master/docs/exception-guide.md)