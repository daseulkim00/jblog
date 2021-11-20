package com.douzone.jblog.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.PostVO;

@Repository
public class PostRepository {
	
	@Autowired
	private SqlSession sqlSession;
	
	public boolean insert(PostVO vo) {
		int count = sqlSession.insert("post.insert",vo);
		return count == 1;
	}
}
