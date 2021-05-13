package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class SearchController implements Controller {
	/**
	 * 나라와 컬럼명을 선택하여 특정 문자열이 포함된 게시물을 검색하는 controller
	 * 조회된 게시물에 새로 글번호를 매깁니다.
	 * @author 지은
	 */

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String keyWord = request.getParameter("keyWord");
		String column = request.getParameter("column");
		String country_id = request.getParameter("country_id");
		int totalPostCount = PostDAO.getInstance().getSearchByKeyWordTotalPostCount(country_id, column, keyWord);
		
		System.out.println("keyWord: " + keyWord);
		System.out.println("column: " + column);
		System.out.println("country_id: " + country_id);
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
		
		request.setAttribute("controller", "SearchController");
		request.setAttribute("queryString", request.getQueryString());
		request.setAttribute("pagingBean", pagingBean);

		ArrayList<PostVO> resultList = new ArrayList<PostVO>();
		resultList = PostDAO.getInstance().searchByKeyWord(pagingBean, country_id, column, keyWord);
		request.setAttribute("list", resultList);
		request.setAttribute("url", "/board/main-list.jsp");
		return "/template/layout.jsp";
	}

}
