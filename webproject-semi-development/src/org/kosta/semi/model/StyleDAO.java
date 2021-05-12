package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class StyleDAO {
	private static StyleDAO dao = new StyleDAO();
	private DataSource dataSource;

	private StyleDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}

	public static StyleDAO getInstance() {
		return dao;
	}

	public void closeAll(PreparedStatement pstmt, Connection con) throws SQLException {
		closeAll(null, pstmt, con);
	}

	public void closeAll(ResultSet rs, PreparedStatement pstmt, Connection con) throws SQLException {
		if (rs != null)
			rs.close();
		if (pstmt != null)
			pstmt.close();
		if (con != null)
			con.close();
	}

	public ArrayList<MemberVO> findMemberBySvo(MemberVO mvo, StyleVO svo) throws SQLException {
		ArrayList<MemberVO> memberList = new ArrayList<MemberVO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("SELECT s.member_id,TRUNC(MONTHS_BETWEEN(sysdate, m.birth)/12) AS AGE, m.country_id ");
			sql.append("FROM style s, member m ");
			sql.append(
					"WHERE s.member_id=m.member_id AND m.country_id = ? AND s.style1 =? AND s.style2 =? AND s.style3 =? AND s.style4 =? ");
			pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getCountryVO().getCountryId());
			pstmt.setString(2, svo.getStyle1());
			pstmt.setString(3, svo.getStyle2());
			pstmt.setString(4, svo.getStyle3());
			pstmt.setString(5, svo.getStyle4());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MemberVO mvo2 = new MemberVO();
				mvo2.setId(rs.getString(1));
				mvo2.setAge(rs.getInt(2));
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return memberList;
	}

	public void registerStyle(String memberId, StyleVO svo) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = dataSource.getConnection();
			String sql = "INSERT INTO style VALUES (?,?,?,?,?) ";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberId);
			pstmt.setString(2, svo.getStyle1());
			pstmt.setString(3, svo.getStyle2());
			pstmt.setString(4, svo.getStyle3());
			pstmt.setString(5, svo.getStyle4());
			pstmt.executeUpdate();
		} finally {
			closeAll(pstmt, con);
		}
	}
}
