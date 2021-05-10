package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostVO;

public class PointController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		mvo
		
		ArrayList <PostVO> list = new ArrayList <PostVO> ();
		//PointDAO.getInstance().getPostingForPoint();
		request.setAttribute("urlCountry", "/premiumCategory.jsp");
		request.setAttribute("url", "/board/main-list.jsp");
		request.setAttribute("list", list);
		return "template/layout.jsp";
	}

}
