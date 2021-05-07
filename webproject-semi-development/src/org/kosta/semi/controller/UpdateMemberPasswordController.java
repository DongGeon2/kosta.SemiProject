package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;
/**
 * UpdateMemeberForm 접근시 Password입력 Form에 접근하는 Controller
 * 세션 만료 및 로그아웃시에는 index화면으로 보냅니다
 * @author CHENJAE
 */
public class UpdateMemberPasswordController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null ) {
			return "redirect:index.jsp";
		}
		request.setAttribute("url", "/member/checkPassword.jsp");
		return "/template/layoutMember.jsp";
	}

}
