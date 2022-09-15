package com.cos.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.blog.dto.ResponseDto;
import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.service.UserService;

@RestController //데이터만 리턴
public class UserApiController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user) { // 직접 받는건 username, password, email
		System.out.println("UserApiController : save 호출됨");
		//실제 DB에 insert를 하고 아래에 return이 되면 된다.
		user.setRole(RoleType.USER); // 시간은 알아서 넣어지니까 제외
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1); //자바 오브젝트를 JSON으로 변환해서 리턴
	}
	
	@PostMapping("/api/user/login")
	public ResponseDto<Integer> login(@RequestBody User user, HttpSession session){
		System.out.println("UserApiController : login 호출됨");
	 	User principal = userService.로그인(user); //principal(접근주체)
	 	
	 	if(principal != null) {
	 		session.setAttribute("principal", principal);
	 	}
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);  // 1이면 로그인 정상적 요청됨
	}
}




