package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.BlogVO;
import com.douzone.jblog.vo.UserVO;

@Repository
public class BlogRepository {
	 @Autowired
	 private SqlSession sqlSession;
	 
	 public BlogVO find(UserVO vo) {
		 
		 return sqlSession.selectOne("blog.find", vo);  //blog.xml에서 blog에서 find
	
	 }

	 public boolean update(BlogVO vo) {
			int count = sqlSession.update("blog.update", vo);
			return count == 1;  
		}
}
