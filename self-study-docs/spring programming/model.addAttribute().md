: @RequestMapping이 부여된 메서드들은 view를 생성하는 역할, 즉 Controller메서드.
: Controller는 Model을 이용해 데이터를 갖고오고, view에 데이터를 넘겨 적절한 view를 생성하는 역할.


< Controller에서 사용자에게 응답할 View를 생성할 때 Model을 이용하여 **View에 데이터를 전달하는 방법** >

```java
model.addAttribute(String name, Object value);
```

: value 객체를 name 이름으로 추가
: 뷰 코드에서는 name으로 지정한 이름을 통해서 value를 사용