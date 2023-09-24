
| 어노테이션            | 설명                                                                                                                                                                           |
|-----------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `@RequestMapping`     | 핸들러 매핑을 설정하는 어노테이션으로, 요청 URL과 컨트롤러 메소드를 매핑합니다.                                                                                                       |
| `@RequestParam`      | HTTP 요청 파라미터를 메소드 파라미터에 매핑하는 어노테이션입니다. 파라미터가 반드시 있어야 하며, 없으면 HTTP 400 - Bad Request를 받습니다.                                    |
| `@PathVariable`      | URL 패스 기반 파라미터를 메소드 파라미터에 매핑하는 어노테이션입니다.                                                                                                            |
| `@ModelAttribute`   | 모델 객체를 생성하고 메소드 파라미터에 바인딩하는 데 사용되며, 주로 폼 데이터를 처리할 때 사용됩니다.                                                                        |

#### `@RequestMapping` 어노테이션

```java
@Controller
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/greet")
    public String greet() {
        return "Hello, World!";
    }
}
```

- **설명:** `/hello/greet` URL이 `greet` 메소드와 매핑되어 "Hello, World!" 문자열을 반환합니다.

#### `@RequestParam` 어노테이션

```java
@GetMapping("/user")
public String getUserInfo(@RequestParam("id") int userId) {
    // userId를 사용하여 사용자 정보를 가져옴
    return "User Info for ID: " + userId;
}
```

- **설명:** `/user?id=123`와 같은 요청에서 `id` 파라미터를 메소드의 `userId` 매개변수에 매핑합니다.

#### `@PathVariable` 어노테이션

```java
@GetMapping("/user/{id}")
public String getUserInfo(@PathVariable("id") int userId) {
    // URL 경로에서 추출한 userId를 사용하여 사용자 정보를 가져옴
    return "User Info for ID: " + userId;
}
```

- **설명:** `/user/123`와 같은 요청에서 URL 경로에서 `id` 값을 추출하여 `userId` 매개변수에 매핑합니다.

#### `@ModelAttribute` 어노테이션

```java
@PostMapping("/addUser")
public String addUser(@ModelAttribute User user) {
    // User 객체에 폼 데이터를 바인딩하여 새 사용자를 추가
    return "User added: " + user.getName();
}
```

- **설명:** 폼 데이터를 바인딩하여 `User` 객체를 생성하고 이를 사용하여 새 사용자를 추가합니다.
