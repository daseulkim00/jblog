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
	
	public List<PostVO> findAll(Long no) {
		return sqlSession.selectList("post.findAll",no);
	}
	
	public PostVO findContents(Long no) {
		return sqlSession.selectOne("post.findContents",no);
	}
}
