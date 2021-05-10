package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 * WritePostFormController
 * 글쓰기 BTN 클릭 -> 글쓰기 FORM으로 이동해주는 Controller
 * @author SUE
 *
 */
public class WritePostFormController implements Controller{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("mvo") == null ) {
			return "redirect:index.jsp";
		}
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("url", "/board/post-write.jsp");
		System.out.println("리퀘스트 완료");
		return "/template/layout.jsp";
	}
}
