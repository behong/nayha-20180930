package com.example.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.User;
import com.example.domain.UserRepository;

@Controller
@RequestMapping("/user")
public class UserController {

	// private List<User> users = new ArrayList<User>();

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("session_user");
		return "redirect:/";
	}

	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}

	@PostMapping("/login")
	public String login(String userid, String password, HttpSession session) {

		User user = userRepository.findByUserid(userid);

		if (user == null) {
			System.out.println("LOGIN FAIL !!!");
			return "redirect:/user/loginForm";
		}

		if (!user.matchPassword(password)) {
			System.out.println("LOGIN PASSWORD FAIL !!!");
			return "redirect:/user/loginForm";
		}

		// login 성공
		System.out.println("LOGIN SUCCESS !!!");
		session.setAttribute(HttpSessionUtils.USER_SESSION_KEY, user);

		return "redirect:/";

	}

	@GetMapping("/form")
	public String form() {
		return "user/form";
	}

	@PostMapping("/create")
	public String create(User user) {
		// System.out.println("user " + user );
		// users.add(user);
		userRepository.save(user);
		return "redirect:/user/list";
	}

	@GetMapping("/list")
	public String list(Model model) {
		// model.addAttribute("users",users);
		model.addAttribute("users", userRepository.findAll());
		return "/user/list";
	}

	@GetMapping("/{updateId}/form")
	public String updateForm(@PathVariable Long updateId, Model model, HttpSession session) {
		// findone 애러 JpaRepository findone 미구현..??
		// User user = userRepository.getOne(updateId);

		// 로그인 정보 체크
		if (!HttpSessionUtils.isLoginUser(session)) {
			return "redirect:/user/loginForm";
		}

		// 로그인 정보 캐스팅
		User session_user_cast = HttpSessionUtils.getUserFromSession(session);

		// 로그인 세션과 수정 ID정보가 다르면
		if (!updateId.equals(session_user_cast.getId())) {
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}

		User user = userRepository.findById(updateId).get();

		model.addAttribute("rtn_user", user);
		return "/user/updateForm";
	}

	// @PostMapping("/update")
	@PutMapping("/update")
	public String userUpdate(User update_User ,HttpSession session) {
		
		//로그인 정보 체크
		if(!HttpSessionUtils.isLoginUser(session) ) {
			return "redirect:/user/loginForm";
		}
		
		//로그인 세션 정보 캐스팅
		User session_user_cast = HttpSessionUtils.getUserFromSession(session);
		
		//로그인 세션과 수정 ID정보가 다르면 
		 if(!update_User.getId().equals(session_user_cast.getId())) {
		//if(!user.matchId(session_user_cast.getId()))
			throw new IllegalStateException("자신의 정보만 수정 가능합니다.");
		}
	
	// 전달된 ID로 현재 db 읽어오기 System.out.println("newUser" + update_User );
	User user = userRepository.findById(update_User.getId()).get();

	// User 객체 변경
	user.update(update_User);

	// 변경 DB 반영
	userRepository.save(user);

	return"redirect:/user/list";
}

}
