package com.cos.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {

	// http://localhost:8000/blog/temp/home
	@GetMapping("/temp/home")
	public String tempHome() {
		System.out.println("tempHome()");
		// 파일 리턴 기본경로 : src/main/resources/static (에 / 없이 바로 html.html 붙여짐)
		// 리턴명 : /home.html
		// 풀경로 : src/main/resources/static/home.html
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String templmg() {
		return "/알로에.jpg";
	}
	
	@GetMapping("/temp/jsp")
	public String tempJsp() {
		// prefix(맨앞에 붙이는 경로경위): /WEB-INF/views/
		// suffix(맨뒤에 붙이는 경로경위): .jsp

		//예를 들어
		//return "/test.jsp";
		//풀네임 :  /WEB-INF/views//test.jsp.jsp
		
		return "test";
		// "/test" 했는데 정상작동. 스프링이 알아서 처리해주긴 함.
	}
}
