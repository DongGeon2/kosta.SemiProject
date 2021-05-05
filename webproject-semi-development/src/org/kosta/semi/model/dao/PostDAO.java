package org.kosta.semi.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

import org.kosta.semi.model.vo.CountryVO;
import org.kosta.semi.model.vo.MemberVO;
import org.kosta.semi.model.vo.PostVO;

public class PostDAO {
	private static PostDAO dao=new PostDAO();
	private DataSource dataSource;
	private PostDAO(){
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
	public ArrayList<PostVO> getPostingAll() throws SQLException{
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT p.post_no, c.country_name, p.category_name,p.post_title,p.member_id,  ");
			sql.append(" to_char(time_posted, 'YYYY.MM.DD') as time_posted, p.hits ");
			sql.append(" FROM post p, country c WHERE p.country_id=c.country_id");
			pstmt=con.prepareStatement(sql.toString());
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
}
