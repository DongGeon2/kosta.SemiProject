DROP TABLE member; 
DROP TABLE country;
DROP TABLE manager;
DROP TABLE member;
DROP TABLE post;
DROP TABLE postcomment;

SELECT * FROM MEMBER;
select * from POST;
SELECT count(*) FROM member m , country c  WHERE m.country_id=c.country_id AND country_id='44';
SELECT count(*) FROM member m , country c  WHERE m.country_id=c.country_id AND country_name='영국';
--1번
CREATE TABLE country(
   country_id VARCHAR2(100) primary key,
   country_name VARCHAR2(100) not null, 
   country_time DATE not null,
   language VARCHAR2(100) not null,
   currency VARCHAR2(100) not null
)
--4번
CREATE TABLE post(
   post_no NUMBER primary key,
   country_id VARCHAR2(100) not null,
   category_name VARCHAR2(100) not null,
   post_title VARCHAR2(100) not null,
   member_id VARCHAR2(100) not null,
   time_posted DATE not null,
   hits NUMBER default 0,
   content clob not null,
   constraint fk_post_country foreign key(country_id) references country(country_id),
   constraint fk_post_member foreign key(member_id) references member(member_id)
)
--5번 
CREATE TABLE postcomment( 
   comment_no NUMBER primary key,
   post_no NUMBER not null,
   member_id VARCHAR2(100) not null,
   content clob not null,
   time_commented DATE not null,
   constraint fk_postcomment_member foreign key(member_id) references member(member_id),
   constraint fk_postcomment_postforeign foreign key(post_no) references post(post_no) ON DELETE CASCADE
)
--3번
CREATE TABLE member(
   member_id VARCHAR2(100) primary key,
   password VARCHAR2(100) not null,
   name VARCHAR2(100) not null,
   gender VARCHAR2(10) not null,
   birth DATE not null,
   email VARCHAR2(100) not null unique,
   travel_style VARCHAR2(100) not null,
   country_id VARCHAR2(100) not null,
   constraint fk_member_country foreign key(country_id) references country(country_id)
)
SELECT TRUNC(MONTHS_BETWEEN(sysdate, birth)/12) AS AGE FROM MEMBER 
--2번
CREATE TABLE manager(
   manager_id VARCHAR2(100) primary key,
   password VARCHAR2(100) not null,
   name VARCHAR2(100) not null
)

-------------------------------fileDB
drop table filedb;

drop sequence file_seq;
create sequence file_seq;
create table filedb(
	file_id varchar2(100) primary key,
	post_no number not null,
	org_name varchar2(100) not null,
	file_name varchar2(100) not null,
	file_path varchar2(200) not null,
	file_size varchar2(100) not null,
	fdate date not null,
	constraint fk_filedb_post foreign key(post_no) references post(post_no)
)
ALTER TABLE filedb MODIFY file_path varchar2(200)
SELECT * FROM post;
select * from filedb;
SELECT file_seq.nextval FROM dual;

--조인한다면 이거 어떻게 해야할지 물어보기.
SELECT f.file_id, f.org_name, f.file_path, f.file_size, f.date, p.post_no ;
FROM filedb f, post p
WHERE f.file_id=p.post_no AND file_name='filelist3.hwp'

--위에꺼랑 다르게 조인 안하고 바로 불러오기.
SELECT post_no, file_id, org_name, file_name ,file_path, file_size, fdate
FROM filedb
WHERE file_name='filelist3.hwp'


-- member table 상태값 추가
ALTER TABLE member ADD ( state NUMBER ) ;
UPDATE member SET state=1 ;
ALTER TABLE member MODIFY ( state NUMBER DEFAULT 1 ) ;

-- !!삭제한 모든 회원 되살리기!!
UPDATE member SET state=1 ;



---------------------------------------------------likedb추가
drop table likedb;
drop sequence like_seq;

create sequence like_seq;

create table likedb(
	post_no number not null,
	member_id varchar2(100) not null,
	constraint fk_likedb_post foreign key(post_no) references post(post_no),
	constraint fk_likedb_member foreign key(member_id) references member(member_id),
	constraint pk_likedb primary key(post_no,member_id)
)

select * from LIKEDB;
select count(*) from LIKEDB where post_no=?;
------------comment-----------------------------
SELECT member_id, time_commented , content  FROM postcomment  
WHERE post_no=42 order by time_commented DESC
----------------------------------------------------------
-- member table 포인트 추가
ALTER TABLE member ADD ( point NUMBER ) ;
UPDATE member SET point=0 ;
ALTER TABLE member MODIFY ( point NUMBER DEFAULT 0 ) ;


create table member_timeline(
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
-----------------------------------------------------------------
--게시글 삭제를 위한 TABLE 수정
--PostNo를 참조하는 테이블들의 제약조건을 수정
--Post가 삭제 될때 해당 PK를 참조하는 자료들을 모두 삭제함
ALTER TABLE POSTCOMMENT drop constraint fk_postcomment_postforeign
ALTER TABLE postcomment ADD  constraint fk_postcomment_postforeign foreign key(post_no) references post(post_no) ON DELETE CASCADE

ALTER TABLE filedb drop constraint fk_filedb_post
ALTER TABLE filedb ADD  constraint fk_filedb_post foreign key(post_no) references post(post_no) ON DELETE CASCADE

ALTER TABLE likedb drop constraint fk_likedb_post
ALTER TABLE likedb ADD  constraint fk_likedb_post foreign key(post_no) references post(post_no) ON DELETE CASCADE

