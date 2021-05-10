package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostDAO3;
import org.kosta.semi.model.PostVO;
/**
 * 조회수 UPDATE 안되는 PostDetail View 기능
 * (작성 후 자기가 쓴 상세 글 보기 
 * or 내가 쓴 글 조회시 업데이트 안되게)
 * 
 * @author SUE
 *
 */
public class PostDetailNoHitsController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:index.jsp";
		}
		String postNo = request.getParameter("postNo");
		System.out.println(postNo);
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		ArrayList<String> time = PostDAO3.getInstance().getSysdateAndLocalTime(postNo);
		
		// 개별 게시물 조회
		request.setAttribute("pvo", pvo);
		String countryName = pvo.getCountryVO().getCountryName();
		request.setAttribute("countryName", countryName);
		//시간정보(작성시간)
		request.setAttribute("time", time);
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("url", "/board/post-detail.jsp");
		return "/template/layout.jsp";
	}

}
