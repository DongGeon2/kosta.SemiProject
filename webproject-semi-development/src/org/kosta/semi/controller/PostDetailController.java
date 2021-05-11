package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.FileDAO;
import org.kosta.semi.model.FileVO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;

/**
 * PostDetailController 
 * 글 상세보기 Controller
 * (조회수 update)
 * 
 * @author SUE
 *
 */
public class PostDetailController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println();
		System.out.println("PostDetailController 시작");
		HttpSession session = request.getSession(false);
		if (session == null || session.getAttribute("mvo") == null) {
			return "redirect:member/loginUnlocked.jsp";
		}
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String postNo = request.getParameter("postNo");
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		// post 작성자 아이디와 로그인한 id 가 같을 때(본인글) 조회수 count 안하기
		if( mvo.getId().equals(pvo.getMemberVO().getId())) {
			//System.out.println("작성자가 글읽음");
			return "PostDetailNoHitsController.do";
		}
		/*
		 * 읽은 게시물을 다시 읽었을 때 조회수 증가를 방지하기 위해 noList에 게시글번호가 존재하지 않으면 조회수를 증가시킨다.
		 */
		@SuppressWarnings("unchecked")
		ArrayList<String> noList = (ArrayList<String>) session.getAttribute("noList");
		if (noList.contains(postNo) == false) {
			//System.out.println("page 새로 읽음");
			PostDAO2.getInstance().updateHit(postNo);		//혹시 파일명 깨질까
			noList.add(postNo);
		}
		
		//파일 정보 불러오기 관련 로직
		String fileName = FileDAO.getInstance().getFileName(postNo);
		FileVO fvo = FileDAO.getInstance().getFile(postNo, fileName);
		System.out.println("fvo: "+fvo);
		System.out.println(fileName);
		//혹시 파일명 깨질까
		if (fileName != null && fileName != "") {
			fileName=new String(fileName.getBytes("UTF-8"),"8859_1");
			System.out.println("게시글번호, 파일이름: "+postNo+","+fileName);
		}
		//한국과 해당게시판의 나라별 시간
		ArrayList<String> time = PostDAO.getInstance().getSysdateAndLocalTime(postNo);
		request.setAttribute("time", time);
		
		//한국과 해당게시판의 나라별 시간
		ArrayList<String> time = PostDAO.getInstance().getSysdateAndLocalTime(postNo);
		request.setAttribute("time", time);
		
		request.setAttribute("pvo", pvo);
		request.setAttribute("fvo", fvo);
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("url", "/board/post-detail.jsp");
		System.out.println("PostDetailController 끝");
		return "/template/layout.jsp";
	}
}
