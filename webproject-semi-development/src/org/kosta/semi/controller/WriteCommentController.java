package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CommentDAO;
import org.kosta.semi.model.CommentVO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;


public class WriteCommentController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null || request.getMethod().equals("POST") == false) {
			return "redirect:member/loginUnlocked.jsp";
		}
		//commentVO 생성 하는 method 작성 
		String memberId = request.getParameter("memberId");
		String postNo = request.getParameter("postNo");
		String commentContent = request.getParameter("commentContent");
		
		
		// postVO 받아오기
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		CommentVO cvo = new CommentVO();
		cvo.setPostVO(pvo); // PostVO
		cvo.setMemberVO(pvo.getMemberVO()); //countryVO
		cvo.setCommentContent(commentContent); //commentContent
		
		CommentDAO.getInstance().commentPosting(cvo, memberId, commentContent);
		String path="PostDetailNoHitsController.do?pageNo="+pvo.getPostNo();
		return path;
	}
	
	
	
	

}
