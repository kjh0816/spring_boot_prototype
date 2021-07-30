package com.kjh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.dto.Article;
import com.kjh.dto.ResultData;
import com.kjh.util.Util;

@Controller
public class MpaUsrArticleController {
	
	private int articleLastId;
	private List<Article> articles;
	
	
	public MpaUsrArticleController(){
		articleLastId = 0;
		articles = new ArrayList<>();
	}
	
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		
		int id = writeArticle(title, body);
		
		Article article = getArticleById(id);
	}


	private int writeArticle(String title, String body) {
		articleLastId++;
		int id = articleLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);
		
		return id;
		
		
	}


	private Article getArticleById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

}
