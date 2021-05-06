package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.controller.Controller;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;






public class ListController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int totalPostCount=PostDAO.getInstance().getTotalPostCount();
		String pageNo=request.getParameter("pageNo");
		PagingBean pagingBean = null;
		if(pageNo==null) {
			pagingBean=new PagingBean(totalPostCount);
		} else {
			pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
		}
		request.setAttribute("pagingBean", pagingBean);
		ArrayList<PostVO> list = PostDAO.getInstance().getPostingAllList(pagingBean);
		request.setAttribute("list", list);
		request.setAttribute("url", "/board/main-list.jsp");
		return "/template/layout.jsp";
	}
}
