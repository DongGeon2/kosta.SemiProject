package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CommentDAO;

public class DeleteCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null || request.getMethod().equals("POST") == false) {
			return "redirect:member/loginUnlocked.jsp";
		}
		String postNo = request.getParameter("postNo");
		String commentNo = request.getParameter("commentNo");
		System.out.println("s + commentNo:" + commentNo);
		CommentDAO.getInstance().commentDelete(commentNo);
		
		
		
		return "PostDetailNoHitsController.do?pageNo="+postNo;
	}

}
