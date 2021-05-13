package org.kosta.semi.controller;

import java.util.LinkedHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;

public class PointCheckController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		int point = MemberDAO.getInstance().getPointById(mvo.getId());
		System.out.println(point);
		mvo.setPoint(point);
		request.setAttribute("point", point);
		if (point < 10)
			return "redirect:/premium-access-fail.jsp";
		else {
			String travelStyleArr[][] = { { "북적", "한적", "무관" }, { "럭셔리", "저예산", "가성비" }, { "큰계획", "세부계획", "즉흥" },
					{ "음식", "쇼핑", "숙소" } };
			LinkedHashMap<String, String[]> travelStyleMap = new LinkedHashMap<String, String[]>();
			travelStyleMap.put("인파", travelStyleArr[0]);
			travelStyleMap.put("소비", travelStyleArr[1]);
			travelStyleMap.put("계획", travelStyleArr[2]);
			travelStyleMap.put("중요", travelStyleArr[3]);

			/*
			 * StringBuilder initial = new StringBuilder(); for (int i =0;
			 * i<travelStyleArr.length;i++) { for(String[] style: travelStyleArr ) {
			 * initial.append(style[i].charAt(0)); } } 첫글자로 받을지 인덱스로 받을지
			 */
			if (session.getAttribute("svo") == null) {
				request.setAttribute("map", travelStyleMap);
				request.setAttribute("url", "/premium-select-form2.jsp");
				request.setAttribute("urlCountry", "/premium-header.jsp");
				return "/template/layout.jsp";
			} else {
				return "TravelStyleMatchController.do";
			}
		}
	}
}
