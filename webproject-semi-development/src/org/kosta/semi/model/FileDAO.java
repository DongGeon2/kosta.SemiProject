package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class FileDAO {
	private static FileDAO instance = new FileDAO();
	private DataSource dataSource;
	
	public FileDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static FileDAO getInstance() {
		System.out.println("FileDAO 객체생성");
		return instance;
	}
	
	public void closeAll(Connection con , PreparedStatement pstmt) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close();
	}
	public void closeAll(Connection con , PreparedStatement pstmt , ResultSet rs) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(con, pstmt);
	}
	public void insertFile(String postNo, String orgName, String filename, String savePath, long fileSize) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO filedb(file_id, post_no, org_name, file_name, file_path, file_size, fdate) ");
			sql.append("VALUES (file_seq.nextval, ?, ?, ?, ?, ?, sysdate)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, postNo);
			pstmt.setString(2, orgName);
			pstmt.setString(3, filename);
			pstmt.setString(4, savePath);
			pstmt.setLong(5, fileSize);
			pstmt.executeUpdate();
		}finally{
			closeAll(con, pstmt);
		}
	}
	
	public ArrayList<FileVO> getAllFile() throws SQLException {
		ArrayList<FileVO> list = new ArrayList<FileVO>();
		FileVO fvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT id, org_name, file_name, file_path, file_size, fdate ");
			sql.append("FROM filedb");
			pstmt = con.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fvo = new FileVO();
				fvo.setId(rs.getString(1));
				fvo.setOrgName(rs.getString(2));
				fvo.setFileName(rs.getString(3));
				fvo.setFilePath(rs.getString(4));
				fvo.setFileSize(rs.getString(5));
				fvo.setFdate(rs.getString(6));
				list.add(fvo);
			}
		}finally {
			closeAll(con, pstmt, rs);
		}
		return list;
	}
	
	public FileVO getFile(String postNo, String fileName) throws SQLException {
		FileVO fvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
//			String sql = "SELECT id, post_no, org_name, file_path, file_size, fdate FROM filedb WHERE file_name=?";
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT file_id, org_name, file_path, file_size, fdate, post_no ");
			sql.append("FROM filedb ");
			sql.append("WHERE file_name=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, fileName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fvo = new FileVO();
				fvo.setId(rs.getString(1));
				fvo.setOrgName(rs.getString(2));
				fvo.setFileName(fileName);
				fvo.setFilePath(rs.getString(3));
				fvo.setFileSize(rs.getString(4));
				fvo.setFdate(rs.getString(5));
				PostVO pvo = new PostVO();
				pvo.setPostNo(rs.getString(6));
				fvo.setPostVO(pvo);
				System.out.println(fvo);
			}
			System.out.println(fileName+" 찾음");
		}finally {
			closeAll(con, pstmt, rs);
		}
		return fvo;
	}
	
	public String getFileName(String postNo) throws SQLException {
		String fileName=null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT file_name FROM filedb WHERE post_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				FileVO fvo = new FileVO();
				fvo.setFileName(rs.getString(1));
				fileName=fvo.getFileName();
			}
		}finally {
			closeAll(con, pstmt, rs);
		}
		return fileName;
	}
}
