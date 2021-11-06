package com.douzone.jblog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVO;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(value = "/join" )
	public String join(@ModelAttribute UserVO vo){
		return "user/join";
	}
	
	@PostMapping(value = "/join")
	public String join(UserVO vo, Model model) {
		userService.join(vo);
		return "redirect:/user/joinsuccess";
		
	}
	
	@RequestMapping("/joinsuccess" )
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@GetMapping("/login" )
	public String login() {
		return "user/login";
	}
	
	
	
	
	
	
}
