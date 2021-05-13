package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.LikeDAO;
import org.kosta.semi.model.LikeVO;
import org.kosta.semi.model.MemberVO;

public class LikeController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null||request.getMethod().equals("POST")==false) {
			System.out.println("세션 널");
			return "redirect:index.jsp";
		}
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		String memberId = mvo.getId();
		String postNo = request.getParameter("postNo");
		//System.out.println("--->"+postNo);
		
		//lvo가 null이면 좋아요 존재x
		//lvo가 not null이면 좋아요 존재
		LikeVO lvo=LikeDAO.getInstance().check(memberId, postNo);
		
		//lvo가 null이므로 insert into ㄱㄱ
		if(lvo==null) {
			LikeDAO.getInstance().insertLike(memberId,postNo);
		}else {//lvo가 not null이면 좋아요 취소->해당 행 지워버리기
			LikeDAO.getInstance().deleteLike(memberId,postNo);
		}
		
		//해당 게시물의 총 좋아요 수
		int totalLike = LikeDAO.getInstance().totalCount(postNo);
		System.out.println(lvo);
		System.out.println("총 좋아요 수"+totalLike);
		
		request.setAttribute("responsebody", totalLike);
		return "AjaxView";
	}

}
