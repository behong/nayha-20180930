INSERT INTO USER(ID,USERID,PASSWORD,NAME,EMAIL,CREATE_DATE ) VALUES (100,'nayha','1111','TEST_IN','TEST@E.net' ,CURRENT_TIMESTAMP());
INSERT INTO USER(ID,USERID,PASSWORD,NAME,EMAIL,CREATE_DATE ) VALUES (200,'nayha2','1111','TEST_2','TES22@2.net' ,CURRENT_TIMESTAMP());

INSERT INTO QUESTION (ID,WRITER_ID,TITLE,CONTENTS,CREATE_DATE,COUNT_OF_ANSWER ) VALUES(100,100,'제목','내용쓰',CURRENT_TIMESTAMP(),0);
INSERT INTO QUESTION (ID,WRITER_ID,TITLE,CONTENTS,CREATE_DATE,COUNT_OF_ANSWER ) VALUES(200,200,'제목222','내용쓰2222',CURRENT_TIMESTAMP(),0);