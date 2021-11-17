package com.douzone.jblog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.douzone.jblog.repository.CategoryRepository;
import com.douzone.jblog.vo.CategoryVO;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<CategoryVO> getCategory(String blogId){
		return categoryRepository.findAll(blogId);
	}

	public boolean addCategory(CategoryVO vo) {
		return categoryRepository.insert(vo);	
	}
	
	public boolean deleteCategory(Long no) {
		CategoryVO vo = new CategoryVO();
		vo.setNo(no);
		
		return categoryRepository.delete(vo);
	}
}
