package com.douzone.jblog.repository;

import java.util.List;

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
	
	public List<PostVO> findAll(String blogId) {
		return sqlSession.selectList("post.findAll",blogId);
	}
}
