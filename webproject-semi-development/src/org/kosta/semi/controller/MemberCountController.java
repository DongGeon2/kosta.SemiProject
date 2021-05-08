package org.kosta.semi.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.MemberDAO;

public class MemberCountController implements Controller {
	/**
	 * index화면의 상단 콘텐츠에 들어갈 나라별 회원 수를 띄워주는 controller
	 * @author 지은
	 */
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HashMap<String, Integer> map = MemberDAO.getInstance().getMemberCountByCountry();
		request.setAttribute("countryMap", map);
		return "/template/memberCount.jsp";
	}
}

