-- DROP SCHEMA IF EXISTS postgres CASCADE;
CREATE SCHEMA IF NOT EXISTS postgres;
CREATE SEQUENCE hibernate_sequence START WITH 100 INCREMENT BY 1 NO CYCLE;
-- Table: treatment
CREATE TABLE treatment
(
    id         INT         NOT NULL PRIMARY KEY,
    name       VARCHAR(50) NOT NULL,
    type       VARCHAR(20) CHECK ( type IN ('MEDICAL_PROCEDURE', 'MEDICINE')),
    dosageForm VARCHAR(30) CHECK ( dosageForm IN ('TAB', 'ML'))
);

-- Table: users
CREATE TABLE users
(
    id        INT                                                                      NOT NULL PRIMARY KEY,
    firstName VARCHAR(50)                                                              NOT NULL,
    lastName  VARCHAR(50)                                                              NOT NULL,
    role      VARCHAR(30) CHECK ( role IN ('ROLE_ADMIN', 'ROLE_DOCTOR', 'ROLE_NURSE')) NOT NULL,
    email     VARCHAR(50)                                                              NOT NULL,
    password  VARCHAR(255)                                                             NOT NULL
);

-- Table: patients
CREATE TABLE patients
(
    id              INT                                                      NOT NULL PRIMARY KEY,
    firstName       VARCHAR(30)                                              NOT NULL,
    lastName        VARCHAR(30)                                              NOT NULL,
    healthInsurance BIGINT                                                   NOT NULL,
    startDate       TIMESTAMP                                                NOT NULL,
    status          VARCHAR(30) CHECK ( status IN ('TREATED', 'DISCHARGED')) NOT NULL,
    diagnosis       VARCHAR(255),
    doctor_id       INT,
    FOREIGN KEY (doctor_id) REFERENCES users (id)
);

-- Table: appointments
CREATE TABLE appointments
(
    id           INT         NOT NULL PRIMARY KEY,
    startdate    TIMESTAMP   NOT NULL,
    enddate      TIMESTAMP   NOT NULL,
    dosage       INT,
    status       VARCHAR(15) NOT NULL,
    patient_id   INT,
    FOREIGN KEY (patient_id) REFERENCES patients (id),
    treatment_id INT,
    FOREIGN KEY (treatment_id) REFERENCES treatment (id)
);

-- Table for mapping appointments and daysOfWeek: appointments_daysofweek
CREATE TABLE appointments_daysofweek
(
    appointment_id INT     NOT NULL,
    dayofweek      VARCHAR NOT NULL,

    FOREIGN KEY (appointment_id) REFERENCES appointments (id)
);

CREATE TABLE appointments_timeoftheday
(
    appointment_id INT     NOT NULL,
    time           VARCHAR NOT NULL,

    FOREIGN KEY (appointment_id) REFERENCES appointments (id)
);

-- Table: events
CREATE TABLE events
(
    id             INT                                                            NOT NULL,
    date           TIMESTAMP                                                      NOT NULL,
    status         VARCHAR(30) CHECK (status IN ('PLANNED', 'DONE', 'CANCELLED')) NOT NULL,
    comment        VARCHAR(255),
    appointment_id INT,
    FOREIGN KEY (appointment_id) REFERENCES appointments (id)
);







