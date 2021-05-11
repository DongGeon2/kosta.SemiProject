package org.kosta.semi.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kosta.semi.model.FileDAO;
import org.kosta.semi.model.FileVO;

public class DownloadController implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String fileName = request.getParameter("fileName"); //다운로드 시켜줄 파일 이름
		String postNo = request.getParameter("postNo"); //다운로드 시켜줄 파일의 게시글 번호
		FileVO fvo = FileDAO.getInstance().getFile(postNo, fileName); //특정파일DB 뽑기
		String orgFileName= fvo.getOrgName(); //원래 파일명
		String saveFileName= fvo.getFileName(); //저장 파일명
//		String path = application.getRealPath("/upload")+File.separator+saveFileName; 
		
		//다운로드 시켜줄 실제 경로
		/*
		 * 만약 db에는 경로가 저장파일명이 저장돼있는데 실제 디렉토리에 파일이 없다면
		 * 여기서 에러가남. 따라서 저장경로에 해당파일이 없을땐 fileDAO 메서드를 하나 만들어서
		 * 존재하지 않는 파일이름이 들어간 튜플을 지워줘야함.
		 * 그리고 다시 post-detail.jsp가 새로고침되면서 해당게시물에 첨부파일이 사라지도록 코딩하면 좋을꺼같음.
		 */
		String path = request.getServletContext().getRealPath("upload")+File.separator+saveFileName;
		//다운로드 할 파일을 일거올 스트림 객체 생성
		FileInputStream fis = new FileInputStream(path);
		System.out.println(fis);
		
		//다운로드 시켜주는 작업 -> 실제 파일 데이터와 원본파일명을 보내기.
		String encodedName=null;
		System.out.println(request.getHeader("User-Agent")); //브라우저 정보 가저오기
		
		//한글 파일명 세부처리
		encodedName = URLEncoder.encode(orgFileName,"utf-8");
		encodedName = encodedName.replace("\\+"," "); //파일명에 공백이 있는 경우 처리
		
		//응답 헤더 정보 설정
		response.setHeader("Content-Disposition", "attachment;filename="+encodedName);
		response.setHeader("Content-Transfer-Encoding", "binary"); 
		response.setContentLength(Integer.parseInt(fvo.getFileSize()));
		
		//클라이언트에게 출력할수 있는 스트림 객체 얻어오기
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024*1000]; // 한번에 최대 1Mbyte 씩 읽어올수 있는 버퍼
		int readedByte=0;
		//반복문 돌면서 출력
		while(true) {
			//byte[] 객체를 이용해서 파일에 byte 내용 읽어오기
			readedByte = fis.read(buffer);
			if(readedByte == -1 ) 
				break; // 더이상 읽을 데이터 없다면 탈출~
			//읽은 만큼 출력하기
			bos.write(buffer, 0, readedByte);
			bos.flush(); //출력
		}
		//스트림 닫기
		bos.close();
		fis.close();
		
		/*
		 * 첨부파일 클릭 후 다운로드가 실행되면 이컨트롤러가 실행되는데 이때 컨트롤러의 리턴값이 없어서
		 * 에러가 뜨지만 실행은 정상적으로 된다.
		 * 제어를 벗어난 에러를 뜨게 하기 싫으면 예외를 발생시켜주거나.. 다른 컨트롤러 or jsp로 이동시켜야할듯?
		 */
		return null;
	}

}
