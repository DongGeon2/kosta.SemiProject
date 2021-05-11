package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.FileDAO;
import org.kosta.semi.model.FileVO;
import org.kosta.semi.model.MemberVO;
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
			return "redirect:index.jsp";
		}
		String postNo = request.getParameter("postNo");
		System.out.println(postNo);
		String fileName = FileDAO.getInstance().getFileName(postNo);
		
		/*
		 * 만약 사용자가 쓴글이 아니라 sql을 통해 강제로 데이터를 넣었을경우 filedb 에는 해당 post에 대한
		 * db값이 없기때문에 글보기를 할수 없음. 따라서 미리 만들어져있는 게시물의 경우 filedb에 값들을 추가
		 * 해줘야하는데 그럴경우에 대한 실행 흐름은 if문 아래에서 실행되게해야함.
		 * 이렇게 하지 않을꺼면 만들어져있는 post의 게시글 번호를 토대로 sql에서 filedb에 직접 insert해줘야함
		 * insert해준 내용은 ddl.sql에 써져있음.
		 */
//		if(fileName==null||fileName=="") {
//			//fileName = FileDAO.getInstance().insertNewFile(postNo);
//		}
		System.out.println(fileName);
		fileName=new String(fileName.getBytes("UTF-8"),"8859_1");
		System.out.println(postNo+","+fileName);
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		
		/*
		 * 읽은 게시물을 다시 읽었을 때 조회수 증가를 방지하기 위해 noList에 게시글번호가 존재하지 않으면 조회수를 증가시킨다.
		 */
		@SuppressWarnings("unchecked")
		ArrayList<String> noList = (ArrayList<String>) session.getAttribute("noList");
		if (noList.contains(postNo) == false) {
			//System.out.println("page 새로 읽음");
			PostDAO2.getInstance().updateHit(postNo);
			noList.add(postNo);
		}
		
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);
		
		
		// post 작성자 아이디와 로그인한 id 가 같을 때 조회수 count 안하기
		if( mvo.getId().equals(pvo.getMemberVO().getId())) {
			//System.out.println("작성자가 글읽음");
			// redirect:PostDetailNoHitsController.do 로 redirect 해서 hit update 방지
			return "redirect:PostDetailNoHitsController.do?postNo="+pvo.getPostNo()+"&fileName="+fileName;
		}
		
		if(fileName==null||fileName=="") {
			request.setAttribute("pvo", pvo);;
			request.setAttribute("urlCountry", "/template/countryInfo.jsp");
			request.setAttribute("url", "/board/post-detail.jsp");
			return "/template/layout.jsp";
		}
		
		FileVO fvo = FileDAO.getInstance().getFile(postNo, fileName);
		request.setAttribute("pvo", pvo);;
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("fvo", fvo);
		request.setAttribute("url", "/board/post-detail.jsp");
		System.out.println(fvo);
		System.out.println("PostDetailController 끝");
		return "/template/layout.jsp";
	}

}
