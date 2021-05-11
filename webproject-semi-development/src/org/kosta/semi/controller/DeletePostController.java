package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;

public class DeletePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null || session.getAttribute("mgvo") == null || request.getMethod().equals("POST") == false){
			return "redirect:member/loginUnlocked.jsp";
			
		}
		// pageNo -> postNo 수정해야함
		String postNo = request.getParameter("pageNo");	
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		System.out.println(postNo);
		
		String countryId = pvo.getCountryVO().getCountryId();
		PostDAO2.getInstance().deletePosting( Integer.parseInt(postNo));
		// 삭제된 게시글의 나라 게시판으로 이동
		return "redirect:IndividualListBycountryController.do?countryId=" + countryId;
	}

}
