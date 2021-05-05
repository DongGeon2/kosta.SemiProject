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
----postcomment-----