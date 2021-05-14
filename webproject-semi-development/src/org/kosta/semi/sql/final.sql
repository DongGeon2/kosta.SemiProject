--------------------------------------------------수정 총합본
DROP TABLE country;
DROP TABLE manger;
DROP TABLE member;
DROP TABLE post;
DROP TABLE postcomment;
DROP TABLE filedb;
DROP TABLE likedb;
DROP TABLE member_timeline;
DROP TABLE style;
--------------------------------------------------
SELECT * FROM country;
SELECT * FROM manager;
SELECT * FROM member;
SELECT * FROM post;
SELECT * FROM postcomment;
SELECT * FROM filedb;
SELECT * FROM likedb;
SELECT * FROM member_timeline;
SELECT * FROM style;
--------------------------------------------------DDL/DML 생성과 테스트용 데이터
--1.나라 
CREATE TABLE country(
   country_id VARCHAR2(100) primary key,
   country_name VARCHAR2(100) not null, 
   country_time number DEFAULT -7,
   language VARCHAR2(100) not null,
   currency VARCHAR2(100) not null,
   --basic_expression VARCHAR2(200)
)

INSERT INTO country VALUES('33', '프랑스', -7, '불어','프랑');
INSERT INTO country VALUES('39', '이탈리아', -7, '이탈리아어','이딸로');
INSERT INTO country VALUES('44', '영국', -8, '영어','파운드');
INSERT INTO country VALUES('49', '독일', -7, '독일어','유로');
--INSERT INTO country VALUES ('30', '그리스', -6, '그리스어', '유로')
INSERT INTO country VALUES('82', '한국', 0, '한국어','원');

--2.관리자
CREATE TABLE manager(
   manager_id VARCHAR2(100) primary key,
   password VARCHAR2(100) not null,
   name VARCHAR2(100) not null
)
INSERT INTO manager VALUES('managerKim', 'a','주커피');

--3.회원
CREATE TABLE member(
   member_id VARCHAR2(100) primary key,
   password VARCHAR2(100) not null,
   name VARCHAR2(100) not null,
   gender VARCHAR2(10) not null,
   birth DATE not null,
   email VARCHAR2(100) not null unique,
   travel_style VARCHAR2(100) not null,
   country_id VARCHAR2(100) not null,
   state NUMBER DEFAULT 1,
   point NUMBER default 0,
   constraint fk_member_country foreign key(country_id) references country(country_id)
)
INSERT INTO member VALUES('java','a','최인재','m','19950929','a@ab.c','사진','33',1,4);
INSERT INTO member (member_id,password,name,gender,birth,email,travel_style,country_id) VALUES('spring','a','김지은','f','19930316','b@ab.c','맛집','39');
INSERT INTO member (member_id,password,name,gender,birth,email,travel_style,country_id) VALUES('mvc','a','김동건','m','19940421','rlagjqm1@naver.com','뚜벅이','44');
INSERT INTO member (member_id,password,name,gender,birth,email,travel_style,country_id) VALUES('singleton','a','이다영','f','19930822','c@ab.c','뚜벅이','39');
INSERT INTO member (member_id,password,name,gender,birth,email,travel_style,country_id) VALUES('francfranc','a','조수빈','f','19920908','d@ab.c','맛집','49');
INSERT INTO member (member_id,password,name,gender,birth,email,travel_style,country_id) VALUES('princessK','a','김정윤','m','19940501','f@ab.c','리무진','33');
INSERT INTO member VALUES('match','a','아이유','m','19940501','g@ab.c','리무진','33',1,20);

--4.게시물
CREATE SEQUENCE post_seq;
CREATE TABLE post(
   post_no NUMBER primary key,
   country_id VARCHAR2(100) not null,
   category_name VARCHAR2(100) not null,
   post_title VARCHAR2(100) not null,
   member_id VARCHAR2(100) not null,
   time_posted DATE not null, --default sysdate가능?
   hits NUMBER default 0,
   content clob not null,
   constraint fk_post_country foreign key(country_id) references country(country_id),
   constraint fk_post_member foreign key(member_id) references member(member_id)
)
INSERT INTO post VALUES(post_seq.nextval, '33', '정보', '프랑스 환전소 정보', 'java', sysdate, 0, '공항에서 환전 ㄴㄴ 봉쥬르마트안에 있는 환전소가 잘쳐줘요!');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', '이탈리아에서 한식을 외치다', 'spring',  sysdate, 0,'파스타 피자 질렸어요 내일 저녁 김치찌개에 소주 한잔 하실분? ');
INSERT INTO post VALUES( post_seq.nextval, '44', '후기', '영국 맛집 플랫아이언 추천!', 'mvc', sysdate,  0,'영국 음식 맛없기로 유명하지만 스테이크가 맛없기는 힘들죠 가성비에 후식 아이스크림까지 완전추천입니다!');
INSERT INTO post VALUES(post_seq.nextval, '49', '후기', '독일 히틀러호텔 비추ㅠ', 'francfranc', sysdate, 0, '독일 히틀러호텔 거르세요 베드버그 나옴 ㅠ');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', 'dfsf', 'singleton',  sysdate, 0,'czcz? ');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', '12345', 'spring',  sysdate, 0,'12345 ');

--5.댓글
CREATE TABLE postcomment( 
   comment_no NUMBER primary key,
   post_no NUMBER not null,
   member_id VARCHAR2(100) not null,
   content clob not null,
   time_commented DATE not null, --default sysdate가능?
   constraint fk_postcomment_member foreign key(member_id) references member(member_id),
   constraint fk_postcomment_postforeign foreign key(post_no) references post(post_no) ON DELETE CASCADE
)
INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) VALUES(postcomment_seq.nextval, '2', 'java', '너무 좋은 아이디어입니다!', sysdate); 
INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) VALUES(postcomment_seq.nextval, '2', 'spring', '정보가 수정되서 말씀드려요! 지금 여기는 안판다고 합니다!!', sysdate); 
INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) VALUES(postcomment_seq.nextval, '2', 'mvc', ' 수정 감사드립니다!', sysdate);

--6.파일
create sequence file_seq;
create table filedb(
	file_id varchar2(100) primary key,
	post_no number not null,
	org_name varchar2(100) not null,
	file_name varchar2(100) not null,
	file_path varchar2(200) not null,
	file_size varchar2(100) not null,
	fdate date not null,
	constraint fk_filedb_post foreign key(post_no) references post(post_no) ON DELETE CASCADE
)
--7.좋아요 
CREATE TABLE likedb(
	post_no number not null,
	member_id varchar2(100) not null,
	constraint fk_likedb_post foreign key(post_no) references post(post_no),
	constraint fk_likedb_member foreign key(member_id) references member(member_id),
	constraint fk_likedb_post foreign key(post_no) references post(post_no) ON DELETE CASCADE
)
--8.포인트 플로우 기록 
CREATE TABLE member_timeline(
	member_id varchar2(100) not null,	
	acted_time date not null,
	point number,
	message clob not null,
	primary key(member_id, acted_time)
)
INSERT INTO member_timeline VALUES('java',to_date('2021-02-22 09:04:20','yyyy-mm-dd hh24:mi:ss'),null,'회원가입');
INSERT INTO member_timeline VALUES('spring',to_date('2021-02-22 09:05:45','yyyy-mm-dd hh24:mi:ss'),null,'회원가입');
INSERT INTO member_timeline VALUES('mvc',to_date('2021-02-22 09:01:20','yyyy-mm-dd hh24:mi:ss'),null,'회원가입');
INSERT INTO member_timeline VALUES('singleton',to_date('2021-02-22 08:56:10','yyyy-mm-dd hh24:mi:ss'),null,'회원가입');
INSERT INTO member_timeline VALUES('francfranc',to_date('2021-02-22 09:02:27','yyyy-mm-dd hh24:mi:ss'),null,'회원가입');
INSERT INTO member_timeline VALUES('princessK',to_date('2021-02-22 08:45:09','yyyy-mm-dd hh24:mi:ss'),null,'회원가입');
--9.구체적여행스타일
CREATE TABLE style (
	member_id VARCHAR2(100) PRIMARY KEY,
	style1 VARCHAR2(100) NOT NULL,
	style2 VARCHAR2(100) NOT NULL,
	style3 VARCHAR2(100) NOT NULL,
	style4 VARCHAR2(100) NOT NULL,
	message clob,
	constraint fk_style_member foreign key(member_id) references member(member_id)
)
--------------------------------------------------사용sql문 
--(포트폴리오에 적을수있게 새로 알게된 문법이나 생각이 필요했던 몇가지 위주로 추가해주시면 좋을것 같습니다)

--검색(나라 33, 컬럼 작성자id, 검색어 java) 명령어 : LIKE
SELECT x.*
	FROM (SELECT ROWNUM rn,p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, to_char(p.time_posted, 'YYYY.MM.DD') as time_posted, p.hits
		FROM post p, country c
      	WHERE p.country_id=c.country_id 
      	--AND p.country_id='33'
      	AND p.member_id LIKE '%' || 'java' || '%'
      	ORDER BY p.post_no) x
--WHERE rn  BETWEEN 6 AND 10 --페이징빈

--만나이
SELECT TRUNC(MONTHS_BETWEEN(sysdate, birth)/12) AS AGE FROM MEMBER 

--시차를 이용해 나라 기준 글작성 시간 조회 
--DATE타입 시간 연산 :하루 더하기는 sysdate + 1, 한시간 더하기는 +1/24, 1초 더하기는 +1/24/60/60
SELECT p.country_id, to_char(p.time_posted,'YYYY.MM.DD PM HH12:MI'), c.country_time, 
to_char((p.time_posted + c.country_time/24), 'YYYY.MM.DD PM HH12:MI') as local_time
FROM post p, country c WHERE p.country_id = c.country_id; 

--컬럼 수정 (컬럼명변경, 데이터타입 수정)
ALTER TABLE [테이블명] RENAME COLUMN [원래컬럼명] TO [바꿀컬럼명]
ALTER TABLE [테이블명] MODIFY [원래컬럼명] [데이터타입 및 제약조건]

--Cascade
