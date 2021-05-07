package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.MemberDAO;

public class IdCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		boolean result = MemberDAO.getInstance().idcheck(id);
		if (result) {// id가 존재하므로 중복, idcheck-fail.jsp 가 팝업으로 응답
			return "member/idcheck-fail.jsp";
		} else {// id가 존재하지 않으므로 사용가능 , idcheck-ok.jsp 가 팝업으로 응답
			return "member/idcheck-ok.jsp";
		}
	}
}
