package org.kosta.semi.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		HttpSession session = request.getSession(false);
		if(session==null||session.getAttribute("mvo")==null||request.getMethod().equals("POST")==false) {
			System.out.println("세션 널");
			return "redirect:index.jsp";
		}else {
			MultipartRequest multi = null;
			int sizeLimit = 10*1024*1024; 			
			//String path = request.getContextPath();
			String savePath = "C:\\kosta215\\git\\semiproject-v1\\kosta.SemiProject\\kosta.SemiProject\\webproject-semi-development\\WebContent\\upload";
			try {
				multi = new MultipartRequest(request, savePath, sizeLimit, "utf-8", new DefaultFileRenamePolicy());
				System.out.println("try문");
			}catch(Exception e) {
				System.out.println("에러나옴");
				e.printStackTrace();
			}
			System.out.println("세션존재");
			MemberVO mvo = (MemberVO)session.getAttribute("mvo"); 
			//나라 id,name 갖고오는거 MultipartReqeust 말고 MemberVO의 세션을 이용해도됨.
			//MemberVO의 세션을 이용하면 로그인한 사람의 나라가 선택이 아니라 확정될수 있음
			String countryId = multi.getParameter("countryId");
			System.out.println(countryId);
			
//			CountryVO cvo = CountryDAO.getInstance().findCountryName(countryName);
//			System.out.println(cvo.getCountryId());
			
			CountryVO cvo = new CountryVO();
			cvo.setCountryId(countryId);
			
			PostVO pvo = new PostVO();
			pvo.setMemberVO(mvo);
			pvo.setPostTitle(multi.getParameter("postTitle"));
			pvo.setPostContent(multi.getParameter("postContent"));
			pvo.setCatergory(multi.getParameter("catergory"));
			pvo.setCountryVO(cvo);
			//제대로 set이됐나 확인
			System.out.println(pvo.getPostNo()+","+pvo.getPostTitle()+","+pvo.getPostContent()+","+pvo.getCatergory()+","+pvo.getCountryVO().getCountryId());
			PostDAO.getInstance().writeContent(pvo);//글쓰면 DB에 정보 들어가게하는 DAO
			System.out.println(pvo.getPostNo());
			System.out.println("글쓰기완료");
			
			//--------------------------------------------------------------------------------------------------------
			
			String filename = multi.getFilesystemName("filename");//저장된 파일명
			/*
			 * 만약 filename이 없다면 파일이 없는거임. 따라서 아래 else구문의 multi 객체를 통해
			 * orgName(원래파일명)과 fileSize등을 통해 filedb에 file관련 값들을 넣어줄 필요없으므로
			 * 바로 redirect를 통해 PostDetailController로 이동
			 */
			if(filename == null || filename == "") {
				return "redirect:PostDetailController.do?postNo="+pvo.getPostNo()+"&countryId="+countryId;
			} else {
				String orgName = multi.getOriginalFileName("filename");
				long fileSize = multi.getFile("filename").length();
				System.out.println(orgName+","+filename+","+fileSize);
				System.out.println(savePath);
				//String regip = request.getRemoteAddr(); // 요청을 보낸사람의 IP 주소
				
				
				String postNo=pvo.getPostNo();
				/*
				 	inserFile메서드를 통해 filedb에 db정보들을 삽입 시킴.
				 */
				FileDAO.getInstance().insertFile(postNo, orgName, filename, savePath, fileSize);
				
				//filedb 모두 조회.
				//ArrayList<FileVO> list = FileDAO.getInstance().getAllFile();

				
				/*
					fvo 객체 만들고 FileDAO.getFile(postNo, filename) 메서드로 저장한 파일명에 해당하는
					정보들을 조회한뒤 있으면 fvo객체에 넣어줌.
					넣어주는 이유는 아래 return값을 통한 page 이동시 redirect로 이동해서
					이동한 Controller or jsp 에서는 request 를 할 수 없음.
					따라서 쿼리스트링으로 fvo값을 return값에 넣어줌.
				 */
				FileVO fvo = new FileVO();
				fvo = FileDAO.getInstance().getFile(postNo, filename);
				
				
				//파일이 업로드된 경로+파일 이름을 file 객체에 담음. 왜담을까? 생각해보자...
				File file = multi.getFile("filename");
				System.out.println(file);
//				System.out.println(pvo.getMemberVO().getId());
//				System.out.println(fvo);
				
				return "redirect:PostDetailController.do?postNo="+pvo.getPostNo()+"&fileName="+fvo.getFileName()+"&countryId="+countryId;
			}
		}
	}
}
