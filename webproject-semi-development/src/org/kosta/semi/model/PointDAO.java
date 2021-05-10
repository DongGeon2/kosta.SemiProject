package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.sql.DataSource;

public class PointDAO {
	private static PointDAO dao=new PointDAO();
	private DataSource dataSource;
	private PointDAO(){
		//dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static PointDAO getInstance(){		
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
	public ArrayList<PostVO> getPostingForPoint() {
		// TODO Auto-generated method stub
		return null;
	}

}
