package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.PostRepository;
import com.douzone.jblog.vo.PostVO;

@Service
public class PostService {
	@Autowired 
	private PostRepository postRepository;
	
	public boolean writePost(PostVO vo){
		return postRepository.insert(vo);
	}
	
	public List<PostVO> getPost(Long no){
	 return postRepository.findAll(no);
	}

	public PostVO getContents(Long pno) {
		return postRepository.findContents(pno);
	}
	
	
	
}
