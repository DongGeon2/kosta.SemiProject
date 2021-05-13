package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.ActVO;
import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;

public class MyDetailInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null ) {
			return "redirect:index.jsp";
		}
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		//detail
		MemberVO detailMVO = MemberDAO.getInstance().getMemberDetailById(mvo.getId());
		request.setAttribute("detailMVO", detailMVO);
		
		//act list
		int totalPostCount=MemberDAO.getInstance().getActCountById(mvo.getId());
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
		} else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}
		request.setAttribute("pagingBean", pagingBean);
		ArrayList<ActVO> list = MemberDAO.getInstance().getActListById(mvo.getId(), pagingBean);
		//System.out.println(list);
		request.setAttribute("list", list);

		
		request.setAttribute("urlCountry", "/member/member-detail-info.jsp");
		request.setAttribute("url", "/member/acted-list.jsp");
		return "/template/layout.jsp";
	}

}
