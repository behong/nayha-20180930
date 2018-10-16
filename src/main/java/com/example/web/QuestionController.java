package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Question;
import com.example.domain.QuestionRepository;
import com.example.domain.Result;
import com.example.domain.User;

@Controller
@RequestMapping("/questions")
public class QuestionController {

	@Autowired
	private QuestionRepository QuestionRepository;

	@GetMapping("/form")
	public String form(HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}

		return "/qna/form";
	}

	@PostMapping("/create")
	public String create(String title, String contents, HttpSession session) {

		if (!HttpSessionUtils.isLoginUser(session)) {
			return "/user/loginForm";
		}

		User sessionuser = HttpSessionUtils.getUserFromSession(session);
		Question newQuestion = new Question(sessionuser, title, contents);

		QuestionRepository.save(newQuestion);

		return "redirect:/";
	}

	@GetMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {

		model.addAttribute("questions", QuestionRepository.findById(id).get());

		return "/qna/show";
	}

	@GetMapping("{id}/form")
	public String updateForm(@PathVariable Long id, Model model, HttpSession session) {
		Question question = QuestionRepository.findById(id).get();
		Result result = valid(session, question);
		if( !result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		model.addAttribute("questions", question);
		return "/qna/updateForm";

	}

	@PutMapping("/{id}")
	public String update(@PathVariable Long id, String title, String contents, Model model, HttpSession session) {
		Question question = QuestionRepository.findById(id).get();
		Result result = valid(session, question);
		if( !result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		question.update(title, contents);
		QuestionRepository.save(question);
		return String.format("redirect:/questions/show/%d", id);		
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, HttpSession session, Model model) {
		Question question = QuestionRepository.findById(id).get();
		Result result = valid(session, question);
		if( !result.isValid()) {
			model.addAttribute("errorMessage", result.getErrorMessage());
			return "/user/login";
		}
		QuestionRepository.deleteById(id);
		return "redirect:/";
	}
	
	private Result valid(HttpSession session, Question question) {
		if (!HttpSessionUtils.isLoginUser(session)) {
			return Result.fail("로그인이 필요합니다.");
		}
		User loginuser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginuser)) {
			return Result.fail("본인 작성글 수정/삭제 가능합니다.");
		}
		return Result.ok();
	}
	/*
	private boolean hasPermission(HttpSession session, Question question) {
		
		if (!HttpSessionUtils.isLoginUser(session)) {
			throw new IllegalStateException("로그인이 필요합니다.");
		}

		User loginuser = HttpSessionUtils.getUserFromSession(session);
		if (!question.isSameWriter(loginuser)) {
			throw new IllegalStateException("본인 작성글 수정/삭제 가능합니다.");
		}

		return true;
	}
	*/
}
