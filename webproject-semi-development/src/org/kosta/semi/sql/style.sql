DROP TABLE style
DELETE FROM style WHERE member_id = 'java'
SELECT * FROM style
CREATE TABLE style (
	member_id VARCHAR2(100) PRIMARY KEY,
	style1 VARCHAR2(100) NOT NULL,
	style2 VARCHAR2(100) NOT NULL,
	style3 VARCHAR2(100) NOT NULL,
	style4 VARCHAR2(100) NOT NULL,
	constraint fk_style_member foreign key(member_id) references member(member_id))

INSERT INTO member VALUES('match','a','김정윤','m','19940501','g@ab.c','리무진','33',1,20);

INSERT INTO style VALUES ('match','한적','럭셔리','큰계획','숙소' );	
INSERT INTO style VALUES ('spring','한적','럭셔리','큰계획','숙소' );	
INSERT INTO style VALUES ('mvc','한적','럭셔리','큰계획','숙소' );	
INSERT INTO style VALUES ('singleton','한적','럭셔리','큰계획','쇼핑' );	
INSERT INTO style VALUES ('francfranc','북적','가성비','세부계획','쇼핑' )
--만나이 구하는 메서드
SELECT TRUNC(MONTHS_BETWEEN(sysdate, birth)/12) AS AGE FROM MEMBER 

SELECT s.member_id,TRUNC(MONTHS_BETWEEN(sysdate, m.birth)/12) AS AGE, m.country_id
FROM style s, member m 
WHERE s.member_id=m.member_id AND m.country_id = '33'
 AND s.style1 ='한적' AND s.style2 ='럭셔리' AND s.style3 ='큰계획' AND s.style4 ='숙소' 