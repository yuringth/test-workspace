package com.ring.email.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.ring.email.member.model.vo.CertVO;

@Repository
public class MemberDao {


	public void insertSecret(SqlSessionTemplate sqlSession, CertVO certVo) {
		sqlSession.insert("memberMapper.insertSecret", certVo);
	}

	public boolean validate(SqlSessionTemplate sqlSession, CertVO certVo) {
		CertVO result = sqlSession.selectOne("memberMapper.validate", certVo);
		if(result != null) {
			sqlSession.delete("memberMapper.remove", certVo);
		}
		return result != null;
	}
	
	
}
