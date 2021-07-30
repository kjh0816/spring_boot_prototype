package com.kjh.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kjh.dto.Article;
import com.kjh.dto.ResultData;
import com.kjh.util.Util;

@Component
public class ArticleDao {
	
	private int articleLastId;
	private List<Article> articles;
	
	public ArticleDao() {
		articleLastId = 0;
		articles = new ArrayList<>();
		makeTestArticles();
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


	public int writeArticle(String title, String body) {
		
		
		articleLastId++;
		int id = articleLastId;
		String regDate = Util.getNowDateStr();
		String updateDate = Util.getNowDateStr();
		
		Article article = new Article(id, regDate, updateDate, title, body);
		articles.add(article);
		
		return id;
		
		
		
	}


	public Article getArticleById(int id) {
		for(Article article : articles) {
			if(article.getId() == id) {
				return article;
			}
		}
		return null;
	}

}
