package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class CountryDAO {
	/**
	 * CountryDAO에서는 나라정보관리에 관한 모든 메서드를 정의합니다
	 */
	private static CountryDAO dao=new CountryDAO();
	private DataSource dataSource;
	private CountryDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static CountryDAO getInstance(){		
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
}
