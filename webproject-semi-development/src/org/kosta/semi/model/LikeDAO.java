package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class LikeDAO {
	private static LikeDAO instance = new LikeDAO();
	private DataSource dataSource;
	private LikeDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static LikeDAO getInstance() {
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
	public LikeVO check(String memberId, String postNo) throws SQLException {
		LikeVO lvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT post_no FROM likedb ");
			sql.append("WHERE member_id=? AND post_no=?");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			pstmt.setString(2, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lvo = new LikeVO();
				PostVO pvo = new PostVO();
				pvo.setPostNo(rs.getString(1));
				lvo.setPostVO(pvo);
			}
		}finally {
			closeAll(con, pstmt, rs);
		}
		return lvo;
	}
	
	public void insertLike(String memberId, String postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO likedb(post_no,member_id) ");
			sql.append("VALUES (?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, postNo);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
		}finally {
			closeAll(con, pstmt);
		}
	}
	public void deleteLike(String memberId, String postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "DELETE FROM likedb WHERE member_id=? AND post_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, postNo);
			pstmt.executeUpdate();
		}finally {
			closeAll(con, pstmt);
		}
	} 
	public int totalCount(String postNo) throws SQLException {
		int totalCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT count(*) FROM likedb WHERE post_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, postNo);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalCount = Integer.parseInt(rs.getString(1));
			}
		}finally {
			closeAll(con, pstmt, rs);
		}
		return totalCount;
	}
}
