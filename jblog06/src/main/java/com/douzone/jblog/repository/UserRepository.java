package com.douzone.jblog.repository;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.douzone.jblog.vo.UserVO;

@Repository
public class UserRepository {

	@Autowired
	private SqlSession sqlsession;
	
	public boolean insert(UserVO vo) {
		return sqlsession.insert("user.insert",vo) == 1;
	}

	public UserVO findByIdAndPassword(String id, String password) {
		Map<String, String> map = new HashMap<>();
		map.put("i", id);
		map.put("p", password);
		
		return sqlsession.selectOne("user.findByIdAndPassword", map);
	}

	public UserVO findById(String id) {
		return sqlsession.selectOne("user.findById", id);
	}

}
