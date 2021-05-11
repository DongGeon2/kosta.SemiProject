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
	private static PostDAO dao = new PostDAO();
	private DataSource dataSource;


	private PostDAO() {
		// dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource = DataSourceManager.getInstance().getDataSource();
	}


	public static PostDAO getInstance() {
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
	 * SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id,
	 * p.time_posted, p.hits FROM (SELECT row_number() over(ORDER BY post_no DESC)
	 * as rnum, post_no,post_title, member_id, hits, country_id, category_name,
	 * to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post) p, country c
	 * WHERE p.country_id=c.country_id AND rnum BETWEEN 1 AND 5
	 */
	public ArrayList<PostVO> getAllPostingList(PagingBean pagingBean) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					"SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits  ");
			sql.append(" FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title, ");
			sql.append(
					" member_id, hits, country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post) p, country c");
			sql.append(" WHERE p.country_id=c.country_id AND rnum BETWEEN ? AND ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, pagingBean.getStartRowNumber());
			pstmt.setInt(2, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public ArrayList<PostVO> getPostingListByCountry(PagingBean pagingBean, String countryId) throws SQLException {
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits ");
			sql.append(
					" FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title , member_id, hits, ");
			sql.append(
					" country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post WHERE country_id=? ) p, country c ");
			sql.append(" WHERE p.country_id=c.country_id AND rnum BETWEEN ? AND ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, countryId);
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	/**
	 * id파라미터에 받아온 현재 세션의 id를 가진 회원의 작성글 목록을 가져오는 메서드.
	 * 
	 * @author 지은
	 * @param pagingBean, id
	 * @return list
	 * @throws SQLException
	 **/
	public ArrayList<PostVO> getMyPostingList(PagingBean pagingBean, String id) throws SQLException {
		// 서브쿼리 작성후 ROWNUM 사용
		ArrayList<PostVO> list = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ROWNUM, x.* ");
			sql.append(
					"FROM (SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, to_char(p.time_posted, 'YYYY.MM.DD') as time_posted , p.hits ");
			sql.append("FROM post p, country c ");
			sql.append("WHERE p.country_id=c.country_id AND p.member_id=? ");
			sql.append("ORDER BY p.post_no) x ");
			sql.append("WHERE ROWNUM BETWEEN ? AND ? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			pstmt.setInt(2, pagingBean.getStartRowNumber());
			pstmt.setInt(3, pagingBean.getEndRowNumber());
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	public int getTotalPostCount() throws SQLException {
		int totalCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from post";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}
	/**
	 * 한회원이 작성한 총 게시물 수를 구하는 메서드. 
	 * getMyPostingList() 메서드의 매개변수로 넘겨줄 PagingBean 객체 생성을 하기 위함
	 * 
	 * @author 지은
	 * @param pagingBean, id
	 * @return list
	 * @throws SQLException
	 **/
	public int getMyTotalPostCount(String id) throws SQLException {
		int totalCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from post where member_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}

	public int getCountryPostCount(String countryId) throws SQLException {
		int totalCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "select count(*) from post where country_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, countryId);
			rs = pstmt.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}

	public void updatePosting(PostVO vo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			pstmt = con.prepareStatement("update board set title=?,content=? where no=?");
			pstmt.setString(1, vo.getPostTitle());
			pstmt.setString(2, vo.getPostContent());
			pstmt.setString(3, vo.getCatergory());
			pstmt.setString(4, vo.getPostNo());
			pstmt.executeUpdate();			
		} finally {
			closeAll(pstmt, con);
		}
	}
	/**
	 * 나라와 칼럼(column)을 선택후 검색 문자열(keyWord)을 포함하는 로우(게시물) 목록을 가져오는 메서드.
	 * getMyPostingList() 코드 가져와서 변경
	 * 
	 * @author 지은
	 * @param pagingBean, country_id, keyWord
	 * @return resultList
	 * @throws SQLException
	 */


	/**
	 * 나라/컬럼/검색어로 검색된 총 게시물 수를 구하는 메서드 SearchByKeyWord() 메서드의 매개변수로 넘겨줄 PagingBean
	 * 객체 생성을 하기 위함
	 * 
	 * @author 지은
	 * @param country_id, column, id
	 * @return totalCount
	 * @throws SQLException
	 **/
	


	/**
	 * 나라와 칼럼(column)을 선택후 검색 문자열(keyWord)을 포함하는 로우(게시물) 목록을 가져오는 메서드.
	 * getMyPostingList() 코드 가져와서 변경
	 * 
	 * @author 지은
	 * @param pagingBean, country_id, keyWord
	 * @return resultList
	 * @throws SQLException
	 */
	public ArrayList<PostVO> searchByKeyWord(PagingBean pagingBean, String country_id, String column, String keyWord)
			throws SQLException {
		System.out.println("PostDAO 메서드searchByKeyWord접근");
		ArrayList<PostVO> resultList = new ArrayList<PostVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT ROWNUM, x.* ");
			sql.append(
					"FROM (SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, to_char(p.time_posted, 'YYYY.MM.DD') as time_posted, p.hits ");
			sql.append("FROM post p, country c ");
			sql.append("WHERE p.country_id=c.country_id ");
			if (!country_id.equals("all")) {
				sql.append("AND p.country_id=? ");
			}
			sql.append("AND " + column + " LIKE '%' || ? || '%' ");
			sql.append("ORDER BY p.post_no) x ");
			sql.append("WHERE ROWNUM BETWEEN ? AND ? ");
			pstmt = con.prepareStatement(sql.toString());
			// country_id가 all(모든나라에 대해 검색)일경우 country_id WHERE절 제외
			if (!country_id.equals("all")) {
				pstmt.setString(1, country_id);
				pstmt.setString(2, keyWord);
				pstmt.setInt(3, pagingBean.getStartRowNumber());
				pstmt.setInt(4, pagingBean.getEndRowNumber());
			} else {
				pstmt.setString(1, keyWord);
				pstmt.setInt(2, pagingBean.getStartRowNumber());
				pstmt.setInt(3, pagingBean.getEndRowNumber());
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
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

	/**
	 * 나라/컬럼/검색어로 검색된 총 게시물 수를 구하는 메서드 SearchByKeyWord() 메서드의 매개변수로 넘겨줄 PagingBean
	 * 객체 생성을 하기 위함
	 * 
	 * @author 지은
	 * @param country_id, column, id
	 * @return totalCount
	 * @throws SQLException
	 **/
	public int getSearchByKeyWordTotalPostCount(String country_id, String column, String keyWord) throws SQLException {
		System.out.println("PostDAO - getSearchByKeyWordTotalPostCount()메서드 접근");
		int totalCount = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT COUNT(*) FROM post p ");
			sql.append("WHERE ");
			// country_id가 all(모든나라에 대해 검색)일경우 country_id WHERE절 제외
			if (!country_id.equals("all"))
				sql.append("p.country_id= ? AND ");
			sql.append(column + " LIKE '%' || ? || '%' ");
			pstmt = con.prepareStatement(sql.toString());
			if (!country_id.equals("all")) {
				pstmt.setString(1, country_id);
				pstmt.setString(2, keyWord);
			} else {
				pstmt.setString(1, keyWord);
			}
			rs = pstmt.executeQuery();
			if (rs.next())
				totalCount = rs.getInt(1);
		} finally {
			closeAll(rs, pstmt, con);
		}
		return totalCount;
	}


	
	public void writeContent(PostVO pvo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO post(post_no, hits, member_id, category_name, time_posted, post_title, content, country_id) ");
			sql.append("VALUES (post_seq.nextval, ?, ?, ?, sysdate, ?, ?, ?)");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setInt(1, 0);
			pstmt.setString(2, pvo.getMemberVO().getId());
			pstmt.setString(3, pvo.getCatergory());
			pstmt.setString(4, pvo.getPostTitle());
			pstmt.setString(5, pvo.getPostContent());
			pstmt.setString(6, pvo.getCountryVO().getCountryId());
			pstmt.executeUpdate();
			pstmt.close();
			String sql2 = "SELECT post_seq.currval FROM dual";
			pstmt = con.prepareStatement(sql2);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				//이 메서드가 끝나고 PostVO에 post_no이 컨트롤러에서 필요하므로 여기서 set을 해준다.
				pvo.setPostNo(rs.getString(1));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
	}
	public ArrayList<String> getSysdateAndLocalTime(String postNo) throws SQLException {
		ArrayList<String> time = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT to_char(p.time_posted,'YYYY.MM.DD PM HH12:MI') AS kor_time, c.country_time, ");
			sql.append(
					" to_char((p.time_posted + c.country_time/24), 'YYYY.MM.DD PM HH12:MI') as local_time ");
			sql.append(" FROM post p, country c WHERE p.country_id = c.country_id AND p.post_no =? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, postNo);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				time.add(rs.getString(1));
				time.add(rs.getString(2));
				time.add(rs.getString(3));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		System.out.println(time);
		return time;
	}
}
