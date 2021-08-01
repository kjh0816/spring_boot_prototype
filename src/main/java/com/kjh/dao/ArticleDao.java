package com.kjh.dao;

import java.util.List;

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


	int getArticlesCount(@Param("boardId") int boardId,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword
			);


	List<Article> getArticles(@Param("boardId") int boardId, @Param("limitFrom") int limitFrom, @Param("limitTake") int limitTake,
			@Param("searchKeywordTypeCode") String searchKeywordTypeCode, @Param("searchKeyword") String searchKeyword
			);

}
