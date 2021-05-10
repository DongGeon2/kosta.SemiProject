package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostDAO3;
import org.kosta.semi.model.PostVO;

/**
 * PostDetailController 
 * 글 상세보기 Controller
 * (조회수 update)
 * 
 * @author SUE
 *
 */
public class PostDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:index.jsp";
		}
		String postNo = request.getParameter("postNo");		
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");

		/*
		 * 읽은 게시물을 다시 읽었을 때 조회수 증가를 방지하기 위해 noList에 게시글번호가 존재하지 않으면 조회수를 증가시킨다.
		 */
		@SuppressWarnings("unchecked")
		ArrayList<String> noList = (ArrayList<String>) session.getAttribute("noList");
		if (noList.contains(postNo) == false) {
			//System.out.println("page 새로 읽음");
			PostDAO2.getInstance().updateHit(postNo);
			noList.add(postNo);
		}
		
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		String countryName = pvo.getCountryVO().getCountryName();
		ArrayList<String> time = PostDAO3.getInstance().getSysdateAndLocalTime(postNo);
		
		// post 작성자 아이디와 로그인한 id 가 같을 때 조회수 count 안하기
		if( mvo.getId().equals(pvo.getMemberVO().getId())) {
			//System.out.println("작성자가 글읽음");
			// redirect:PostDetailNoHitsController.do 로 redirect 해서 hit update 방지
			return "redirect:PostDetailNoHitsController.do?postNo="+pvo.getPostNo();
		}
		request.setAttribute("pvo", pvo);
		request.setAttribute("time", time);
		request.setAttribute("countryName", countryName);
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("url", "/board/post-detail.jsp");
		return "/template/layout.jsp";
	}

}
