package com.kjh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.dao.ArticleDao;
import com.kjh.dto.Article;
import com.kjh.dto.ResultData;
import com.kjh.util.Util;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	public ResultData writeArticle(String title, String body) {
		
//		임시 데이터
		int memberId = 1;
		int boardId = 1;
		
		articleDao.writeArticle(memberId, boardId, title, body);
		int id = articleDao.getLastInsertId();
		
		return new ResultData("S-1", id + "번 게시물이 생성되었습니다.", "id", id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
		
	}

	public void delete(int id) {
		articleDao.deleteArticle(id);
	}

	public ResultData modify(int id, String title, String body) {
		Article article = getArticleById("")
	}
	
	
	
	
	

}
