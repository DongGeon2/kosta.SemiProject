package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PagingBean;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

public class MyPostListController implements Controller {
	/**
	 * 세션 정보에서 mvo의 id를 받아와 회원이 쓴 글 목록을 가져오는 controller
	 * 세션 만료 및 로그아웃시에는 index화면으로 보냅니다
	 * @author 지은
	 */

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession(false);
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		String id = request.getParameter("id");
		MemberVO mvo = null;
		//로그인확인
		if (session.getAttribute("mvo") != null) {
			mvo = (MemberVO) session.getAttribute("mvo");
		}else { //세션 만료 및 로그아웃시 index로이동
			return "redirect:AllListController.do";
		}
		
		// 본인만 본인의 작성글 리스트 보게 하고 싶으면 id와 sessionScope.mvo.id같다는 조건 걸어주기
		if (!id.equals(mvo.getId())) {//다른회원의 조회글 쿼리스트링으로 접근시
			System.out.println("현재 타회원의 내가쓴글보기는 접근 불가합니다");
			return "redirect:AllListController.do";//수정할까? : 위내용의 팝업 띄우고 전체목록 또는 본인글쓰기목록으로
		}else {
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
			//System.out.println(list);
			request.setAttribute("list", list);
			request.setAttribute("country", "내가 쓴 글");
			request.setAttribute("url", "/board/board-list.jsp");		
			return "/template/layout.jsp";
		}

		}
	}
	

