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
	
	//private List<User> users = new ArrayList<User>();
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}	
	
	@GetMapping("/loginForm")
	public String loginForm() {
		return "user/login";
	}
	
	@PostMapping("/login")
	public String login(String userid, String password,HttpSession session) {

		User user = userRepository.findByUserid(userid);

		if (user == null) {
			System.out.println("LOGIN FAIL !!!");
			return "redirect:/user/loginForm";
		}
		
		if (!password.equals(user.getPassword())) {
			System.out.println("LOGIN FAIL !!!");
			return "redirect:/user/loginForm";
		}

		// login 성공
		System.out.println("LOGIN SUCCESS !!!");
		session.setAttribute("user", user);
		
		return "redirect:/";

	}
	
	@GetMapping("/form")
	public String form() {
		return "user/form";
	}
	
	@PostMapping("/create")
	public String create(User user) {
		//System.out.println("user " + user );
		//users.add(user);
		userRepository.save(user);
		return "redirect:/user/list";
	}
	
	@GetMapping("/list")
	public String list(Model model) {
		//model.addAttribute("users",users);
		model.addAttribute("users",userRepository.findAll());
		return "/user/list";
	}
	
	@GetMapping("/{updateId}/form")
	public String updateForm(@PathVariable Long updateId,Model model) {
		//findone 애러 JpaRepository findone 미구현..??
		//User user = userRepository.getOne(updateId);
		User user = userRepository.findById(updateId).get();
		//System.out.println("rtn_user " + user );
		model.addAttribute("rtn_user", user);
		return "/user/updateForm";
	}
	
	//@PostMapping("/update")
	@PutMapping("/update")
	public String userUpdate(User newUser) {
		System.out.println("newUser" + newUser );
		//전달된 ID로  현재 db 읽어오기
		User user = userRepository.findById(newUser.getId()).get();
		//변경
		user.update(newUser);
		//변경 DB 반영
		userRepository.save(user);
		return "redirect:/user/list";
	}

}
