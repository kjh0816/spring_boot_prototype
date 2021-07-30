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

	public int writeArticle(String title, String body) {
		return articleDao.writeArticle(title, body);
	}

	public Article getArticleById(int id) {
		return articleDao.getArticleById(id);
	}

	public ResultData delete(int id) {
		return articleDao.delete(id);
	}

	public ResultData modify(int id, String title, String body) {
		return articleDao.modify(id, title, body);
	}
	
	
	
	
	

}
