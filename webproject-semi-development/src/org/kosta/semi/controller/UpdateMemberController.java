package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class UpdateMemberController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String birth = request.getParameter("birth");
		String travelstyle = request.getParameter("travelstyle");
		String country = request.getParameter("country");
		
		mvo.setName(name);
		mvo.setPassword(password);
		mvo.setBirth(birth);
		mvo.setTravelStyle(travelstyle);
		CountryVO cvo = CountryDAO.getInstance().findCountryById(country);
		mvo.setCountryVO(cvo);
		
		MemberDAO.getInstance().UpdateMember(mvo);
		return "redirect:member/update-ok.jsp";	
	}

}
