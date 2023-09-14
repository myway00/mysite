# 구조
```
src
	|--- main
		|--- java
			|--- com.poscodx.web.mvc
				|--- ActionFactory
				|--- Action		
			|--- com.poscodx.web.util
				|--- MvcUtil
			
			|--- com.poscodx.mysite.contorller
				|--- MainController
				|--- UserController : 객체 지향 
				|--- GuestbookController : 방명록Controller
			|--- com.poscodx.mysite.dao
				|--- GuestbookDao.java
				|--- UserDao.java
			|--- com.poscodx.mysite.vo
				|--- GuestbookVo.java
				|--- UserVo.java
				
			|--- com.poscodx.mysite.mvc.main
				|--- MainActionFactory.java
				|--- MainAction.java
			|--- com.poscodx.mysite.mvc.user
				|--- JoinAction.java
				|--- JoinFormAction.java
				|--- JoinSuccessAction.java
				|--- LoginAction.java
				|--- LoginFormAction.java
				|--- LoginOutAction.java
				|--- UpdateAction.java
				|--- UpdateFormAction.java
				|--- updateSuccessAction.java
				|--- UserActionFactory.java
			|--- com.poscodx.mysite.mvc.guestbook
				|--- AddAction.java
				|--- DeleteAction.java
				|--- DeleteFormAction.java
				|--- GuestbookActionFactory.java
				|--- IndexAction.java
					
			|--- com.poscodx.web.mvc
				|--- ActionFactory.java
				|--- Action.java
			|--- com.poscodx.web.filter
				|--- readme.md
				|--- EncodingFilter.java
			|--- com.poscodx.web.util
				|--- MvcUtil.java
					
		|--- resources
		|--- webapp
			|--- assets
				|--- css
				|--- images
				|--- js
			|--- WEB-INE
				|--- views 
					|--- board
					|--- uestbook
					|--- main 
					|--- user
					|--- includes
					|--- web.xml


```
