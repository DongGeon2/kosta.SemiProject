package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class PointCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		int point = MemberDAO.getInstance().getPointById(mvo.getId());
		mvo.setPoint(point);
		request.setAttribute("point", point);
		if (point < 10)
			return "premium-access-fail.jsp";
		else {
			// request.setAttribute("url","/board/main-list.jsp");
			request.setAttribute("url", "/premium-selectform.jsp");
			request.setAttribute("urlCountry", "/premiumBoard.jsp");
			return "/template/layout.jsp";
		}
	}
}
