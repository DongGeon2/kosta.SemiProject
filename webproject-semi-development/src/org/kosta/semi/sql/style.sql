DROP TABLE style

CREATE TABLE style (
	member_id VARCHAR2(100) PRIMARY KEY,
	style1 VARCHAR2(100) NOT NULL,
	style2 VARCHAR2(100) NOT NULL,
	style3 VARCHAR2(100) NOT NULL,
	style4 VARCHAR2(100) NOT NULL,
	constraint fk_style_member foreign key(member_id) references member(member_id))

INSERT INTO style VALUES ('spring','한적','럭셔리','큰계획','숙소' );	
INSERT INTO style VALUES ('mvc','한적','럭셔리','큰계획','숙소' );	
INSERT INTO style VALUES ('singleton','한적','럭셔리','큰계획','쇼핑' );	
INSERT INTO style VALUES ('spring','북적','가성비','세부계획','맛집' );	
INSERT INTO style VALUES ('francfranc','북적','가성비','세부계획','쇼핑' );	
--만나이 구하는 메서드
SELECT TRUNC(MONTHS_BETWEEN(sysdate, birth)/12) AS AGE FROM MEMBER 
