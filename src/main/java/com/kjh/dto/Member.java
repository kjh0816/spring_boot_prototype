package com.kjh.dto;

import lombok.Data;

@Data
public class Member {
	private int id;
	private String regDate;
	private String updateDate;
	private String loginId;
	private String loginPw;
	private String name;
	private String nickname;
	private String cellphoneNo;
	private String email;

}
