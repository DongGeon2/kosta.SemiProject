package org.kosta.semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.kosta.semi.model.CountryVO;
import org.kosta.semi.model.DataSourceManager;
import org.kosta.semi.model.MemberVO;
import org.kosta.semi.model.PostVO;


public class PostDAO {
	private static PostDAO dao=new PostDAO();
	private DataSource dataSource;
	private PostDAO(){
		//dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static PostDAO getInstance(){		
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
	/*
SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits
FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title,
member_id, hits, country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post) p, country c
WHERE p.country_id=c.country_id AND rnum BETWEEN 1 AND 5		
	 */
	public ArrayList<PostVO> getPostingAllList(PagingBean pagingBean) throws SQLException{
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits  ");
			sql.append(" FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title, ");
			sql.append(" member_id, hits, country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post) p, country c");
			sql.append(" WHERE p.country_id=c.country_id AND rnum BETWEEN ? AND ? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PostVO vo = new PostVO();
				vo.setPostNo(rs.getString(1));
				vo.setPostTitle(rs.getString(4));
				vo.setPostContent(null);
				vo.setHits(rs.getInt(7));
				vo.setPostTime(rs.getString(6));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString(5));		
				vo.setMemberVO(mvo);
				CountryVO cvo = new CountryVO();
				cvo.setCountryName(rs.getString(2));
				vo.setCountryVO(cvo);
				vo.setCatergory(rs.getString(3));
				list.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return list;
	}
	
	
	public int getTotalPostCount() throws SQLException{
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}
}