package com.kjh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.dao.MemberDao;
import com.kjh.dto.Member;

@Service
public class MemberService {
	
	@Autowired
	MemberDao memberDao;
	
	public Member getMemberByLoginId(String loginId) {
		return memberDao.getMemberByLoginId(loginId);
	}

	public Member geMemberByNameEmail(String name, String email) {
		return memberDao.getMemberByNameEmail(name, email);
	}

}
