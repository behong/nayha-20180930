package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Answer;
import com.example.domain.AnswerRepository;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.User;

@Controller
@RequestMapping("/answers")
public class AnswerController {
	
	@Autowired
	private QuestionRepository questionRepostory;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@PostMapping("/create/{questionId}")
	public String create(@PathVariable Long questionId,String contents,HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return "/user/login";
		}
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		
		Question question = questionRepostory.findById(questionId).get();
		
		Answer answer = new Answer(loginUser, question,contents);
		answerRepository.save(answer);
		
		return  String.format("redirect:/questions/show/%d", questionId );
	}
	
	@GetMapping("/test")
	public String test() {
		return "/";
	}

}
