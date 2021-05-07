package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class LoginController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		MemberVO mvo = MemberDAO.getInstance().login(id, password);
		if (mvo != null) {
			HttpSession session = request.getSession();
			session.setAttribute("mvo", mvo);
			/*
		      읽은 게시물을 다시 읽었을 때 조회수 증가를 방지하기 위해 noList를 추가한다 
			  PostDetailController(상세글보기 컨트롤러)에서 이용한다 
			 */
			session.setAttribute("noList",new ArrayList<String>());
			return "redirect:index.jsp";
		} else {			
			return "redirect:member/login-fail.jsp";		
		}
	}

}
