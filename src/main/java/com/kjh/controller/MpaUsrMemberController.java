package com.kjh.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kjh.dto.Member;
import com.kjh.service.MemberService;
import com.kjh.util.Util;

@Controller
public class MpaUsrMemberController {
	
	@Autowired
	MemberService memberService;
	
	
	@RequestMapping("/mpaUsr/member/join")
	public String showJoin() {
		
		return "/mpaUsr/member/join";
	}
	
	
	@RequestMapping("/mpaUsr/member/doJoin")
	public String doJoin(HttpServletRequest req, String loginId, String loginPw,
			String name, String nickname, String cellphoneNo, String email) {
		
		Member member = memberService.getMemberByLoginId(loginId);
		if(member != null) {
			return Util.msgAndBack(req, loginId + "는(은) 이미 사용 중인 로그인 아이디입니다.");
		}
		
		member = memberService.geMemberByNameEmail(name, email);
		if(member != null) {
			return Util.msgAndBack(req, String.format("%s님은 이미 %s 이메일로 가입하셨습니다.", name, email));
		}
		
		
		return "/mpaUsr/member/login";
	}
	
	
	@RequestMapping("/mpaUsr/member/login")
	public String showLogin() {
		
		
		return "/mpaUsr/member/login";
	}
}
