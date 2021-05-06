package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class UpdateMemberFormController implements Controller {

	/**
	 * password 입력 값을 확인하여 비밀번호가 일치시 UpdateMemberForm에 접근시킵니다.
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		if(session == null || mvo == null ) {
			return "redirect:index.jsp";
		}
		//세션에 저장되어 있는 mvo의 id값과 입력받은 password로 로그인을 다시 실행 후,
		//반환된 값이 없을 경우(로그인 실패)에는 fail.jsp로 보냅니다.
		MemberVO checkMVO = MemberDAO.getInstance().login(mvo.getId(), request.getParameter("password"));
		if(checkMVO == null) {
			request.setAttribute("url", "/member/checkPassword.jsp");
			return "/member/update-check-password-fail.jsp";
		} else {
			request.setAttribute("url", "/member/member-update.jsp");
			return "/template/layoutMember.jsp";
		}
	}

}
