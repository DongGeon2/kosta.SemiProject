package org.kosta.semi.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

	public class RegisterController implements Controller{

		@Override
		public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			String id = request.getParameter("id");
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String birth = request.getParameter("birth");
			String travelstyle = request.getParameter("travelstyle");
			
			String countryId = request.getParameter("countryId");
			CountryVO cvo = CountryDAO.getInstance().findCountryById(countryId);
			String email = request.getParameter("email");
			MemberVO mvo = new MemberVO(cvo, birth, gender, travelstyle, email, id, name, password);

			MemberDAO.getInstance().registerMember(mvo);
			
			return "redirect:index.jsp";
		}
	}
