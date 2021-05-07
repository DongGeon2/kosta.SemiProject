package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;

public class UpdatePostFormController implements Controller {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null||
				request.getMethod().equals("POST")==false){
			return "redirect:index.jsp";
		}
		PostVO vo = PostDAO2.getInstance().getPostingByNo(request.getParameter("postNo"));
		
		return null;
	}

}
