DROP TABLE member; 
DROP TABLE country;
DROP TABLE manager;
DROP TABLE member;
DROP TABLE post;
DROP TABLE postcomment;

SELECT * FROM MEMBER;
select * from POST;

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
   constraint fk_postcomment_postforeign foreign key(post_no) references post(post_no)
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
 