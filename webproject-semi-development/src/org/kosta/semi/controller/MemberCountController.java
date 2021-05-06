package org.kosta.semi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.MemberDAO;

public class MemberCountController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("MemberCountController 접근");
		HashMap<String, Integer> map = MemberDAO.getInstance().getMemberCountByCountry();
		request.setAttribute("countryMap", map);
		return "/template/memberCount.jsp";
	}
}

