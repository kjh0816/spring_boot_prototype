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
		makeTestArticles();
	}
	
	
	@RequestMapping("/mpaUsr/article/doWrite")
	@ResponseBody
	public ResultData doWrite(String title, String body) {
		
		int id = writeArticle(title, body);
		
		Article article = getArticleById(id);
		
		return new ResultData("S-1", id + "번 게시물이 생성되었습니다.", "article", article);
	}
	
	
	@RequestMapping("/mpaUsr/article/getArticle")
	@ResponseBody
	public ResultData getArticle(int id) {
		
		Article article = getArticleById(id);
		
		if(article == null) {
			return new ResultData("F-1", id + "번 게시물이 존재하지 않습니다.");
		}
		
		return new ResultData("S-1", id + "번 게시물입니다.", "article", article);
	}
	
	@RequestMapping("/mpaUsr/article/doDelete")
	@ResponseBody
	public ResultData deleteArticle(int id) {
		
		ResultData rd = delete(id);
		
		return rd;
		
		
	}
	
	@RequestMapping("/mpaUsr/article/doModify")
	@ResponseBody
	public ResultData modifyArticle(int id, String title, String body) {
		
		ResultData rd = modify(id, title, body);
		
		return rd;
	}
	
	public ResultData modify(int id, String title, String body) {
		for(Article article : articles) {
			if(article.getId() == id) {
				article.setUpdateDate(Util.getNowDateStr());
				article.setTitle(title);
				article.setBody(body);
				return new ResultData("S-1", id + "번 게시물이 수정되었습니다.", "article", article);
			}
		}
		
		return new ResultData("F-1", id + "번 게시물이 존재하지 않습니다.");
	}
	
	public ResultData delete(int id) {
		
		for(Article article : articles) {
			if(article.getId() == id) {
				articles.remove(article);
				return new ResultData("S-1", id + "번 게시물을 삭제했습니다.");
			}
		}
		
		return new ResultData("F-1", id + "번 게시물이 존재하지 않습니다.");
	}
	
	private void makeTestArticles() {
		for(int i = 0; i < 3; i++) {
			writeArticle("제목"+i+1, "내용"+i+1);
		}
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
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}

}
