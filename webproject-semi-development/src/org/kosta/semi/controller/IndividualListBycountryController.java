package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class IndividualListBycountryController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String countryId = request.getParameter("countryId");
		//board-list.jsp-paging에서 사용 
		request.setAttribute("countryId", countryId);
		int totalPostCount=PostDAO.getInstance().getCountryPostCount(countryId);
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
		} else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}	
		request.setAttribute("pagingBean", pagingBean);
		ArrayList<PostVO>list = PostDAO.getInstance().getPostingListByCountry(pagingBean, countryId);
		request.setAttribute("list", list);
		request.setAttribute("url", "/board/board-list.jsp");
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		String countryName = CountryDAO.getInstance().findCountryById(countryId).getCountryName();
		CountryVO country = CountryDAO.getInstance().findCountryById(countryId);
		request.setAttribute("country", country);
		request.setAttribute("countryName", countryName);
		request.setAttribute("count", CountryDAO.getInstance().findMemberCountByCountryname(countryName));
		return "/template/layout.jsp";
	}
}
