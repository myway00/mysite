package com.poscodx.mysite.mvc.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscodx.mysite.dao.BoardDao;
import com.poscodx.mysite.vo.BoardVo;
import com.poscodx.web.mvc.Action;
import com.poscodx.web.util.MvcUtil;

public class ListAction implements Action {
	
	private static final int PAGING_CNT = 6;
	private static final int LIST_CNT = 8;
	private static final int START_PAGE = 1;
	private static final int NO_NEXT_PAGE = 1;
	private static final int INIT_ZERO_PAGE = 0;
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pageCount = PAGING_CNT;
		int listCount = LIST_CNT;
		int currentPage = START_PAGE;  
		int startPage = START_PAGE;  
		int nextPage = NO_NEXT_PAGE;
		int count = INIT_ZERO_PAGE;	//	리스트 개수
		int totalPage = 1; // 총 페이지
		int endPage = INIT_ZERO_PAGE; // 끝나는 페이징
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		
		//	현재 페이지 번호
		if (request.getParameter("page") != null) {
			currentPage = Integer.parseInt(request.getParameter("page"));
		}

		// 키워드 검색 여부
		String input;
		String limit = "";
		List<BoardVo> list = null;
		if (request.getParameter("keyWord") == null && request.getParameter("keyWord2")==null) {	//	키워드 검색 아니 경우
			input = "";
			count = new BoardDao().CountList();
			m.put("serach", 0);
		} else {	//	키워드 검색인 경우
			if(request.getParameter("keyWord")!=null) {
				input = request.getParameter("keyWord");	
				
			}
			else {
				input = request.getParameter("keyWord2");
			}
			
			list = new BoardDao().findAll("", "\\" + input);
			count = list.size();
			m.put("serach", 1);
		}
		System.out.println(input);
		
		totalPage = ((int) Math.ceil(count / (double) listCount)); 
		endPage = ((int) Math.ceil(totalPage / (double) pageCount));
		limit = " LIMIT "+  (currentPage - 1) + " , " + listCount;
		list = new BoardDao().findAll(limit, input);
		
		if (totalPage <= pageCount) {
			endPage = totalPage;
			startPage = 1;
		} else {
			endPage = ((int) Math.ceil(currentPage / (double) pageCount)) * pageCount;
			startPage = (endPage - pageCount) + 1;
		}
		nextPage = endPage + 1;

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		int prePage = startPage - 1; // 이전 페이징
		m.put("pageCount", pageCount);
		m.put("listCount", listCount);
		m.put("currentPage", currentPage);
		m.put("totalPage", totalPage);
		m.put("startPage", startPage);
		m.put("endPage", endPage);
		m.put("prePage", prePage);
		m.put("nextPage", nextPage);
		m.put("totalList", count);
		m.put("startnum", ((totalPage - currentPage) + 1) * listCount);
		
		request.setAttribute("m", m);
		request.setAttribute("list", list);
		request.setAttribute("keyWord", input);
		MvcUtil.forward("board/list", request, response);
	}


}
