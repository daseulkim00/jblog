package com.douzone.jblog.controller;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.UserVO;
// import com.douzone.mysite.service.FileUploadService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("")
	public String main(@AuthUser UserVO vo, Model model) {
		BlogVO blog = blogService.getBlog(vo);
		model.addAttribute("blog", blog);   //JSP ${blog. }이렇게 사용가능 
		return "blog/blog-main";
	}
	
	
	@GetMapping("/blog-admin-basic")
	public String blogadmin(@AuthUser UserVO vo, Model model) {
		BlogVO blog = blogService.getBlog(vo);
		model.addAttribute("blog", blog);
		return "blog/blog-admin-basic";
	}
	
	
	@PostMapping("/blog-admin-basic")
	public String blogadmin(BlogVO blog, @RequestParam("file") MultipartFile file) {
		
		String logo = fileUploadService.restoreImage(file);
		blog.setLogo(logo);
		System.out.println(blog);
		blogService.update(blog);
		servletContext.setAttribute("blog", blog);
		
		return "redirect:/blog/blog-main";
		
	}
	
	
	
}
