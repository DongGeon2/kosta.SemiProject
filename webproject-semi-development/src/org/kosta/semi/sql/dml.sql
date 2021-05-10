SELECT * FROM country;
SELECT * FROM manager;
SELECT * FROM member;
SELECT * FROM post;
SELECT * FROM postcomment;
----country-----
INSERT INTO country VALUES('33', '프랑스', sysdate, '불어','유로');
INSERT INTO country VALUES('39', '이탈리아', sysdate, '이탈리아어','유로');
INSERT INTO country VALUES('44', '영국', sysdate, '영어','파운드');
INSERT INTO country VALUES('49', '독일', sysdate, '독일어','유로');
INSERT INTO country VALUES('82', '한국', sysdate, '한국어','원');
----manager-----
INSERT INTO manager VALUES('managerKim', 'a','주커피');
----member-----
INSERT INTO member VALUES('java','a','최인재','m','19950929','a@ab.c','사진','33');
INSERT INTO member VALUES('spring','a','김지은','f','19930316','b@ab.c','맛집','39');
INSERT INTO member VALUES('mvc','a','김동건','m','19940421','rlagjqm1@naver.com','뚜벅이','44');
INSERT INTO member VALUES('singleton','a','이다영','f','19930822','c@ab.c','뚜벅이','39');
INSERT INTO member VALUES('francfranc','a','조수빈','f','19920908','d@ab.c','맛집','49');
INSERT INTO member VALUES('princessK','a','김정윤','m','19940501','f@ab.c','리무진','33');
----post-----
CREATE SEQUENCE post_seq;
INSERT INTO post VALUES(post_seq.nextval, '33', '정보', '프랑스 환전소 정보', 'java', sysdate, 0, '공항에서 환전 ㄴㄴ 봉쥬르마트안에 있는 환전소가 잘쳐줘요!');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', '이탈리아에서 한식을 외치다', 'spring',  sysdate, 0,'파스타 피자 질렸어요 내일 저녁 김치찌개에 소주 한잔 하실분? ');
INSERT INTO post VALUES( post_seq.nextval, '44', '후기', '영국 맛집 플랫아이언 추천!', 'mvc', sysdate,  0,'영국 음식 맛없기로 유명하지만 스테이크가 맛없기는 힘들죠 가성비에 후식 아이스크림까지 완전추천입니다!');
INSERT INTO post VALUES(post_seq.nextval, '49', '후기', '독일 히틀러호텔 비추ㅠ', 'francfranc', sysdate, 0, '독일 히틀러호텔 거르세요 베드버그 나옴 ㅠ');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', 'dfsf', 'singleton',  sysdate, 0,'czcz? ');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', '12345', 'spring',  sysdate, 0,'12345 ');
----postcomment-----
delete from post where post_no='22';
delete from post where post_no='23';
delete from post where post_no='24';

DELETE FROM member WHERE id='jsp'

SELECT p.post_no, c.country_name, p.category_name,p.post_title,p.member_id, to_char(time_posted, 'YYYY.MM.DD') as time_posted, p.hits 
FROM post p, country c WHERE p.country_id=c.country_id

SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits
FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title , member_id, hits, country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post) p, country c
WHERE p.country_id=c.country_id AND rnum BETWEEN 1 AND 5

SELECT p.post_no, c.country_name, p.category_name,p.post_title,p.member_id,to_char(time_posted, 'YYYY.MM.DD') as time_posted, p.hits
FROM post p, country c WHERE p.country_id=c.country_id

SELECT row_number() over(ORDER BY post_no DESC) as rnum, member_id, hits, country_id, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post;

SELECT B.no,B.title,B.hits,B.time_posted,M.name
FROM (SELECT row_number() over(ORDER BY NO DESC) as rnum,no,title,hits,to_char(time_posted,'YYYY.MM.DD') as time_posted,id FROM board) B, board_member M
		WHERE  B.id=M.id AND rnum BETWEEN 1 AND 5
-- 사용 sql
SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits
FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title , member_id, hits, country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post) p, country c
WHERE p.country_id=c.country_id AND rnum BETWEEN 1 AND 5		
		
SELECT c.country_name, COUNT(m.member_id)		
FROM member m 
LEFT OUTER 
JOIN country c 
ON m.country_id=c.country_id 
GROUP BY c.country_name;
		
-- 사용 sql (게시글 등록)		
INSERT INTO post VALUES(post_seq.nextval, '33', '정보', '프랑스 환전소 정보', 'java', sysdate, 0, '공항에서 환전 ㄴㄴ 봉쥬르마트안에 있는 환전소가 잘쳐줘요!');


select m.name,m.gender,m.birth,m.email,m.travel_style,c.country_id,c.country_name from member m, country c where m.country_id=c.country_id and member_id='java' and password='a'

SELECT p.post_no, c.country_name, p.category_name, p.post_title, p.member_id, p.time_posted, p.hits

FROM (SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title , member_id, hits, 
country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post WHERE country_id=39 ) p, country c
WHERE p.country_id=c.country_id AND rnum BETWEEN 1 AND 3

SELECT row_number() over(ORDER BY post_no DESC) as rnum,  post_no,post_title , member_id, hits, country_id, category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted FROM post
