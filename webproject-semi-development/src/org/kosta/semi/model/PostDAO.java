package org.kosta.semi.model;

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
	public ArrayList<PostVO> getAllPostingList(PagingBean pagingBean) throws SQLException{
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
	

		public ArrayList<PostVO> getPostingListByCountry(PagingBean pagingBean,String countryId) throws SQLException {
		      ArrayList<PostVO> list = new ArrayList<PostVO>();
		      Connection con = null;
		      PreparedStatement pstmt = null;
		      ResultSet rs = null;
		      try {
		         con=dataSource.getConnection();
		         StringBuilder sql = new StringBuilder();
		         sql.append(" SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits ");
		         sql.append(" FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title , member_id, hits, ");
		         sql.append(" country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post WHERE country_id=? ) p, country c ");
		         sql.append(" WHERE p.country_id=c.country_id AND rnum BETWEEN ? AND ? ");
		         pstmt=con.prepareStatement(sql.toString());
		         pstmt.setString(1, countryId);
		         pstmt.setInt(2, pagingBean.getStartRowNumber());
		         pstmt.setInt(3, pagingBean.getEndRowNumber());
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
		            cvo.setCountryId(countryId);
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
	
	public ArrayList<PostVO> getMyPostingList(PagingBean pagingBean, String id) throws SQLException{
		//서브쿼리 작성후 ROWNUM 사용
		System.out.println("PostDAO - getMyPostingList()메서드 접근");
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ROWNUM, x.* ");
			sql.append("FROM (SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, to_char(p.time_posted, 'YYYY.MM.DD') as time_posted , p.hits ");
			sql.append("FROM post p, country c ");
			sql.append("WHERE p.country_id=c.country_id AND p.member_id=? ");
			sql.append("ORDER BY p.post_no) x ");
			sql.append("WHERE ROWNUM BETWEEN ? AND ? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PostVO vo = new PostVO();
				vo.setPostNo(rs.getString(1));
				vo.setPostTitle(rs.getString(5));
				vo.setPostContent(null);
				vo.setHits(rs.getInt(8));
				vo.setPostTime(rs.getString(7));
				MemberVO mvo = new MemberVO();
				mvo.setId(id);		
				vo.setMemberVO(mvo);
				CountryVO cvo = new CountryVO();
				cvo.setCountryName(rs.getString(3));
				vo.setCountryVO(cvo);
				vo.setCatergory(rs.getString(4));
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
			String sql="select count(*) from post";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}
	public int getMyTotalPostCount(String id) throws SQLException{
		System.out.println("PostDAO - getMyTotalPostCount()메서드 접근");
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from post where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		System.out.println("totalCount: " +totalCount);
		return totalCount;
	}
	public int getCountryPostCount(String countryId) throws SQLException {
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from post where country_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, countryId);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}
	public void updatePosting(PostVO vo) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			pstmt=con.prepareStatement("update board set title=?,content=? where no=?");
			pstmt.setString(1, vo.getPostTitle());
			pstmt.setString(2, vo.getPostContent());
			pstmt.setString(3, vo.getPostNo());	
			pstmt.executeUpdate();			
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 특정 칼럼(column)에 특정 문자열(keyWord) 을 포함하는 로우(게시물) 검색
	 * getAllPostingList()활용
	 * @author 지은
	 * @param keyWord
	 * @return resultList
	 * @throws SQLException 
	 */
	//제목으로 검색했을때 결과 목록
	//매개변수 pagingBean, column 추가해서 구현해야
	public ArrayList<PostVO> searchByKeyWord(PagingBean pagingBean, String keyWord) throws SQLException {
		System.out.println("PostDAO 메서드searchByKeyWord접근");
		ArrayList<PostVO> resultList = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con=dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ROWNUM, x.* ");
			sql.append("FROM (SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, to_char(p.time_posted, 'YYYY.MM.DD') as time_posted, p.hits ");
			sql.append("FROM post p, country c ");
			sql.append("WHERE p.country_id=c.country_id ");
			sql.append("AND post_title LIKE '%' || ? || '%' ");
			sql.append("ORDER BY p.post_no) x ");
			sql.append("WHERE ROWNUM BETWEEN ? AND ? ");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, keyWord);
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				PostVO vo = new PostVO();
				vo.setPostNo(rs.getString(1));
				vo.setPostTitle(rs.getString(5));
				vo.setPostContent(null);
				vo.setHits(rs.getInt(8));
				vo.setPostTime(rs.getString(7));
				MemberVO mvo = new MemberVO();
				mvo.setId(rs.getString(6));		
				vo.setMemberVO(mvo);
				CountryVO cvo = new CountryVO();
				cvo.setCountryName(rs.getString(3));
				vo.setCountryVO(cvo);
				vo.setCatergory(rs.getString(4));
				resultList.add(vo);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return resultList;
	}
	//제목으로 검색결과 게시물수
	public int getSearchByKeyWordTotalPostCount(String keyWord) throws SQLException{
		System.out.println("PostDAO - getMyTotalPostCount()메서드 접근");
		int totalCount=0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="SELECT COUNT(*) FROM post WHERE post_title LIKE '%' || ? || '%' ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyWord);
			rs=pstmt.executeQuery();
			if(rs.next())
				totalCount=rs.getInt(1);
		}finally {
			closeAll(rs, pstmt, con);
		}
		System.out.println("totalCount: " +totalCount);
		return totalCount;
	}
}
