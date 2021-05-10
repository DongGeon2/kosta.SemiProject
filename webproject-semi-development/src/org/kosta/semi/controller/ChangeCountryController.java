package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class ChangeCountryController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session=request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null|| request.getMethod().equals("POST")==false){
			return "redirect:index.jsp";
		}
		String id = request.getParameter("cid");
		System.out.println(id);
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		System.out.println(mvo);
		MemberDAO.getInstance().UpdateMemberCountry(mvo.getId(), id);
		CountryVO cvo = CountryDAO.getInstance().findCountryById(id);
		System.out.println(cvo);
		//session.setAttribute("mvo", mvo);
		return "redirect:index.jsp";
	}
}
