package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class SearchController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("SearchController 접근");
		String keyWord = request.getParameter("keyWord");
		String column = request.getParameter("column");
		String country_id = request.getParameter("country_id");
		// test
		System.out.println("keyWord: " + keyWord);
		System.out.println("column: " + column);
		System.out.println("country_id: " + country_id);
		
		int totalPostCount = PostDAO.getInstance().getSearchByKeyWordTotalPostCount(country_id, column, keyWord);
		//test
		System.out.println("totalPostCount: "+totalPostCount);

		String pageNo = request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if (pageNo == null) {
			pagingBean = new PagingBean(totalPostCount);
			System.out.println("pB 시작넘버: " + pagingBean.getStartRowNumber());
			System.out.println("pB 끝넘버: " + pagingBean.getEndRowNumber());
		} else {
			pagingBean = new PagingBean(totalPostCount, Integer.parseInt(pageNo));
		}
		request.setAttribute("pagingBean", pagingBean);

		ArrayList<PostVO> resultList = new ArrayList<PostVO>();
		resultList = PostDAO.getInstance().searchByKeyWord(pagingBean, country_id, column, keyWord);
		request.setAttribute("list", resultList);
		request.setAttribute("url", "/board/main-list.jsp");
		return "/template/layout.jsp";
	}

}
