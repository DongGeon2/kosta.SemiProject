package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PostDAO3 {
	private static PostDAO3 dao = new PostDAO3();
	private DataSource dataSource;


	private PostDAO3() {
		// dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource = DataSourceManager.getInstance().getDataSource();
	}


	public static PostDAO3 getInstance() {
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
	public ArrayList<String> getSysdateAndLocalTime(String postNo) throws SQLException {
		ArrayList<String> time = new ArrayList<String>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			StringBuilder sql = new StringBuilder();
			sql.append(
					" SELECT to_char(p.time_posted,'YYYY.MM.DD PM HH12:MI') AS kor_time, c.time_dif, ");
			sql.append(
					" to_char((p.time_posted + c.time_dif/24), 'YYYY.MM.DD PM HH12:MI') as local_time ");
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
