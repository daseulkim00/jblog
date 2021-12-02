package com.douzone.jblog.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.douzone.jblog.dto.JsonResult;
import com.douzone.jblog.service.UserService;
import com.douzone.jblog.vo.UserVO;


@RestController("userApiController")
@RequestMapping("/user/api")
public class UserController {
	@Autowired
	private UserService userService;
	
	// @ResponseBody
	// @RequestMapping("/checkemail")
	@GetMapping("/checkid")
	public JsonResult checkeid(@RequestParam(value="id", required=true, defaultValue="") String id) {
		UserVO userVo = userService.getUser(id);
		return JsonResult.success(userVo != null);
	}
}