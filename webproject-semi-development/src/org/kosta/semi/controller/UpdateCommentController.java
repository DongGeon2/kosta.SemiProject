package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CommentDAO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;

public class UpdateCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null|| request.getMethod().equals("POST")==false){
			return "redirect:index.jsp";
		}
		System.out.println("");
		System.out.println("****UpdateCommentController 시작****");
		String postNo = request.getParameter("postNo");
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		
		String commentNo = request.getParameter("commentNo");
		String commentContent = request.getParameter("commentContent");
		System.out.println(commentNo);
		System.out.println(commentContent);
		System.out.println("");
		
		CommentDAO.getInstance().commentUpdate(commentNo, commentContent);
		request.setAttribute("url", "/board/post-detail.jsp");
		request.setAttribute("postNo",postNo);
		return "redirect:PostDetailNoHitsController.do?postNo="+pvo.getPostNo();
	}

}
