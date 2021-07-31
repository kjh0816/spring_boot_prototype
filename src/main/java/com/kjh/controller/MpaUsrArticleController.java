package com.kjh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.dto.Article;
import com.kjh.dto.ResultData;
import com.kjh.service.ArticleService;
import com.kjh.util.Util;

@Controller
public class MpaUsrArticleController {
	
	@Autowired
	private ArticleService articleService;

	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		
		if(Util.isEmpty(title)) {
			return new ResultData("F-1", "제목 입력");
		}
		if(Util.isEmpty(body)) {
			return new ResultData("F-2", "내용 입력");
		}
		
		
//		성공인 경우, ResultData 형식의 return 값을 Service에 위임하는 형태 : 일관성이 있을까
		return articleService.writeArticle(title, body);
		
		
		
		
	}
	
	
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "게시물 번호 입력");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-2", id + "번 게시물이 존재하지 않습니다.");
		}
		
		return new ResultData("S-1", id + "번 게시물입니다.", "article", article);
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData deleteArticle(int id) {
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "게시물 번호 입력");
		}
		
		ResultData rd = articleService.delete(id);
		
		return rd;
		
		
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData modifyArticle(int id, String title, String body) {
		if(Util.isEmpty(id)) {
			return new ResultData("F-1", "게시물 번호 입력");
		}
		if(Util.isEmpty(title)) {
			return new ResultData("F-2", "제목 입력");
		}
		if(Util.isEmpty(body)) {
			return new ResultData("F-3", "내용 입력");
		}
		
		ResultData rd = articleService.modify(id, title, body);
		
		return rd;
	}
	
	

}
