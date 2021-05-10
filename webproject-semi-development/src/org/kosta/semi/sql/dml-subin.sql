SELECT * FROM country;
SELECT * FROM manager;
SELECT * FROM member;
SELECT * FROM post;
SELECT * FROM postcomment;

-- 게시글 상세보기
SELECT p.post_no, p.hits,p.member_id, p.category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted, p.post_title, p.content, m.country_id
FROM  post p, member m, country c
WHERE p.member_id = m.member_id AND p.country_id = c.country_id  AND p.post_no = '1';

-- 조회수 업데이트
UPDATE post SET hits=hits+1 WHERE post_no = '1';

-- 게시글 입력
INSERT INTO post VALUES(post_seq.nextval, '33', '동행', '동행구합니다', 'java', sysdate, 0, '동행을 많이많이 구해요!!!');
INSERT INTO post VALUES(post_seq.nextval, '39', '동행', '동행구합니다', 'spring', sysdate, 0, '동행을 많이많이 구해요!!!');
INSERT INTO post VALUES(post_seq.nextval, '33', '동행', '동행구합니다', 'java', sysdate, 0, '동행을 많이많이 구해요!!!');

-- 게시물 삭제
DELETE from post WHERE post_no = '21';

-- 맴버수 조회회
selectc.country_name, COUNT(m.member_id) FROM member m LEFT OUTER JOIN country c ON m.country_id=c.country_id GROUP BY c.country_name

-- 댓글 입력
INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) VALUES(postcomment_seq.nextval, '2', 'java', '너무 좋은 아이디어입니다!', sysdate); 
INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) VALUES(postcomment_seq.nextval, '2', 'spring', '정보가 수정되서 말씀드려요! 지금 여기는 안판다고 합니다!!', sysdate); 
INSERT INTO postcomment (comment_no, post_no, member_id, content ,time_commented ) VALUES(postcomment_seq.nextval, '2', 'mvc', ' 수정 감사드립니다!', sysdate);

-- 댓글 삭제
delete postcomment where comment_no = '1';
delete postcomment where comment_no = '2';
delete postcomment where comment_no = '3';
delete postcomment where comment_no = '4';

INSERT INTO manager VALUES('binnee3', 'a','주커피');
