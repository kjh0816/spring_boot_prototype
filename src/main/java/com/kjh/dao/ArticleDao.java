package com.kjh.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kjh.dto.Article;
import com.kjh.dto.Board;

@Mapper
public interface ArticleDao {
	
	
	
	boolean modifyArticle(@Param("id") int id, @Param("title") String title, @Param("body") String body);
		
	
	void deleteArticle(@Param("id") int id);
	

	void writeArticle(@Param("memberId") int memberId, @Param("boardId") int boardId, @Param("title") String title, @Param("body") String body);


	Article getArticleById(@Param("id") int id);


	int getLastInsertId();


	Board getBoardById(int boardId);


	int getArticlesCount(@Param("boardId") int boardId);

}
