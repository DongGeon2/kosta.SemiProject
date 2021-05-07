package org.kosta.semi.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.sql.DataSource;


public class MemberDAO {
	/**
	 * MemberDAO에서는 회원정보관리에 관한 모든 메서드를 정의합니다
	 */
	private static MemberDAO dao=new MemberDAO();
	private DataSource dataSource;
	private MemberDAO(){
		dataSource=DataSourceManager.getInstance().getDataSource();
	}
	public static MemberDAO getInstance(){		
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
	    * 회원정보로 로그인
	    * @param id
	    * @param password
	    * @return MemberVO-패스워드 제외한 모든 정보
	    * @throws SQLException
	    */
	   public MemberVO login(String id,String password) throws SQLException{
	      MemberVO vo=null;
	      CountryVO cvo = null;
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try{
	         con=dataSource.getConnection();
	         StringBuffer sql= new StringBuffer();
	         sql.append("select m.name,m.gender,m.birth,m.email,m.travel_style,c.country_id,c.country_name ");
	         sql.append("from member m, country c ");
	         sql.append("where m.country_id=c.country_id and member_id=? and password=?");
	         pstmt=con.prepareStatement(sql.toString());
	         pstmt.setString(1, id);
	         pstmt.setString(2, password);
	         rs=pstmt.executeQuery();
	         if(rs.next()){
	            vo=new MemberVO();
	            cvo = new CountryVO();
	            vo.setId(id);
	            vo.setName(rs.getString(1));
	            vo.setGender(rs.getString(2));
	            vo.setBirth(rs.getString(3));
	            vo.setEmail(rs.getString(4));
	            vo.setTravelStyle(rs.getString(5));
	            cvo.setCountryId(rs.getString(6));
	            cvo.setCountryName(rs.getString(7));
	         }
	      }finally{
	         closeAll(rs, pstmt,con);
	      }
	      return vo;
	   }
	/**
	 * id로 회원 이름 조회 
	 * 회원가입시 아이디 중복 확인
	 * @param id
	 * @return id,name
	 * @throws SQLException
	 */
	public MemberVO findMemberById(String id) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try{
			con=dataSource.getConnection();
			String sql="select name from member where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				vo=new MemberVO();
				vo.setId(id);
				vo.setName(rs.getString(1));
			}
		}finally{
			closeAll(rs, pstmt,con);
		}
		return vo;
	}

	/**
	 * email로 회원 id 조회
	 * @param email
	 * @return email,id
	 * @throws SQLException
	 */
	public MemberVO findIdbyEmail(String email) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select member_id from member where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,email);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new MemberVO();
				vo.setEmail(email);
				vo.setId(rs.getString(1));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	/**
	 * email과 id로 회원 password 조회
	 * @param email, id
	 * @return email,password
	 * @throws SQLException
	 */
	public MemberVO findPasswordByEmail(String email, String id) throws SQLException{
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			String sql="select password from member where email=? and member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				vo=new MemberVO();
				vo.setEmail(email);
				vo.setPassword(rs.getString(1));
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return vo;
	}
	
	/**
	 * 회원 가입
	 * @param mvo
	 * @throws SQLException
	 */
	public void registerMember(MemberVO mvo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO member ");
			sql.append("(member_id,password,name,gender,birth,email,travel_style,country_id) ");
			sql.append("VALUES(?,?,?,?,?,?,?,?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getId());
			pstmt.setString(2, mvo.getPassword());
			pstmt.setString(3, mvo.getName());
			pstmt.setString(4, mvo.getGender());
			pstmt.setString(5, mvo.getBirth());
			pstmt.setString(6, mvo.getEmail());
			pstmt.setString(7, mvo.getTravelStyle());
			pstmt.setString(8, mvo.getCountryVO().getCountryId());
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	/**
	 * 회원정보 수정
	 * 수정 가능 정보 : 비밀번호, 이름, 생년월일, 여행스타일, 나라
	 * @param mvo.id
	 * @throws SQLException
	 */
	public void UpdateMember(MemberVO mvo) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE member set ");
			sql.append("password=?, name=?, birth=?, travel_style=?, country_id=? ");
			sql.append("where member_id = ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, mvo.getPassword());
			pstmt.setString(2, mvo.getName());
			pstmt.setString(3, mvo.getBirth());
			pstmt.setString(4, mvo.getTravelStyle());
			pstmt.setString(5, mvo.getCountryVO().getCountryId());
			pstmt.setString(6, mvo.getId());
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	/**
	 * 나라별 회원 수 조회
	 * @return map
	 * @throws SQLException
	 */
	public HashMap<String, Integer> getMemberCountByCountry() throws SQLException{
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=dataSource.getConnection();
			StringBuffer sql=new StringBuffer();
			sql.append("SELECT c.country_name, COUNT(m.member_id) ");
			sql.append("FROM member m ");
			sql.append("LEFT OUTER ");
			sql.append("JOIN country c ");
			sql.append("ON m.country_id=c.country_id ");
			sql.append("GROUP BY c.country_name");
			pstmt=con.prepareStatement(sql.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				if (rs.getString(1).equals("한국"))
					continue;
				map.put(rs.getString(1), rs.getInt(2));
			}
			/*여행중인 총 회원수를 구하기 위해 
			 *새로운 메서드를 정의하는 대신
			 *map.values의 총합을 구합니다.
			 */
			Collection values = map.values();
			Iterator it = values.iterator();
			int totalCount = 0;
			while (it.hasNext()) {
			Integer i = (Integer)it.next();
			totalCount += i.intValue();
			}
			map.put("totalCount", totalCount);
		}finally {
			closeAll(rs, pstmt, con);
		}
		return map;
	}
	
	public boolean idcheck(String id) throws SQLException {
		MemberVO vo=null;
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag = false;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from member where member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				flag = true;
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return flag;
	}
}
