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
		if(session==null||session.getAttribute("mvo")==null){
			return "redirect:index.jsp";
		}
		String id = request.getParameter("cid");
		MemberVO mvo = (MemberVO)session.getAttribute("mvo");
		MemberDAO.getInstance().updateMemberCountry(mvo.getId(), id);
		CountryVO cvo = CountryDAO.getInstance().findCountryById(id);
		mvo.setCountryVO(cvo);
		session.setAttribute("mvo", mvo);
		return "redirect:member/changeCountry-ok.jsp";
	}
}
