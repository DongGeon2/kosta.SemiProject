package org.kosta.semi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class AllListController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalPostCount=PostDAO.getInstance().getTotalPostCount();
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
		} else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}
		
		request.setAttribute("controller", "AllListController");
		request.setAttribute("pagingBean", pagingBean);
		ArrayList<PostVO> list = PostDAO.getInstance().getAllPostingList(pagingBean);
		request.setAttribute("list", list);
		request.setAttribute("url", "/board/main-list.jsp");
		HashMap<String, Integer> map = MemberDAO.getInstance().getMemberCountByCountry();
		request.setAttribute("countryMap", map);
		request.setAttribute("urlCountry", "/template/memberCount.jsp");
		request.setAttribute("urlpopup", "/template/popup.jsp");
		return "/template/layoutpopup.jsp";
	}
}
