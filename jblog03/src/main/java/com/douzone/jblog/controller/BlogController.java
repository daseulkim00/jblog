package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.UserVO;
// import com.douzone.mysite.service.FileUploadService;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	@GetMapping("")
	public String main(@AuthUser UserVO vo, Model model) {
		BlogVO blog = blogService.getBlog(vo);
		model.addAttribute("blog", blog);   //JSP ${blog. }이렇게 사용가능 
		
		List<CategoryVO> list = categoryService.getCategory(vo.getId());
		model.addAttribute("list",list);
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
		
		try{
			String logo = fileUploadService.restoreImage(file);
			blog.setLogo(logo);
		}catch (FileUploadException ex) {
			   System.out.println("error: " + ex);
		}
		blogService.update(blog);
		servletContext.setAttribute("blog", blog);
		
		return "redirect:/blog";
		
	}
	
	//////////////////////////category///////////////////////////////////////
	//list
	@RequestMapping("/blog-admin-category")
	public String category( @AuthUser UserVO vo ,Model model) {
		List<CategoryVO> list = categoryService.getCategory(vo.getId());
		model.addAttribute("list",list);
		return "blog/blog-admin-category";
	}
	
	// add
	@RequestMapping(value ="/blog-admin-category/add", method = RequestMethod.POST )
	public String add(@AuthUser UserVO uservo, CategoryVO vo) {
		vo.setBlogId(uservo.getId());  // 로그인하고있는 아이디를 셋팅해준다.
		categoryService.addCategory(vo);
		
		return "redirect:/blog/blog-admin-category";
	}
	
	//delete
	@RequestMapping("delete/{no}")
	public String delete(@PathVariable("no") Long no) {
		categoryService.deleteCategory(no);
		return "redirect:/blog/blog-admin-category";
		
	}
	
	
	
	
	
	
	
	/////////////////write////////////////////////////
	

	
	@RequestMapping("/blog-admin-write")
	public String write() {
		return "blog/blog-admin-write";
	}
	
	
}
