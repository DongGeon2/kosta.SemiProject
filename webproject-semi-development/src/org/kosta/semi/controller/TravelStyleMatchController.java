package org.kosta.semi.controller;

import java.sql.SQLIntegrityConstraintViolationException;
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
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String memberId = mvo.getId();
		if (session == null || mvo == null) // ||mvo.getPoint()<10 하려면 로그인시 가져오는 mvo에 point추가해야
			return "redirect:index.jsp";
		StyleVO svo = (StyleVO) session.getAttribute("svo");
		if (svo == null) {
			String style1 = request.getParameter("style1");
			String style2 = request.getParameter("style2");
			String style3 = request.getParameter("style3");
			String style4 = request.getParameter("style4");
			svo = new StyleVO(style1, style2, style3, style4);
			session.setAttribute("svo", svo);
			try {
				StyleDAO.getInstance().registerStyle(memberId, svo);
			} catch (SQLIntegrityConstraintViolationException e) {
				StyleDAO.getInstance().updateStyle(memberId, svo);
			}
		}

		ArrayList<MemberVO> matchingMemberList = StyleDAO.getInstance().findMemberBySvo(mvo, svo); // 원래는 MemberDAO에
																									// 들어가야할 메서드
		request.setAttribute("memberList", matchingMemberList);
		request.setAttribute("url", "/manager/member-list2.jsp");
		request.setAttribute("urlCountry", "/premium-header.jsp");
		return "/template/layout.jsp";
	}
}
