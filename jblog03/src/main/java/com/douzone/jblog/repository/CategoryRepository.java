package com.douzone.jblog.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.CategoryVO;

@Repository
public class CategoryRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public List<CategoryVO> findAll(String blogId){
		return sqlSession.selectList("category.findAll",blogId);
	}

	public boolean insert(CategoryVO vo) {
		int count = sqlSession.insert("category.insert" ,vo);
		return count ==1;
	}
	
}
