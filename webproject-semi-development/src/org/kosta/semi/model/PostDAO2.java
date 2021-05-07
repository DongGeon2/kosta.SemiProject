package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class PostDAO2 {
	private static PostDAO2 dao=new PostDAO2();
	private DataSource dataSource;
	private PostDAO2(){
		//dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static PostDAO2 getInstance(){		
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
	 * getPostingByNo
	 * 상세 글 보기 (post-detail.jsp) 와 연동
	 * @param postNo
	 * @return
	 * @throws SQLException
	 */
	public PostVO getPostingByNo(String postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PostVO pvo = null;
		try{
			con=dataSource.getConnection();
			StringBuilder sql=new StringBuilder();
			sql.append("SELECT p.post_no, p.hits,p.member_id, p.category_name, ");	
			sql.append("to_char(time_posted, 'YYYY.MM.DD') as time_posted, ");	
			sql.append("p.post_title, p.content, m.country_id, c.country_name ");	
			sql.append("FROM  post p, member m, country c ");	
			sql.append("WHERE p.member_id = m.member_id AND p.country_id = c.country_id  AND p.post_no = ?");	
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, Integer.parseInt(postNo));
			rs=pstmt.executeQuery();		
			if(rs.next()){
				pvo=new PostVO();
				pvo.setPostNo(postNo);
				pvo.setHits(rs.getInt("hits"));
				
				MemberVO mvo=new MemberVO();
				mvo.setId(rs.getString("member_id"));
				pvo.setMemberVO(mvo);
				
				CountryVO cvo = new CountryVO();
				cvo.setCountryId("country_id");
				cvo.setCountryName("country_name");
				pvo.setCountryVO(cvo);
				
				pvo.setCatergory(rs.getString("category_name"));
				pvo.setPostTime(rs.getString("time_posted"));
				pvo.setPostTitle(rs.getString("post_title"));
				pvo.setPostContent(rs.getString("content"));
			}			
		}finally{
			closeAll(rs,pstmt,con);
		}
		return pvo;
	}
	/**
	 * 조회수 UPDATE 
	 * @param postNo
	 * @throws SQLException
	 */
	public void updateHit(String postNo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con=dataSource.getConnection();
			String sql = "UPDATE post SET hits=hits+1 WHERE post_no = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, postNo);
			pstmt.executeQuery();
		}finally {
			closeAll(pstmt,con);
		}
	}
}
