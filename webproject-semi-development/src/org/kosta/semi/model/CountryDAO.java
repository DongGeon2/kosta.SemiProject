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

	public CountryVO findCountryName(String countryName) throws SQLException {
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
	/**
	 * ID로 나라에 대한 모든 정보를 찾아옵니다
	 * @param id
	 * @return CountryVO
	 * @throws SQLException
	 */
	public CountryVO findCountryById(String id) throws SQLException{
		CountryVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=dataSource.getConnection();
			String sql="select country_name,country_time,language,currency from country where country_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo=new CountryVO();
				vo.setCountryId(id);
				vo.setCountryName(rs.getString(1));
				vo.setCountryTime(rs.getString(2));
				vo.setLanguage(rs.getString(3));
				vo.setCurrency(rs.getString(4));
			}
		}finally{
			closeAll(rs, pstmt,con);
		}
		return vo;
		
	}
	public int findMemberCountByCountryname(String countryName) throws SQLException {
		int count = 0;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql =" SELECT count(*) FROM member m , country c  WHERE m.country_id=c.country_id AND country_name=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, countryName);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		} finally {
			closeAll(rs, pstmt, con);
		}
		return count;
	}

}

