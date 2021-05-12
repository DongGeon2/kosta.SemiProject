package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.ActVO;
import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PagingBean;

public class MemberDetailInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mgvo") == null ) {
			return "redirect:index.jsp";
		}
		String id = request.getParameter("id");
		//detail
		MemberVO detailMVO = MemberDAO.getInstance().getMemberDetailById(id);
		request.setAttribute("detailMVO", detailMVO);
		
		//acted list
		int totalPostCount=MemberDAO.getInstance().getActCountById(id);
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
		} else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}
		request.setAttribute("pagingBean", pagingBean);
		ArrayList<ActVO> list = MemberDAO.getInstance().getActListById(id, pagingBean);
		//System.out.println(list);
		request.setAttribute("list", list);
		request.setAttribute("id", id);
		
		request.setAttribute("urlCountry", "/manager/member-detail-info.jsp");
		request.setAttribute("url", "/manager/acted-list.jsp");
		return "/template/layout.jsp";
	}

}
