SELECT * FROM country
--country의 컬럼 country_time 
--1)컬럼 데이터 지우기 (사실 수정이 아니라 싹 지우고 새로운 컬럼을 만들어도되긴하지만..)
--2)컬럼명을 time_dif로 변경 //이미 정의한 메서드들 바꾸고 싶지 않다면 그대로 유지
--3)데이터 타입을 date에서 number로 변경 후 한국기준 시차 정보 넣어놓기

--1)컬럼 데이터 지우기 
--ALTER TABLE country DROP COLUMN country_time --아예컬럼을 삭제
UPDATE country SET country_time = NULL WHERE country_time IS NOT NULL;
-- 위의 쿼리로 데이터를 지우려면 not null이라는 데이터타입을 null로 변경해야함
ALTER TABLE country MODIFY country_time NULL
--2)컬럼명 변경
ALTER TABLE country RENAME COLUMN country_time TO time_dif
--3)컬럼 데이터 타입 변경
ALTER TABLE country MODIFY time_dif NUMBER 
--4)새로운 값을 넣어준후 not null조건 다시 넣어주기
ALTER TABLE country MODIFY time_dif NOT NULL
----------------------------------------------
--time_dif 데이터 넣어주기
UPDATE country SET time_dif = -7 WHERE country_id=33;
UPDATE country SET time_dif = -7 WHERE country_id=39;
UPDATE country SET time_dif = -8 WHERE country_id=44;
UPDATE country SET time_dif = -7 WHERE country_id=49;
UPDATE country SET time_dif = 0 WHERE country_id=82;
INSERT INTO country VALUES ('30', '그리스', -6, '그리스어', '유로')
-----------------------------------------------
--time_dif에 넣어놓은 시차를 이용해 나라 기준 글작성 시간 조회
--ex) 하루 더하기는 sysdate + 1, 한시간 더하기는 +1/24, 1초 더하기는 +1/24/60/60
SELECT p.country_id, to_char(p.time_posted,'YYYY.MM.DD PM HH12:MI'), c.time_dif, 
to_char((p.time_posted + c.time_dif/24), 'YYYY.MM.DD PM HH12:MI') as local_time
FROM post p, country c WHERE p.country_id = c.country_id; 