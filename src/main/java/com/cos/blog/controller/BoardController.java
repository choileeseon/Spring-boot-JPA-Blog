package com.cos.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BoardController {

	@GetMapping({"","/"})
	public String index() {
		// prefix: /WEB-INF/views/ 와 suffix: .jsp 가 합쳐져서 
		// /WEB-INF/views/index.jsp
		return "index";
	}
}
