package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	   
	   /**
	    * 가입된 총 회원수를 반환
	    * @return int
	    * @throws SQLException
	    */
	   public int getTotalMemberCount() throws SQLException {
			int totalCount = 0;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = dataSource.getConnection();
				String sql = "select count(*) from member where state>0";
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
		 * 멤버 리스트 불러오기
		 */
		public ArrayList<MemberVO> getAllMemberList(PagingBean pagingBean) throws SQLException {
			ArrayList<MemberVO> list = new ArrayList<MemberVO>();
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				con = dataSource.getConnection();
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT m.member_id, m.name, m.travel_style, c.country_name ");
				sql.append("FROM (SELECT row_number() over(ORDER BY member_id DESC) as rnum,  member_id, name, ");
				sql.append("travel_style, country_id FROM member where state>0) m, country c ");
				sql.append("WHERE m.country_id=c.country_id AND rnum BETWEEN ? AND ?");
				pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, pagingBean.getStartRowNumber());
				pstmt.setInt(2, pagingBean.getEndRowNumber());
				rs = pstmt.executeQuery();
				while (rs.next()) {
					MemberVO mvo = new MemberVO();
					mvo.setId(rs.getString(1));
					mvo.setName(rs.getString(2));
					mvo.setTravelStyle(rs.getString(3));
					CountryVO cvo = new CountryVO();
					cvo.setCountryName(rs.getString(4));
					mvo.setCountryVO(cvo);
					list.add(mvo);
				}
			} finally {
				closeAll(rs, pstmt, con);
			}
			return list;
		}
}
