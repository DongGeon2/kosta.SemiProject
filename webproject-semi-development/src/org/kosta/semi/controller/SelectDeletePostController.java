package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * 관리자 모드일때 
 * 게시글 관리(여러개 선택해서 게시글 제거)
 * @author SUE
 *
 */
public class SelectDeletePostController implements Controller {

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
		return null;
	}

}
