package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.repository.UserRepository;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.UserVO;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BlogRepository blogRepository;
	
	public void join(UserVO vo) {

		userRepository.insert(vo);
		BlogVO blog = new BlogVO();
		blog.setId(vo.getId());
		blog.setTitle(vo.getName() + "님의 블로그");
		blog.setLogo("/upload/images/2021101661012361.jpg");
		blogRepository.insert(blog);
	}
	

	public UserVO getUser(String id, String password) {
		
		return userRepository.findByIdAndPassword(id, password);
	}
	
	public UserVO getUser(String id) {
		return userRepository.findById(id);
	}

}
