package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.StyleDAO;
import org.kosta.semi.model.StyleVO;

public class TravelStyleMatchController implements Controller {
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session =request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		System.out.println(session);
		System.out.println(mvo);
		if (session==null||mvo==null) //||mvo.getPoint()<10 하려면 로그인시 가져오는 mvo에 point추가해야
			return "redirect:index.jsp";
		String style1 =request.getParameter("index1");
		String style2 =request.getParameter("index2");
		String style3=request.getParameter("index3");
		String style4=request.getParameter("index4");
		StyleVO svo = new StyleVO(style1, style2, style3, style4);
		//System.out.println("svo: "+svo);
		String memberId = mvo.getId();
		StyleDAO.getInstance().registerStyle(memberId, svo);
		ArrayList <MemberVO> matchingMemberList = StyleDAO.getInstance().findMemberBySvo(mvo, svo); //원래는 MemberDAO에 들어가야할 메서드
		System.out.println(matchingMemberList);
		//세션에 저장해도 될지도?
		//session.setAttribute("svo", svo);
		request.setAttribute("svo", svo);
		request.setAttribute("memberList", matchingMemberList);
		request.setAttribute("url", "/manager/member-list.jsp");
		request.setAttribute("urlCountry", "/premium-board.jsp");
		return "/template/layout.jsp";
	}
}
