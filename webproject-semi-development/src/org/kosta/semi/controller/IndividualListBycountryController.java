package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class IndividualListBycountryController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//System.out.println("1");
		String countryId = request.getParameter("countryId");
		System.out.println(countryId);
		//System.out.println(countryId);
		//여기서부터 수정 
		int totalPostCount=PostDAO.getInstance().getCountryPostCount(countryId);
		System.out.println(totalPostCount);
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
			System.out.println(pagingBean.getStartRowNumber());
			System.out.println(pagingBean.getEndRowNumber());
		} else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}	
		request.setAttribute("pagingBean", pagingBean);
		ArrayList<PostVO>list = PostDAO.getInstance().getPostingListByCountry(pagingBean, countryId);
		System.out.println(list.toString());
		request.setAttribute("list", list);
		request.setAttribute("url", "/board/board-list.jsp");
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("country", CountryDAO.getInstance().findCountryById(countryId).getCountryName());
		return "/template/layout.jsp";
	}
}
