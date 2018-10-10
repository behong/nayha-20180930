package com.example.web;

import javax.servlet.http.HttpSession;

import com.example.domain.User;

public class HttpSessionUtils {
	
	public static final String USER_SESSION_KEY ="session_user";
	
	public static boolean isLoginUser(HttpSession session) {
		
		Object session_user = session.getAttribute(USER_SESSION_KEY);
		
		if(session_user == null) {
			return false;
		}
		
		return true;
	}
	
	public static User getUserFromSession(HttpSession session) {
		
		if(!isLoginUser(session)) {
			return null;
		}
		return (User) session.getAttribute(USER_SESSION_KEY);
	}

}
