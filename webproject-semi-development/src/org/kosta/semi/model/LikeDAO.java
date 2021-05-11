package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class LikeDAO {
	private static LikeDAO instance = new LikeDAO();
	private DataSource dataSource;
	private LikeDAO() {
		dataSource = DataSourceManager.getInstance().getDataSource();
	}
	public static LikeDAO getInstance() {
		return instance;
	}
	
	public void closeAll(Connection con , PreparedStatement pstmt) throws SQLException {
		if(pstmt != null)
			pstmt.close();
		if(con != null)
			con.close();
	}
	public void closeAll(Connection con , PreparedStatement pstmt , ResultSet rs) throws SQLException {
		if(rs != null)
			rs.close();
		closeAll(con, pstmt);
	}
}
