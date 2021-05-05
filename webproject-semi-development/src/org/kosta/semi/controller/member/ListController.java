package org.kosta.semi.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.controller.common.Controller;
import org.kosta.semi.model.dao.PostDAO;


public class ListController implements Controller {
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute("list", PostDAO.getInstance().getPostingAll());
		request.setAttribute("url", "/board/main-list.jsp");
		return "/template/layout.jsp";
	}
}
