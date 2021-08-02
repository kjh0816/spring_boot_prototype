package com.kjh.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjh.dto.Article;
import com.kjh.dto.Board;
import com.kjh.dto.ResultData;
import com.kjh.dto.Rq;
import com.kjh.service.ArticleService;
import com.kjh.util.Util;

@Controller
public class MpaUsrArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	
	
	@RequestMapping("/mpaUsr/article/write")
	public String showWrite(HttpServletRequest req, int boardId) {
		
		Board board = articleService.getBoardById(boardId);
		if(board == null) {
			return Util.msgAndBack(req, boardId + "번 게시판이 존재하지 않습니다.");
		}
		
		req.setAttribute("board", board);
		
		return "/mpaUsr/article/write";
	}
	
	@RequestMapping("/mpaUsr/article/doWrite")
	
	public String doWrite(HttpServletRequest req, int boardId, String title, String body) {
		
		if(Util.isEmpty(title)) {
			return Util.msgAndBack(req, "제목 입력");
		}
		if(Util.isEmpty(body)) {
			return Util.msgAndBack(req, "내용 입력");
		}
		
		
		Rq rq = (Rq) req.getAttribute("rq");
		
//		int memberId = rq.getLoginedMemberId();
//		임시 데이터
		int memberId = 1;
		
		ResultData writeArticleRd = articleService.writeArticle(boardId, memberId, title, body);
		
//		필요없어서 뺐음.
		
//		if(writeArticleRd.isFail()) {
//			return Util.msgAndBack(req, writeArticleRd.getMsg());
//		}
		
		String replaceUri = "detail?id=" + writeArticleRd.getBody().get("id");
		
		return Util.msgAndReplace(req, writeArticleRd.getMsg(), replaceUri);
		
		
	}
	
	
	@RequestMapping("/mpaUsr/article/detail")
	public String getArticle(HttpServletRequest req, int id) {
		if(Util.isEmpty(id)) {
			return Util.msgAndBack(req, "게시물 번호 입력");
		}
		
		Article article = articleService.getArticleById(id);
		
		if(article == null) {
			return Util.msgAndBack(req, id + "번 게시물이 존재하지 않습니다.");
		}
		
		req.setAttribute("article", article);
		
		return "/mpaUsr/article/detail";
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
	
	
	@RequestMapping("/mpaUsr/article/list")
	public String showList(HttpServletRequest req, Integer boardId, @RequestParam(defaultValue = "1") int page,
			String searchKeywordTypeCode, String searchKeyword) {
		
		if(Util.isEmpty(boardId)) {
			return Util.msgAndReplace(req, "끼익", "/mpaUsr/home/main");
		}
		
		
		Board board = articleService.getBoardById(boardId);
		if( board == null ) {
			return Util.msgAndReplace(req, "끼익", "/mpaUsr/home/main");
		}
		
		if(Util.isEmpty(searchKeywordTypeCode)) {
			searchKeywordTypeCode = "titleAndBody";
		}
		
		
		
//		게시물 개수
		int totalItemsCount = articleService.getArticlesCount(boardId, searchKeywordTypeCode, searchKeyword);
		
//		게시물 개수를 알아야 현재 페이지에서 보여줘야할 게시물도 조회할 수 있기 때문에 위에 Count를 세는 함수는 필요하다.
		
//		페이징 (시작)
		
//		한 페이지에 보여줄 게시물 수
		int itemsCountInAPage = 10;
		
//		총 페이지 수
		
		int totalPage = (int) Math.ceil(totalItemsCount / (double) itemsCountInAPage);
		
//		현재 페이지에서 보여줄 게시물
		List<Article> articles = articleService.getArticles(boardId, itemsCountInAPage, page, searchKeywordTypeCode, searchKeyword);
		
		
		req.setAttribute("board", board);
		req.setAttribute("totalItemsCount", totalItemsCount);
		req.setAttribute("page", page);
		req.setAttribute("totalPage", totalPage);
		req.setAttribute("articles", articles);
		
		return "/mpaUsr/article/list";
	}
	
	

}
