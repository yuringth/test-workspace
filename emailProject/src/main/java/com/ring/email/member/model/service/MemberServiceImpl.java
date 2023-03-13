package com.ring.email.member.model.service;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ring.email.member.model.dao.MemberDao;
import com.ring.email.member.model.vo.CertVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private SqlSessionTemplate sqlSession; // 기존의 mybatis의 sqlSession 대체

	
	@Override
	public void sendMail(CertVO certVo) {
		memberDao.insertSecret(sqlSession, certVo);
	}

	@Override
	public boolean validate(CertVO certVo) {
		return memberDao.validate(sqlSession, certVo);
	}
	
}


