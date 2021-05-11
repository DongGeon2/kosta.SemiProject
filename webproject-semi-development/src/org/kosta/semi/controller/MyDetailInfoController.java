package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class MyDetailInfoController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null ) {
			return "redirect:index.jsp";
		}
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		MemberDAO.getInstance().getMemberDetailById(mvo.getId());
		request.setAttribute("detailMVO", mvo);
		request.setAttribute("url", "/member/myinfo.jsp");
		return "/template/layoutMember.jsp";
	}

}
