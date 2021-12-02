package com.douzone.jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	public MainController() {
		super();
		System.out.println("SDFsafipjasoidfjasoidfjoasidjfoaisjfoiasjdofjaosfjiosajf");
	}

	@RequestMapping({"", "/main"})
	public String index() {
		
		return "main/index";
	}
	
}
