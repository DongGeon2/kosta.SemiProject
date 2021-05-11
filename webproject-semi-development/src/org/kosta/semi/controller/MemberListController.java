package org.kosta.semi.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.ManagerDAO;
import org.kosta.semi.model.ManagerVO;
import org.kosta.semi.model.MemberDAO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class MemberListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
			HttpSession session = request.getSession(false);
			ManagerVO mgvo = (ManagerVO)session.getAttribute("mgvo");
			if(session == null || mgvo == null ) {
				return "redirect:index.jsp";
			}
			
			int totalMemeberCount=ManagerDAO.getInstance().getTotalMemberCount();
			String pageNo=request.getParameter("pageNo");
			PagingBean pagingBean = null;
			if(pageNo==null) {
				pagingBean=new PagingBean(totalMemeberCount);
			} else {
				pagingBean=new PagingBean(totalMemeberCount,Integer.parseInt(pageNo));
			}
			
			
			request.setAttribute("pagingBean", pagingBean);
			ArrayList<MemberVO> list = ManagerDAO.getInstance().getAllMemberList(pagingBean);
			request.setAttribute("memberList", list);
			request.setAttribute("url", "/manager/member-list.jsp");
			
			return "/template/layoutMember.jsp";
		}
	}


