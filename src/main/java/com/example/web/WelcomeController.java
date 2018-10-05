package com.example.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelcomeController {
	
	@GetMapping("/helloworld")
	public String welcome(String name) {
		System.out.println("11111 name : " + name);
		//Model.add
		return "welcome";
	}

}
