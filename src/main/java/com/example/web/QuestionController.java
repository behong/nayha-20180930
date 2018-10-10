package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {
	
	@Autowired
	private QuestionRepository QuestionRepository;
	
	
	@GetMapping("/form")
	public String form(HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		
		return "/qna/form";
	}
	
	@PostMapping("/create")
	public String create(String title, String contents,HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}
		
		User sessionuser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionuser.getUserid(), title, contents);
		
		QuestionRepository.save(newQuestion);
		
		return "redirect:/";
	}
	

}
