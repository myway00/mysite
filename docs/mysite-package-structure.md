### Mysite04, 05 Package Structure
```bash
mysite04
[src]
    |--- [main]
        |--- [java]
        |   |--- com.poscodx
        |       |--- config
        |       |   |--- app
        |       |   |   |--- DBConfig.java 
        |       |   |   |--- MyBatisConfig.java
        |       |   |--- web
        |       |       |--- FileuploadConfig.java
        |       |       |--- MessageConfig.java
        |       |       |--- MvcConfig.java
        |       |       |--- SecurityConfig.java
        |       |--- mysite
        |           |--- aspect
        |           |--- config
        |           |   |--- AppConfig.java
        |           |   |--- WebConfig.java
        |           |--- controller
        |           |--- exception
        |           |--- interceptor
        |           |--- repository
        |           |--- security
        |           |--- service
        |           |--- vo
        |--- [resource]
            |--- com
            |   |--- poscoict
            |       |--- mysite
            |           |--- config
            |               |--- app
            |                   |--- jdbc.properties
            |                   |--- mybatise
            |                      |--- mappers
            |               |--- web
            |                   |--- fileupload.properties
            |                   |--- messages.properties
            |--- logback.xml
```
     