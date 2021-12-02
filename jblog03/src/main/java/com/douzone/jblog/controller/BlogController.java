package com.douzone.jblog.controller;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.exception.FileUploadException;
import com.douzone.jblog.security.AuthUser;
import com.douzone.jblog.service.BlogService;
import com.douzone.jblog.service.CategoryService;
import com.douzone.jblog.service.FileUploadService;
import com.douzone.jblog.service.PostService;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.CategoryVO;
import com.douzone.jblog.vo.PostVO;
import com.douzone.jblog.vo.UserVO;
// import com.douzone.mysite.service.FileUploadService;

@Controller
@RequestMapping("/blog/{id}")
public class BlogController {

	@Autowired
	private BlogService blogService;
	@Autowired
	private CategoryService categoryService;

	@Autowired
	private FileUploadService fileUploadService;

	@Autowired
	private ServletContext servletContext;

	@Autowired
	private PostService postService;

	@GetMapping({ "", "/{no}", "/{no}/{pno}" })
	public String main(@PathVariable(value = "no", required = false) Long no,
			@PathVariable(value = "pno", required = false) Long pno, @PathVariable("id") String id, Model model) {

		BlogVO blog = blogService.getBlog(id);
		model.addAttribute("blog", blog); // JSP ${blog. }이렇게 사용가능

		List<CategoryVO> list = categoryService.getCategory(id);
		model.addAttribute("list", list);
		if (no == null && list.size() != 0) {
			no = list.get(0).getNo();
		}

		List<PostVO> post = postService.getPost(no);
		if (pno == null && post.size() != 0) {
			pno = post.get(0).getNo();
		}

		PostVO pvo = postService.getContents(pno);

		model.addAttribute("pvo", pvo);
		model.addAttribute("post", post);

		return "blog/blog-main";
	}

	@GetMapping("/blog-admin-basic")
	public String blogadmin(@AuthUser UserVO vo, Model model) {
		BlogVO blog = blogService.getBlog(vo.getId());
		model.addAttribute("blog", blog);
		return "blog/blog-admin-basic";
	}

	@PostMapping("/blog-admin-basic")
	public String blogadmin(@PathVariable("id") String id, BlogVO blog, @RequestParam("file") MultipartFile file) {

		try {
			String logo = fileUploadService.restoreImage(file);
			blog.setLogo(logo);
		} catch (FileUploadException ex) {
			System.out.println("error: " + ex);
		}
		blogService.update(blog);
		servletContext.setAttribute("blog", blog);

		return "redirect:/blog/" + id;

	}

	////////////////////////// category///////////////////////////////////////

	// api
	@GetMapping("/catelist")
	@ResponseBody
	public JsonResult catelist(@PathVariable("id") String id) {
		List<CategoryVO> list = categoryService.getCategory(id);

		return JsonResult.success(list);
	}

	@PostMapping("/addCategory")
	@ResponseBody
	public JsonResult cateadd(@PathVariable("id") String id, @RequestBody CategoryVO vo) {
		vo.setBlogId(id);
		categoryService.addCategory(vo);

		return JsonResult.success(vo);
	}

	@DeleteMapping("/deletecate/{no}")
	@ResponseBody
	public JsonResult catedelete(@PathVariable("no") Long no) {
		categoryService.deleteCategory(no);
		return JsonResult.success(no);
	}
	////////////////////////////

	// list
	@RequestMapping("/blog-admin-category")
	public String category(@AuthUser UserVO vo, Model model) {
		BlogVO blog = blogService.getBlog(vo.getId());
		model.addAttribute("blog", blog);

		List<CategoryVO> list = categoryService.getCategory(vo.getId());
		model.addAttribute("list", list);
		return "blog/blog-admin-category";
	}

	// add
	@RequestMapping(value = "/blog-admin-category/add", method = RequestMethod.POST)
	public String add(@PathVariable("id") String id, @AuthUser UserVO uservo, CategoryVO vo) {
		vo.setBlogId(uservo.getId()); // 로그인하고있는 아이디를 셋팅해준다.
		categoryService.addCategory(vo);

		return "redirect:/blog/" + id + "/blog-admin-category";
	}

	// delete
	@RequestMapping("delete/{no}")
	public String delete(@PathVariable("no") Long no, @PathVariable("id") String id) {
		categoryService.deleteCategory(no);
		return "redirect:/blog/" + id + "/blog-admin-category";

	}

	///////////////// write////////////////////////////

	@GetMapping("/blog-admin-write")
	public String write(@AuthUser UserVO vo, Model model) {
		BlogVO blog = blogService.getBlog(vo.getId());
		model.addAttribute("blog", blog);

		List<CategoryVO> list = categoryService.getCategory(vo.getId());
		model.addAttribute("list", list);
		return "blog/blog-admin-write";
	}

	@PostMapping("/blog-admin-write")
	public String write(@PathVariable("id") String id, @AuthUser UserVO uservo, PostVO postvo) {
		postService.writePost(postvo);
		return "redirect:/blog/" + id + "/blog-admin-write";
	}

}
