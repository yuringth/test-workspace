package com.ring.email.member.model.service;

import org.springframework.stereotype.Service;

import com.ring.email.member.model.vo.CertVO;

@Service
public interface MemberService {
	

	void sendMail(CertVO certVo);

	boolean validate(CertVO certVo);
	

}
