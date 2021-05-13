package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CommentDAO;
import org.kosta.semi.model.CommentVO;

public class UpdateCommentFormController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null ) {
			return "redirect:index.jsp";
		}
		String commentNo = request.getParameter("commentNo");
		System.out.println("***************");
		System.out.println(commentNo);
		CommentVO cvo = CommentDAO.getInstance().getComment(commentNo);
		System.out.println(cvo);
		request.setAttribute("cvo", cvo);
		request.setAttribute("url", "/board/comment-update.jsp");
		return "/template/layout.jsp";
	}

}
