--DDL

CREATE TABLE country(
   country_id VARCHAR2(100) primary key,
   country_name VARCHAR2(100) not null,
   country_time DATE not null,
   language VARCHAR2(100) not null,
   currency VARCHAR2(100) not null
)

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

CREATE TABLE postcomment( 
   comment_no NUMBER primary key,
   post_no NUMBER not null,
   member_id VARCHAR2(100) not null,
   content clob not null,
   time_commented DATE not null,
   constraint fk_member_comment foreign key(member_id) references member(member_id),
   constraint fk_post_comment foreign key(post_no) references post(post_no)
)

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

CREATE TABLE manager(
   manager_id VARCHAR2(100) primary key,
   password VARCHAR2(100) not null,
   name VARCHAR2(100) not null
)