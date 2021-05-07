-- 게시글 상세보기
SELECT p.post_no, p.hits,p.member_id, p.category_name, to_char(time_posted, 'YYYY.MM.DD') as time_posted, p.post_title, p.content, m.country_id
FROM  post p, member m, country c
WHERE p.member_id = m.member_id AND p.country_id = c.country_id  AND p.post_no = '1';

-- 조회수 업데이트
UPDATE post SET hits=hits+1 WHERE post_no = '1';

-- 게시글 입력
INSERT INTO post VALUES(post_seq.nextval, '33', '동행', '동행구합니다', 'java', sysdate, 0, '동행을 많이많이 구해요!!!');
-- 게시물 삭제
