package org.kosta.semi.model;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;



/**
 * 컨넥션 풀pool을 생성하여 공유하는 객체 
 * (컨넥션풀은 컨넥션 필요시 생성,소멸하는
 * 방식이 아니라 빌려오고 반납받는 형식으로
 * 동작하여 성능을 향상시키는 것이 목적)
 * 
 * 시스템 상에서 dbcp 는 하나만 존재하면 되고
 * 여러곳에서 사용하면 되므로 singleton pattern을 
 * 적용하여 구현한다. 
 * @author KOSTA
 *
 */
public class DataSourceManager {
	private static DataSourceManager instance
							=new DataSourceManager();
	private DataSource ds;
	private DataSourceManager(){
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		dataSource.setUsername("scott");
		dataSource.setPassword("tiger");		
		ds=dataSource;
	}
	public static DataSourceManager getInstance(){
		return instance;
	}
	public DataSource getDataSource(){
		return ds;
	}
}








