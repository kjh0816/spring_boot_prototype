package com.kjh.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjh.dao.ArticleDao;
import com.kjh.dto.Article;
import com.kjh.dto.Board;
import com.kjh.dto.ResultData;
import com.kjh.util.Util;

@Service
public class ArticleService {
	
	@Autowired
	private ArticleDao articleDao;

	public ResultData writeArticle(int boardId, int memberId, String title, String body) {
		

		
		articleDao.writeArticle(memberId, boardId, title, body);
		int id = articleDao.getLastInsertId();
		
		return new ResultData("S-1", id + "번 게시물이 생성되었습니다.", "id", id);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
		
	}

	public ResultData delete(int id) {
		
		Article article = articleDao.getArticleById(id);
		if(article == null) {
			return new ResultData("F-1", id + "번 게시물이 존재하지 않습니다.");
		}
		
		articleDao.deleteArticle(id);
		return new ResultData("S-1", id + "번 게시물이 삭제되었습니다.");
		
	}

	public ResultData modify(int id, String title, String body) {
		
		Article article = articleDao.getArticleById(id);
		if(article == null) {
			return new ResultData("F-1", id + "번 게시물이 존재하지 않습니다.");
		}
		
		articleDao.modifyArticle(id, title, body);
		
		return new ResultData("S-1", id + "번 게시물이 수정되었습니다.");
		
	}

	public Board getBoardById(int boardId) {
		return articleDao.getBoardById(boardId);
	
	}

	public int getArticlesCount(int boardId, String searchKeywordTypeCode, String searchKeyword) {
		return articleDao.getArticlesCount(boardId, searchKeywordTypeCode, searchKeyword);
	}

	public List<Article> getArticles(int boardId, int itemsCountInAPage, int page, String searchKeywordTypeCode, String searchKeyword) {
		int limitFrom = (page - 1) * itemsCountInAPage;
		int limitTake = itemsCountInAPage;

		return articleDao.getArticles(boardId, limitFrom, limitTake, searchKeywordTypeCode, searchKeyword);
	}
	
	
	
	
	

}
