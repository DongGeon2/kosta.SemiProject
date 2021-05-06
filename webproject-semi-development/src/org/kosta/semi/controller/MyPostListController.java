package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class MyPostListController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		String id = request.getParameter("id");
		MemberVO mvo = null;
		if (session.getAttribute("mvo") != null) 
			mvo = (MemberVO) session.getAttribute("mvo");
		// 본인만 작성글 리스트 보게 하고 싶으면 id와 sessionScope.mvo.id같다는 제약 걸어주기
		if (id.equals(mvo.getId())) {
			int totalPostCount=PostDAO.getInstance().getMyTotalPostCount(id);
			String pageNo=request.getParameter("pageNo");
			PagingBean pagingBean = null;
			if(pageNo==null) {
				pagingBean=new PagingBean(totalPostCount);
			} else {
				pagingBean=new PagingBean(totalPostCount,Integer.parseInt(pageNo));
			}
			request.setAttribute("pagingBean", pagingBean);
			list = PostDAO.getInstance().getMyPostingList(pagingBean, id);
		// 다른 회원의 조회글 쿼리스트링으로 접근할시
			} else if (id != null) {
				System.out.println("현재 타회원의 내가쓴글보기는 접근 불가합니다");
				return "error.jsp"; //수정 : 위내용의 팝업 띄우고 전체목록 또는 본인글쓰기목록으로
		//세션만료시
			} else { 
				System.out.println("세션만료등의 이유로 전체 게시판으로 이동");
				//list=PostDAO.getInstance().getAllPostingList(null);	
				return "redirect:AllListController.do";
			}
			request.setAttribute("list", list);
			request.setAttribute("url", "/board/list.jsp");		
			return "/template/layout.jsp";
			/*
			 * 
			 * System.out.println("*List-C 실행");
			 * 
			 * 
			 * String id = request.getParameter("id"); MemberVO mvo = null;
			 * if(session.getAttribute("mvo")!=null) { mvo =
			 * (MemberVO)session.getAttribute("mvo");
			 * 
			 * //본인만 작성글 리스트 보게 하고 싶으면 id와 sessionScope.mvo.id같다는 제약 걸어주기 if
			 * (id.equals(mvo.getId())) {
			 * list=BoardDAO.getInstance().getPostingListById(id); //다른 회원의 조회글 쿼리스트링으로 접근할시
			 * }else if (id!=null) { System.out.println("현재 타회원의 내가쓴글보기는 접근 불가합니다"); return
			 * "error.jsp"; } else { System.out.println("전체목록보기");
			 * list=BoardDAO.getInstance().getPostingList(); } request.setAttribute("list",
			 * list); request.setAttribute("url", "/board/list.jsp"); return
			 * "/template/layout.jsp"; }
			 */
		}

	}

