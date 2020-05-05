INSERT INTO users
VALUES (1, 'Stepan', 'Stepanov', 'ROLE_DOCTOR', 'stepanov@mail.ru',
        '$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6');
INSERT INTO users
VALUES (2, 'Vasya', 'Brown', 'ROLE_DOCTOR', 'brown@com.com',
        '$2a$11$awcx8Xi/jmgOhQy5G3By7uBh.s/ai5GmWIYFV/fyqwerI4cAWBAE6');
INSERT INTO treatment
VALUES (1, 'paracetamol(tablets)', 'MEDICINE', 'TAB');
INSERT INTO treatment
VALUES (2, 'paracetamol(syrup)', 'MEDICINE', 'ML');
INSERT INTO treatment
VALUES (3, 'ibuprofen(injection)', 'MEDICINE', 'ML');
INSERT INTO treatment
VALUES (4, 'ibuprofen(tablets)', 'MEDICINE', 'TAB');
INSERT INTO treatment
VALUES (5, 'ibuprofen(syrup)', 'MEDICINE', 'ML');
INSERT INTO treatment
VALUES (6, 'amoxicillin', 'MEDICINE', 'TAB');
INSERT INTO treatment
VALUES (7, 'codeine', 'MEDICINE', 'TAB');
INSERT INTO treatment
VALUES (8, 'epinephrine', 'MEDICINE', 'ML');
INSERT INTO treatment
VALUES (9, 'aspirin', 'MEDICINE', 'TAB');
INSERT INTO treatment
VALUES (10, 'zyrtec', 'MEDICINE', 'TAB');
INSERT INTO treatment
VALUES (11, 'massage', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (12, 'blood sample (vein)', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (13, 'blood sample (finger)', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (14, 'physical therapy', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (15, 'endoscopy', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (16, 'electrocardiography', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (17, 'magnetoencephalography', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (18, 'photofluorography', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (19, 'tomography', 'MEDICAL_PROCEDURE', null);
INSERT INTO treatment
VALUES (20, 'urinalysis', 'MEDICAL_PROCEDURE', null);


INSERT INTO patients
VALUES (1, 'Ivan', 'Ivanov', 1234567890123456, '2020-03-23', 'TREATED', 'flu', 2);
INSERT INTO patients
VALUES (2, 'Petr', 'Petrov', 1122334455667788, '2020-03-24', 'TREATED', 'virus', 2);
INSERT INTO appointments
VALUES (1, '2020-03-23', '2010-03-25', null, 'ACTIVE', 1, 1);
INSERT INTO appointments
VALUES (2, '2020-03-23', '2020-03-26', 8, 'ACTIVE', 2, 2);
INSERT INTO appointments
VALUES (3, '2020-03-25', '2010-03-25', 10, 'ACTIVE', 1, 2);


INSERT INTO events
VALUES (1, '2020-03-23T10:30', 'CANCELLED', null, 1);
INSERT INTO events
VALUES (2, '2020-03-23T13:30', 'DONE', null, 2);
INSERT INTO events
VALUES (3, '2020-03-25T10:30', 'PLANNED', null, 2);
INSERT INTO events
VALUES (4, '2020-03-23T16:30', 'DONE', null, 3);

INSERT INTO appointments_daysofweek
VALUES (1, 'MON');
INSERT INTO appointments_daysofweek
VALUES (1, 'WED');
INSERT INTO appointments_daysofweek
VALUES (2, 'FRI');
INSERT INTO appointments_daysofweek
VALUES (3, 'SAT');

INSERT INTO appointments_timeoftheday
VALUES (1, 'AM_11');
INSERT INTO appointments_timeoftheday
VALUES (1, 'PM_3');
INSERT INTO appointments_timeoftheday
VALUES (2, 'AM_4');
INSERT INTO appointments_timeoftheday
VALUES (3, 'AM_10');