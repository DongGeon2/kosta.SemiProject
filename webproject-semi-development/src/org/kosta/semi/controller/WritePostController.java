package org.kosta.semi.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.kosta.semi.model.CountryDAO;
import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.FileDAO;
import org.kosta.semi.model.FileVO;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostDAO;
import org.kosta.semi.model.PostVO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
/**
 * WritePostController
 * 글쓰기 FORM 에서 글 작성 기능 Controller
 * @author SUE
 *
 */
public class WritePostController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		MultipartRequest multi = null;
		int sizeLimit = 10*1024*1024; 			
		String path = request.getContextPath();
		String savePath = "C:\\kosta215\\git\\semiproject-v1\\kosta.SemiProject\\webproject-semi-development\\WebContent\\upload";
		try {
			multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
			System.out.println("try문");
		}catch(Exception e) {
			System.out.println("에러나옴");
			e.printStackTrace();
		}
//		Enumeration<String> enums = request.getAttributeNames();
//	
//		while(enums.hasMoreElements()) {
//			System.out.println("여기");
//			String key = enums.nextElement();
//			System.out.println("key = " + request.getParameter(key));
//		}
		
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null||request.getMethod().equals("POST")==false) {
			System.out.println("세션 널");
			return "redirect:index.jsp";
		}else {
			System.out.println("세션존재");
			MemberVO mvo = (MemberVO)session.getAttribute("mvo"); 
			//나라 id,name 갖고오는거 MultipartReqeust 말고 MemberVO의 세션을 이용해도됨.
			//MemberVO의 세션을 이용하면 로그인한 사람의 나라가 선택이 아니라 확정될수 있음
			String countryName = multi.getParameter("countryname");
			System.out.println(countryName);
			
			CountryVO cvo = CountryDAO.getInstance().findCountryName(countryName);
			System.out.println(cvo.getCountryId());
			
			PostVO pvo = new PostVO();
			pvo.setMemberVO(mvo);
			pvo.setPostTitle(multi.getParameter("postTitle"));
			pvo.setPostContent(multi.getParameter("postContent"));
			pvo.setCatergory(multi.getParameter("catergory"));
			pvo.setCountryVO(cvo);
			System.out.println(pvo.getPostNo()+","+pvo.getPostTitle()+","+pvo.getPostContent()+","+pvo.getCatergory()+","+pvo.getCountryVO().getCountryId());
			System.out.println(pvo);
			System.out.println("PostDAO 전");
			PostDAO.getInstance().writeContent(pvo);//글쓰면 DB에 정보 들어가게하는 DAO
			System.out.println(pvo.getPostNo());
			System.out.println("글쓰기완료");
			
			//--------------------------------------------------------------------------------------------------------
			
			
			System.out.println(path);
			System.out.println(request.getRequestURI());
			
			String filename = multi.getFilesystemName("filename");
			String orgName = multi.getOriginalFileName("filename");
			long fileSize = multi.getFile("filename").length();
			System.out.println(orgName+","+filename+","+fileSize);
			System.out.println(savePath);
//			String regip = request.getRemoteAddr(); // 요청을 보낸사람의 IP 주소
			
			
			String postNo=pvo.getPostNo();
			/*
			 	inserFile메서드를 통해 filedb에 db정보들을 삽입 시킴.
			 */
			FileDAO.getInstance().insertFile(postNo, orgName, filename, savePath, fileSize);
			System.out.println("ㅇㅇ");
			
			//filedb 모두 조회.
			//ArrayList<FileVO> list = FileDAO.getInstance().getAllFile();

			
			FileVO fvo = new FileVO();
			System.out.println("파일 조회전 "+filename);
			/*
				fvo 객체 만들고 FileDAO.getFile(postNo, filename) 메서드로 저장한 파일명에 해당하는
				정보들을 조회한뒤 있으면 fvo객체에 넣어줌.
				넣어주는 이유는 아래 return값을 통한 page 이동시 redirect로 이동해서
				이동한 Controller or jsp 에서는 request 를 할 수 없음.
				따라서 쿼리스트링으로 fvo값을 return값에 넣어줌.
			*/
			fvo = FileDAO.getInstance().getFile(postNo, filename);
			
			
			//파일이 업로드된 경로+파일 이름을 file 객체에 담음. 왜담을까? 생각해보자...
			File file = multi.getFile("filename");
			System.out.println(file);
			System.out.println(pvo.getMemberVO().getId());
			System.out.println(fvo);
			
			return "redirect:DetailPostController.do?postNo="+pvo.getPostNo()+"&"+"name="+fvo.getFileName();
		}
	}

}
