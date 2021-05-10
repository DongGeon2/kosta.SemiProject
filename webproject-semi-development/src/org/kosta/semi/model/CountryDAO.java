package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CountryDAO {
	private static CountryDAO instance = new CountryDAO();
	private DataSource dataSource;
	
	public CountryDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	
	public static CountryDAO getInstance() {
		return instance;
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

	public CountryVO findCountryId(String countryName) throws SQLException {
		CountryVO cvo = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			String sql = "SELECT country_id FROM country WHERE country_name=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, countryName);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cvo = new CountryVO();
				cvo.setCountryId(rs.getString(1));
			}
			
		}finally {
			closeAll(rs, pstmt, con);
		}
		return cvo;
	}
	
	
}
