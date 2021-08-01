package com.kjh.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@AllArgsConstructor
@NoArgsConstructor
@Data
public class Board {
	private int id;
	private String regDate;
	private String updateDate;
	private String name;
	private String code;
	private boolean blindStatus;
	private String blindDate;
	private boolean delStatus;
	private String delDate;
	private int hitCount;
	private int repliesCount;
	private int likeCount;
	private int dislikeCount;
	
}
