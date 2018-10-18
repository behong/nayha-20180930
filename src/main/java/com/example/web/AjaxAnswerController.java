package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.domain.Answer;
import com.example.domain.AnswerRepository;
import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.Result;
import com.example.domain.User;

@RestController
@RequestMapping("/api/answers")
public class AjaxAnswerController {
	
	@Autowired
	private QuestionRepository questionRepostory;
	
	@Autowired
	private AnswerRepository answerRepository;

	@PostMapping("/create/{questionId}")
	public Answer create(@PathVariable Long questionId,String contents,HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return null;
		}
		
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		Question question = questionRepostory.findById(questionId).get();
		Answer answer = new Answer(loginUser, question,contents);
		question.addAnswer();
		return answerRepository.save(answer);  
	}
	
	@DeleteMapping("/delete/{q_id}/{id}")
	public Result delete(@PathVariable Long q_id,@PathVariable Long id,HttpSession session) {
		
		if(!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인 해야 합니다");
		}
		
		Answer answer = answerRepository.findById(id).get();
		User loginUser = HttpSessionUtils.getUserFromSession(session);
		
		if( !answer.isSameWriter(loginUser)) {
			return Result.fail("자신의 글만 삭제 가능합니다");
		}
		answerRepository.deleteById(id);
		
		Question question = questionRepostory.findById(q_id).get();
		question.delAnswer();
		
		questionRepostory.save(question);
		
		return Result.ok();
	}

}
