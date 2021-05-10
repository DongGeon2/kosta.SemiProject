package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

public class ManagerDAO {
	/**
	 * ManagerDAO에서는 매니저 정보 관리에 관한 모든 메서드를 정의합니다
	 */
	private static ManagerDAO dao=new ManagerDAO();
	private DataSource dataSource;
	private ManagerDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static ManagerDAO getInstance(){		
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
	/**
	    * 매니저 정보로 로그인
	    * @param id
	    * @param password
	    * @return ManagerVO-패스워드 제외한 모든 정보
	    * @throws SQLException
	    */
	   public ManagerVO login(String id,String password) throws SQLException{
		  ManagerVO managerVO=null;
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try{
	         con=dataSource.getConnection();
	         StringBuffer sql= new StringBuffer();
	         sql.append("select name ");
	         sql.append("from manager ");
	         sql.append("where manager_id=? and password=?");
	         pstmt=con.prepareStatement(sql.toString());
	         pstmt.setString(1, id);
	         pstmt.setString(2, password);
	         rs=pstmt.executeQuery();
	         if(rs.next()){
	            managerVO = new ManagerVO(id,null,rs.getString(1));
	         }
	      }finally{
	         closeAll(rs, pstmt,con);
	      }
	      return managerVO;
	   }
}
