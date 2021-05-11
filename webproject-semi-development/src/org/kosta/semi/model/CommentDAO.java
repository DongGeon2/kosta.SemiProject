package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CommentDAO {
	private static CommentDAO dao=new CommentDAO();
	private DataSource dataSource;
	private CommentDAO(){
		//dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static CommentDAO getInstance(){		
		return dao;
	}
	public void closeAll(PreparedStatement pstmt,
			Connection con) throws SQLException{
		closeAll(null,pstmt,con);
	}
	public void closeAll(ResultSet rs,PreparedStatement pstmt,
			Connection con) throws SQLException{
		if(rs!=null)
			rs.close();
		if(pstmt!=null)
			pstmt.close();
		if(con!=null)
			con.close();
	}
	/**
	 * 댓글 작성
	 * @param cvo
	 * @param member_id
	 * @param commentContent
	 * @throws SQLException
	 * 
	 * INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) 
	 * VALUES(postcomment_seq.nextval, '2', 'mvc', ' 수정 감사드립니다!', sysdate);
	 */
	public void commentPosting(CommentVO cvo, String member_id, String commentContent ) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) ");
			sql.append("VALUES(postcomment_seq.nextval, ?, ?, ?, sysdate) ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, cvo.getPostVO().getPostNo());
			pstmt.setString(2, member_id);
			pstmt.setString(3, commentContent);
			pstmt.executeUpdate();
			pstmt.close();
			String sql2 = "SELECT postcomment_seq.currval from dual";
			pstmt=con.prepareStatement(sql2);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				cvo.setCommentNo(rs.getString(1));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
	}
	/**
	 * 댓글 삭제
	 * delete postcomment where comment_no = '36';
	 * @param commentId
	 * @throws SQLException
	 */
	public void commentDelete(String commentId) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con= dataSource.getConnection();
			String sql = "delete postcomment where comment_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, commentId);
			pstmt.executeUpdate();
		}finally {
			closeAll(pstmt, con);
		}
	}
}







