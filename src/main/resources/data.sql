INSERT INTO users VALUES (1, 'Stepan', 'Stepanov', 'ROLE_DOCTOR', 'stepanov@mail.ru', '$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6');
INSERT INTO users VALUES (2, 'Vasya', 'Brown', 'ROLE_DOCTOR', 'brown@com.com', '12323434241234');
INSERT INTO treatment VALUES (1, 'happiness','MEDICAL_PROCEDURE', NULL);
INSERT INTO treatment VALUES (2, 'love', 'MEDICINE', 'TAB')
INSERT INTO patients VALUES (1, 'Ivan', 'Ivanov', 1234567890123456, '2020-03-24','TREATED', 'flu',2);
INSERT INTO patients VALUES (2, 'Petr', 'Petrov', 1122334455667788, '2020-03-23', 'TREATED', 'virus',2);
INSERT INTO appointments VALUES (1, '2020-03-23', '2010-03-25', 'MON', '11 AM', 2, 1,1);
INSERT INTO appointments VALUES (2, '2020-03-23', '2020-03-26', 'SUN', '14 AM', 8, 2, 2);
INSERT INTO appointments VALUES (3, '2020-03-25', '2010-03-25', 'WED', '15 AM', 10, 1, 2);


INSERT INTO  events VALUES (1, '2020-03-23T10:30', 'PLANNED', null, 1);
INSERT INTO  events VALUES (2, '2020-03-23T13:30', 'PLANNED', null, 2);
INSERT INTO  events VALUES (3, '2020-03-25T10:30', 'PLANNED', null, 2);
INSERT INTO events VALUES (4, '2020-03-23T16:30', 'PLANNED', null, 3);