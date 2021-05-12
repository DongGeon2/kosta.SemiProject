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
	    * @return MemberVO - name, email, country_id, country_name
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
	         sql.append("select m.name,m.email,c.country_id,c.country_name ");
	         sql.append("from member m, country c ");
	         sql.append("where m.country_id=c.country_id and m.member_id=? and m.password=? and m.state>0 ");
	         pstmt=con.prepareStatement(sql.toString());
	         pstmt.setString(1, id);
	         pstmt.setString(2, password);
	         rs=pstmt.executeQuery();
	         if(rs.next()){
	            vo=new MemberVO();
	            cvo = new CountryVO();
	            vo.setId(id);
	            vo.setName(rs.getString(1));
	            vo.setEmail(rs.getString(2));
	            cvo.setCountryId(rs.getString(3));
	            cvo.setCountryName(rs.getString(4));
	            vo.setCountryVO(cvo);
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
			String sql="select member_id from member where email=? and state>0";
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
			String sql="select password from member where email=? and member_id=? and state>0 ";
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
			sql.append("WHERE m.state>0 ");
			sql.append("GROUP BY c.country_name ");
			
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
	/**
	 * id 중복 확인을 합니다
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean idcheck(String id) throws SQLException {
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
	
	/**
	 * email 중복 확인을 합니다
	 * @param email
	 * @return
	 * @throws SQLException
	 */
	public boolean emailCheck(String email) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		boolean flag = false;
		try {
			con=dataSource.getConnection();
			String sql="select count(*) from member where email=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs.next() && rs.getInt(1)>0) {
				flag = true;
			}
		}finally {
			closeAll(rs, pstmt, con);
		}
		return flag;
	}
	
	
	/**
	 * 회원 나라 수정
	 * 수정 가능 정보 : 나라
	 * @param 
	 * @throws SQLException
	 */
	public void updateMemberCountry(String memberId, String countryId) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE member set ");
			sql.append("country_id=? ");
			sql.append("where member_id = ? and state>0");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, countryId);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	/**
	 * 회원 탈퇴, state값을 -1로 바꿉니다.
	 * @param 
	 * @throws SQLException
	 */
	public void deleteMemberById(String memberId) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE member set ");
			sql.append("state = -1 ");
			sql.append("where member_id = ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	/**
	 * 멤버의 포인트를 더합니다, 포인트 추가 근거를 message에 입력
	 * @param memberId, point, message
	 * @throws SQLException
	 */
	public void addMemberPoint(String memberId, int point, String message) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE member set ");
			sql.append("point = point + ? ");
			sql.append("where member_id = ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, point);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
			pstmt.close();
			sql = new StringBuffer();
			sql.append("INSERT INTO member_timeline (member_id, acted_time, point, message) ");
			sql.append("VALUES(?, sysdate, ?, ?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			pstmt.setInt(2, point);
			pstmt.setString(3, message);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	/**
	 * 멤버의 포인트를 뺍니다, 포인트 차감 근거를 message에 입력
	 * 포인트는 차감할 포인트를 양수로 입력(ex: -100점할 때, 100만 입력)
	 * @param memberId, point, message
	 * @param point
	 * @throws SQLException
	 */
	public void subtractMemberPoint(String memberId, int point, String message) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("UPDATE member set ");
			sql.append("point = point - ? ");
			sql.append("where member_id = ?");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setInt(1, point);
			pstmt.setString(2, memberId);
			pstmt.executeUpdate();
			pstmt.close();
			sql = new StringBuffer();
			sql.append("INSERT INTO member_timeline (member_id, acted_time, point, message) ");
			sql.append("VALUES(?, sysdate, ?, ?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			pstmt.setInt(2, -point);
			pstmt.setString(3, message);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
	
	/**
	 * id로 회원의 pw를 제외한 상세 정보를 불러옵니다
	 * @param id
	 * @param password
	 * @return
	 * @throws SQLException
	 */
	public MemberVO getMemberDetailById(String id) throws SQLException{
	      MemberVO vo=null;
	      CountryVO cvo = null;
	      Connection con=null;
	      PreparedStatement pstmt=null;
	      ResultSet rs=null;
	      try{
	         con=dataSource.getConnection();
	         StringBuffer sql= new StringBuffer();
	         sql.append("select m.name, m.email, c.country_id, c.country_name, m.birth, m.gender, m.travel_style, m.point, m.state ");
	         sql.append("from member m, country c ");
	         sql.append("where m.country_id=c.country_id and m.member_id=?");
	         pstmt=con.prepareStatement(sql.toString());
	         pstmt.setString(1, id);
	         rs=pstmt.executeQuery();
	         if(rs.next()){
	            vo=new MemberVO();
	            cvo = new CountryVO();
	            vo.setId(id);
	            vo.setName(rs.getString(1));
	            vo.setEmail(rs.getString(2));
	            cvo.setCountryId(rs.getString(3));
	            cvo.setCountryName(rs.getString(4));
	            vo.setBirth(rs.getString(5));
	            vo.setGender(rs.getString(6));
	            vo.setTravelStyle(rs.getString(7));
	            vo.setPoint(rs.getInt(8));
	            vo.setState(rs.getString(9));
	            vo.setCountryVO(cvo);
	         }
	      }finally{
	         closeAll(rs, pstmt,con);
	      }
	      System.out.println(vo);
	      return vo;
	   }

	public int getPointById(String id) throws SQLException{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int point = 0;
		try{
			con=dataSource.getConnection();
			String sql="SELECT point FROM member WHERE member_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()){
				point =rs.getInt(1);
			}
		}finally{
			closeAll(rs, pstmt,con);
		}
		return point;
	}
	
	/**
	 * 멤버의 활동을 기록합니다
	 * @param memberId, message
	 * @throws SQLException
	 */
	public void AddMemberTimeline(String memberId, String message) throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try{
			con=dataSource.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("INSERT INTO member_timeline (member_id, acted_time, message) ");
			sql.append("VALUES(?, sysdate, ?)");
			pstmt=con.prepareStatement(sql.toString());
			pstmt.setString(1, memberId);
			pstmt.setString(2, message);
			pstmt.executeUpdate();
		}finally{
			closeAll(pstmt,con);
		}
	}
}
