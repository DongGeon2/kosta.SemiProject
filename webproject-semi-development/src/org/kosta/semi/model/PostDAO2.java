package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class PostDAO2 {
	private static PostDAO2 dao=new PostDAO2();
	private DataSource dataSource;
	private PostDAO2(){
		//dataSource=DataSourceManager.getInstance().getDataSource();
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static PostDAO2 getInstance(){		
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
	public PostVO getPostingByNo(String postNo) {
		// TODO Auto-generated method stub
		return null;
	}
}
