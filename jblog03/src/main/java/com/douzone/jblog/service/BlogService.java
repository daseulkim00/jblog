package com.douzone.jblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.BlogRepository;
import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.UserVO;

@Service
public class BlogService {
	@Autowired
	private BlogRepository blogRepository;
	
	
	public BlogVO getBlog(String id) {
		
		return blogRepository.find(id);
	}

	public boolean update(BlogVO blog) {
		return blogRepository.update(blog);
	}

	
}
