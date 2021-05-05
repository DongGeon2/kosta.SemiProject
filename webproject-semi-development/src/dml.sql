SELECT * FROM country;
SELECT * FROM manager;
SELECT * FROM member;
SELECT * FROM post;
SELECT * FROM postcomment;
----country-----
INSERT INTO country VALUES('33', '������', sysdate, '�Ҿ�','����');
INSERT INTO country VALUES('39', '��Ż����', sysdate, '��Ż���ƾ�','����');
INSERT INTO country VALUES('44', '����', sysdate, '����','�Ŀ��');
INSERT INTO country VALUES('49', '����', sysdate, 's���Ͼ�','����');
----manager-----
INSERT INTO manager VALUES('managerKim', 'a','��Ŀ��');
----member-----
INSERT INTO member VALUES('java','a','������','ms','19950929','a@ab.c','����','33');
INSERT INTO member VALUES('spring','a','������','f','19930316','b@ab.c','����','39');
INSERT INTO member VALUES('mvc','a','�赿��','m','19940421','rlagjqm1@naver.com','�ѹ���','44');
INSERT INTO member VALUES('singleton','a','�̴ٿ�','f','19930822','c@ab.c','�ѹ���','39');
INSERT INTO member VALUES('francfranc','a','������','f','19920908','d@ab.c','����','49');
INSERT INTO member VALUES('princessK','a','������','m','19940501','f@ab.c','������','33');
----post-----
CREATE SEQUENCE post_seq;
INSERT INTO post VALUES(post_seq.nextval, '33', '����', '������ ȯ���� ����', 'java', sysdate, 0, '���׿��� ȯ�� ���� ���긣��Ʈ�ȿ� �ִ� ȯ���Ұ� �������!');
INSERT INTO post VALUES(post_seq.nextval, '39', '����', '��Ż���ƿ��� �ѽ��� ��ġ��', 'spring',  sysdate, 0,'�Ľ�Ÿ ���� ���Ⱦ�� ���� ���� ��ġ��� ���� ���� �ϽǺ�? ');
INSERT INTO post VALUES( post_seq.nextval, '44', '�ı�', '���� ���� �÷����̾� ��õ!', 'mvc', sysdate,  0,'���� ���� ������� ���������� ������ũ�� ������� ������ ������ �Ľ� ���̽�ũ������ ������õ�Դϴ�!');
INSERT INTO post VALUES(post_seq.nextval, '49', '�ı�', '���� ��Ʋ��ȣ�� ���ߤ�', 'singleton', sysdate, 0, '���� ��Ʋ��ȣ�� �Ÿ����� ������� ���� ��');
----postcomment-----
