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
		List<CategoryVO> categorylist = categoryRepository.findAll(blogId);
		List<CategoryVO> count = categoryRepository.findCount(blogId);
		
		for(int i=0; i < categorylist.size(); i++ ){
			for(int j=0; j<count.size(); j++) {
				if(categorylist.get(i).getNo()==count.get(j).getNo()) {
					categorylist.get(i).setPostcount(count.get(j).getPostcount());
					break;
				} 
			}
		}
		return categorylist ;
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
