package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class FindMyIdController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		MemberVO mvo = MemberDAO.getInstance().findIdbyEmail(email);
		if(mvo != null) {
			request.setAttribute("findMVO", mvo);
			return "/member/findMyId-ok.jsp";
		}else {
			return "/member/findMyId-fail.jsp";
		}
	}
}
