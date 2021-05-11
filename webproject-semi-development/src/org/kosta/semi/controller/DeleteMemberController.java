package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;

public class DeleteMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null || request.getMethod().equals("POST") == false){
			//return "redirect:AllListController.do";
		}
		String id[] = request.getParameterValues("deleteId");
		for(int i=0; i<id.length; i++) {
			System.out.println(id[i]);
		}
		request.setAttribute("url", "/manager/member-list.jsp");
		
		return "/template/layoutMember.jsp";
	}

}
