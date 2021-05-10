package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class UpdatePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null|| request.getMethod().equals("POST")==false){
			return "redirect:index.jsp";
		}
		String catergory = request.getParameter("catergory");
		String postTitle = request.getParameter("postTitle");
		String postContent = request.getParameter("postContent");
		PostVO vo = new PostVO();
		vo.setPostNo(request.getParameter("postNo"));
		vo.setCatergory(catergory);
		vo.setPostTitle(postTitle);
		vo.setPostContent(postContent);
		PostDAO.getInstance().updatePosting(vo);
		System.out.println("***UpdatePostController***");
		String path = "redirect: PostDetailNoHitsController.do?postNo="+vo.getPostNo();
		return path ;
	}

}
