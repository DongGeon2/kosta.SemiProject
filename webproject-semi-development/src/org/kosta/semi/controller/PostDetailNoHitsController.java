package org.kosta.semi.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CommentDAO;
import org.kosta.semi.model.CommentVO;
import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.FileDAO;
import org.kosta.semi.model.FileVO;
import org.kosta.semi.model.LikeDAO;
import org.kosta.semi.model.LikeVO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostDAO2;
import org.kosta.semi.model.PostVO;

/**
 * 조회수 UPDATE 안되는 PostDetail View 기능 (작성 후 자기가 쓴 상세 글 보기 or 내가 쓴 글 조회시 업데이트 안되게)
 * 
 * @author SUE
 *
 */
public class PostDetailNoHitsController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("PostDetailNoHitsController 시작");
		HttpSession session = request.getSession(false);
		if (session == null || (session.getAttribute("mvo") == null  && session.getAttribute("mgvo") == null)) {
			return "redirect:index.jsp";
		}
		MemberVO mvo = (MemberVO) session.getAttribute("mvo");
		String postNo = request.getParameter("postNo");
		PostVO pvo = PostDAO2.getInstance().getPostingByNo(postNo);

		// 파일 정보 불러오기 관련 로직
		String fileName = FileDAO.getInstance().getFileName(postNo);
		System.out.println("파일이름: " + fileName);
		FileVO fvo = FileDAO.getInstance().getFile(postNo, fileName);
		System.out.println("fvo: " + fvo);
		System.out.println(fileName);
		//혹시 파일명 깨질까
		if (fileName != null && fileName != "") {
			fileName = new String(fileName.getBytes("UTF-8"), "8859_1");
			System.out.println("게시글번호, 파일이름: " + postNo + "," + fileName);
		}

		String countryName = pvo.getCountryVO().getCountryName();
		request.setAttribute("countryName", countryName);
		int countryCount = CountryDAO.getInstance().findMemberCountByCountryname(countryName);
		
		//해당 게시글의 cvo 가져오기
		//CountryVO cvo = CountryDAO.getInstance().findCountryName(countryName);	
		CountryVO cvo = CountryDAO.getInstance().findCountryById(pvo.getCountryVO().getCountryId());
				
		//한국과 해당게시판의 나라별 시간. jsp에서 각각 ${time[0]} 과 ${time[2]} 
		ArrayList<String> time = PostDAO.getInstance().getSysdateAndLocalTime(postNo);
		request.setAttribute("time", time);
		
		/*
		 * comment list 가져오기 
		 * id와 postNo 사용 
		 */
		ArrayList<CommentVO> commentList = CommentDAO.getInstance().getCommentListByPostNo(postNo);
		System.out.println(commentList);
		if(commentList!=null) {
			//comment list --> post-detail.jsp
			request.setAttribute("commentList", commentList);			
		} else {
			request.setAttribute("commentList", null);	
		}

		int totalLike = LikeDAO.getInstance().totalCount(postNo);
		LikeVO lvo = LikeDAO.getInstance().check(mvo.getId(), postNo);
		System.out.println(totalLike);
		System.out.println("test:"+lvo);
		System.out.println(pvo);
		
		request.setAttribute("lvo", lvo);
		request.setAttribute("totalLike", totalLike);
		request.setAttribute("count", countryCount);
		request.setAttribute("pvo", pvo);
		request.setAttribute("fvo", fvo);
		request.setAttribute("country", cvo);
		request.setAttribute("urlCountry", "/template/countryInfo.jsp");
		request.setAttribute("url", "/board/post-detail.jsp");
		System.out.println("PostDetailNoHitsController 끝");
		return "/template/layout.jsp";
	}
}
