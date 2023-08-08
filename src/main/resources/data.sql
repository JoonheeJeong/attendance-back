-- 부서
INSERT INTO department (type)
VALUES
    ('AUTOMOBILE'),
    ('STORE'),
    ('SECURITIES'),
    ('SEMICON'),
    ('MEDICAL'),
    ('MICRO'),
    ('UNKNOWN');

-- 직급
INSERT INTO position (type)
VALUES
    ('STAFF'),
    ('ASSISTANT_MANAGER'),
    ('MANAGER'),
    ('DEPUTY_GENERAL_MANAGER'),
    ('GENERAL_MANAGER');

-- 직급별 연차 개수
INSERT INTO day_off_by_position (position_id, amount)
VALUES
    (1, 15),
    (2, 17),
    (3, 19),
    (4, 21),
    (5, 23);

-- 상태
INSERT INTO status (type)
VALUES
    ('JOINED'),
    ('RESIGNED'),
    ('REJOINED'),
    ('ON_LEAVE'),
    ('REINSTATED'),
    ('RETIRED');

-- 연차 규칙
INSERT INTO day_off (type, amount)
VALUES
    ('FIRST_HALF_DAY_OFF', 0.5),
    ('SECOND_HALF_DAY_OFF', 0.5),
    ('NORMAL_DAY_OFF' ,   1),
    ('SPECIAL_DAY_OFF',   0);

-- 직원
INSERT INTO employee (auth_token, position_id, department_id, status_id, name, email, password, hire_date, day_off_remains, role, phone, birthdate)
VALUES
    ('b902fc85-ccc5-4b5e-9a79-6488db8827bf', 1, 1, 1, '테스트유저1', 'testuser1@email.com', 'test1234', '2023-03-02', 11, 'USER', '010-1234-5712', '1996-07-30'),
    ('b902fc85-ccc5-4b5e-9a79-7488db8827bf', 1, 2, 1, '테스트유저2', 'testuser2@email.com', 'test1234', '2023-03-02', 11.5, 'USER', '010-1234-5711', '1998-01-31'),
    ('b902fc85-ccc5-4b5e-9a79-8488db8827bf', 1, 3, 1, '테스트유저3', 'testuser3@email.com', 'test1234', '2023-03-02', 11, 'USER', '010-1234-5710', '1995-04-13'),
    ('b902fc85-ccc5-4b5e-9a79-9488db8827bf', 1, 4, 1, '테스트유저4', 'testuser4@email.com', 'test1234', '2022-03-02', 11.5, 'USER', '010-1234-5709', '1994-07-25'),
    ('b902fc85-ccc5-4b5e-9a79-6088db8827bf', 1, 5, 1, '테스트유저5', 'testuser5@email.com', 'test1234', '2022-03-02', 11.5, 'USER', '010-1234-5708', '1996-08-14'),
    ('b902fc85-ccc5-4b5e-9a79-6188db8827bf', 2, 6, 1, '테스트유저6', 'testuser6@email.com', 'test1234', '2020-02-28', 14, 'USER', '010-1234-5707', '1990-06-30'),
    ('b902fc85-ccc5-4b5e-9a79-6288db8827bf', 2, 1, 1, '테스트유저7', 'testuser7@email.com', 'test1234', '2020-02-28', 16.5, 'USER', '010-1234-5706', '1992-09-15'),
    ('b902fc85-ccc5-4b5e-9a79-6388db8827bf', 2, 2, 1, '테스트유저8', 'testuser8@email.com', 'test1234', '2018-07-23', 14, 'USER', '010-1234-5705', '1989-12-31'),
    ('b902fc85-ccc5-4b5e-9a79-7088db8827bf', 2, 3, 1, '테스트유저9', 'testuser9@email.com', 'test1234', '2018-07-23', 16.5, 'USER', '010-1234-5704', '1988-07-29'),
    ('b902fc85-ccc5-4b5e-9a79-7188db8827bf', 3, 4, 1, '테스트유저10', 'testuser10@email.com', 'test1234', '2017-03-02', 19, 'USER', '010-1234-5704', '1985-08-01'),
    ('b902fc85-ccc5-4b5e-9a79-7288db8827bf', 3, 5, 1, '테스트유저11', 'testuser11@email.com', 'test1234', '2017-03-02', 19, 'USER', '010-1234-5703', '1991-11-30'),
    ('b902fc85-ccc5-4b5e-9a79-7388db8827bf', 3, 6, 1, '테스트유저12', 'testuser12@email.com', 'test1234', '2017-03-02', 19, 'USER', '010-1234-5702', '1983-10-11'),
    ('b902fc85-ccc5-4b5e-9a79-8088db8827bf', 4, 1, 1, '테스트유저13', 'testuser13@email.com', 'test1234', '2016-09-02', 21, 'USER', '010-1234-5701', '1970-07-30'),
    ('b902fc85-ccc5-4b5e-9a79-8188db8827bf', 4, 2, 1, '테스트유저14', 'testuser14@email.com', 'test1234', '2016-09-02', 21, 'USER', '010-1234-5700', '1968-02-09'),
    ('b902fc85-ccc5-4b5e-9a79-8288db8827bf', 5, 4, 1, '테스트유저15', 'testuser15@email.com', 'test1234', '2015-03-02', 23, 'USER', '010-1234-5679', '1960-03-29'),
    ('b902fc85-ccc5-4b5e-9a79-9988db8827bf', 5, 6, 1, '테스트유저16', 'testuser16@email.com', 'test1234', '2014-12-01', 23, 'ADMIN', '010-1234-5678', '1956-02-10');

INSERT INTO day_off_history(employee_id, day_off_id, status, start_date, end_date, total_amount, reason)
VALUES
    (1, 1, 'REQUESTED', '2023-07-31', '2023-07-31', 0.5, '늦잠 잘래요.'),
    (1, 3, 'REQUESTED', '2023-08-01', '2023-08-03', 3, '놀래요.'),
    (1, 3, 'REJECTED', '2023-08-16', '2023-08-18', 3, '놀래요.'),
    (1, 2, 'APPROVED', '2023-08-21', '2023-08-21', 0.5, '저녁 약속.'),
    (1, 3, 'CANCELLED', '2023-08-23', '2023-08-25', 3, '놀래요.'),
    (2, 1, 'REQUESTED', '2023-07-31', '2023-07-31', 0.5, '늦잠 잘래요.'),
    (2, 3, 'APPROVED', '2023-08-01', '2023-08-03', 3, '놀래요.'),
    (2, 2, 'REJECTED', '2023-08-21', '2023-08-21', 0.5, '저녁 약속.'),
    (2, 3, 'CANCELLED', '2023-08-23', '2023-08-25', 3, '놀래요.'),
    (3, 1, 'REQUESTED', '2023-07-31', '2023-07-31', 0.5, '늦잠 잘래요.'),
    (3, 3, 'APPROVED', '2023-08-01', '2023-08-03', 3, '놀래요.'),
    (3, 2, 'APPROVED', '2023-08-21', '2023-08-21', 0.5, '저녁 약속.'),
    (4, 3, 'REQUESTED', '2023-08-23', '2023-08-25', 3, '놀래요.'),
    (4, 1, 'REQUESTED', '2023-07-31', '2023-07-31', 0.5, '늦잠 잘래요.'),
    (5, 3, 'REQUESTED', '2023-08-01', '2023-08-03', 3, '놀래요.'),
    (5, 2, 'REQUESTED', '2023-08-21', '2023-08-21', 0.5, '저녁 약속.'),
    (6, 3, 'REQUESTED', '2023-08-23', '2023-08-25', 3, '놀래요.'),
    (7, 1, 'REQUESTED', '2023-07-31', '2023-07-31', 0.5, '늦잠 잘래요.'),
    (8, 3, 'REQUESTED', '2023-08-01', '2023-08-03', 3, '놀래요.'),
    (9, 2, 'APPROVED', '2023-08-21', '2023-08-21', 0.5, '저녁 약속.'),
    (10, 4, 'REQUESTED', '2023-08-28', '2023-09-01', 0, '놀래요.');

INSERT INTO duty_history(employee_id, status, date, reason)
VALUES
    (1, 'REQUESTED', '2023-08-09', '배터리 테스트'),
    (1, 'APPROVED', '2023-08-10', '휠 테스트'),
    (1, 'REJECTED', '2023-08-18', '엔진 테스트'),
    (1, 'CANCELLED', '2023-08-25', '프로젝트 발표'),
    (1, 'REQUESTED', '2023-08-28', '프로젝트 발표'),
    (2, 'REQUESTED', '2023-08-09', '배터리 테스트'),
    (2, 'REJECTED', '2023-08-10', '휠 테스트'),
    (2, 'APPROVED', '2023-08-18', '엔진 테스트'),
    (2, 'CANCELLED', '2023-08-25', '프로젝트 발표'),
    (3, 'REQUESTED', '2023-08-09', '배터리 테스트'),
    (3, 'REJECTED', '2023-08-21', '유리창 테스트'),
    (3, 'REQUESTED', '2023-08-28', '프로젝트 발표'),
    (4, 'APPROVED', '2023-08-18', '엔진 테스트'),
    (4, 'CANCELLED', '2023-08-25', '프로젝트 발표'),
    (5, 'REQUESTED', '2023-08-28', '프로젝트 발표');
