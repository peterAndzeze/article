package com.article.recommend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class InitController {

	@RequestMapping("/")
	public String start(Model model) {
		return "index";
	}

	@RequestMapping("login")
	public String login(HttpServletRequest request,String userName,String password) {
		if(null==userName || "".equals(userName)) {
			return "index";
		}
		if (userName.equals("admin") && userName.equals(password)) {
			request.getSession().setAttribute("session_user", userName + password);
			request.getSession().setMaxInactiveInterval(60);
			return "main";
		} else {
			return "index";
		}
	}

}
