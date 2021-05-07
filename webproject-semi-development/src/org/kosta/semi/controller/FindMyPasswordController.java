package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class FindMyPasswordController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String email = request.getParameter("email");
		String id = request.getParameter("id");
		MemberVO mvo = MemberDAO.getInstance().findPasswordByEmail(email, id);
		if(mvo != null) {
			request.setAttribute("findMVO", mvo);
			return "/member/findMyPassword-ok.jsp";
		}else {
			return "/member/findMyPassword-fail.jsp";
		}
	}

}
