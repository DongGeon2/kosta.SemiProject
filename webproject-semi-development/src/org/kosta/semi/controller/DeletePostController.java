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
		if(session == null || session.getAttribute("mvo") == null || request.getMethod().equals("POST") == false){
			//return "redirect:AllListController.do";
			
		}
		// pageNo -> postNo 수정해야함
		String postNo = request.getParameter("pageNo");	
		System.out.println(postNo);
		//PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		PostDAO2.getInstance().deletePosting( Integer.parseInt(postNo));
		//String countryName = pvo.getCountryVO().getCountryName();
		return "redirect:AllListController.do";
	}

}
