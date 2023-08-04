-- 직급(사원, 대리, 과장, 부장, 차장, 전무, 상무, 사장 등..)
CREATE TABLE position
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(50) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP   NOT NULL DEFAULT NOW()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 회원 상태(입사, 퇴사, 재입사, 휴직, 육아휴직, 복직 등..)
CREATE TABLE status
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP   NOT NULL DEFAULT NOW()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 부서(개발, 기획, 영업, 인사, 회계, 법무 등..)
CREATE TABLE department
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    type       VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP   NOT NULL DEFAULT NOW()
);

-- 연차(직급 별 지급하는 연차의 개수)
CREATE TABLE day_off_by_position
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    position_id BIGINT    NOT NULL UNIQUE,
    amount      TINYINT   NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (position_id) REFERENCES position (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 연차(오전 반차, 오후 반차, 연차, 특별 휴가)
CREATE TABLE day_off
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    type        VARCHAR(20) NOT NULL UNIQUE,
    amount      FLOAT       NOT NULL,
    created_at  TIMESTAMP   NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP   NOT NULL DEFAULT NOW()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 회원 테이블 생성
CREATE TABLE employee
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT        COMMENT 'ID',
    auth_token          CHAR(36)      NOT NULL                   COMMENT '인증 토큰',
    position_id         BIGINT        NOT NULL                   COMMENT '직급 ID',
    department_id       BIGINT        NOT NULL                   COMMENT '부서 ID',
    status_id           BIGINT        NOT NULL                   COMMENT '회원상태 ID',
    name                VARCHAR(100)  NOT NULL                   COMMENT '이름',
    email               VARCHAR(100)  NOT NULL UNIQUE            COMMENT '이메일',
    password            CHAR(60)      NOT NULL COLLATE ascii_bin COMMENT '비밀번호',
    profile_image_path  VARCHAR(255)                             COMMENT '프로필 이미지', -- 추가된 칼럼
    hire_date           DATE          NOT NULL                   COMMENT '입사일',
    quit_date           DATE                                     COMMENT '퇴사일',
    day_off_remains     FLOAT         NOT NULL                   COMMENT '잔여 연차수',
    role                VARCHAR(20)   NOT NULL                   COMMENT '권한',
    phone               VARCHAR(60)   NOT NULL                   COMMENT '전화번호',
    birthdate           DATE          NOT NULL                   COMMENT '생년월일',
    zip_address         CHAR(5)                COLLATE ascii_bin COMMENT '우편번호',
    road_address        VARCHAR(255)                             COMMENT '주소1(도로명)',
    detail_address      VARCHAR(255)                             COMMENT '주소2(상세주소)',
    created_at          TIMESTAMP     NOT NULL DEFAULT NOW()     COMMENT '생성일',
    updated_at          TIMESTAMP     NOT NULL DEFAULT NOW()     COMMENT '수정일',
    FOREIGN KEY (position_id) REFERENCES position (id),     -- position 테이블의 id 칼럼을 참조
    FOREIGN KEY (department_id) REFERENCES department (id), -- department 테이블의 id 칼럼을 참조
    FOREIGN KEY (status_id) REFERENCES status (id)          -- status 테이블의 id 칼럼을 참조;
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE position_history
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT    NOT NULL,
    position_id BIGINT    NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (position_id) REFERENCES position (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE department_history
(
    id            BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id   BIGINT    NOT NULL,
    department_id BIGINT    NOT NULL,
    created_at    TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at    TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (department_id) REFERENCES department(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 회원 상태 이력 테이블 생성
CREATE TABLE status_history
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT    NOT NULL,
    status_id   BIGINT    NOT NULL,
    created_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (status_id) REFERENCES status (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 연차 등록요청/응답 이력
CREATE TABLE day_off_history
(
    id               BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id      BIGINT       NOT NULL,
    day_off_id       BIGINT       NOT NULL,
    status           VARCHAR(20)  NOT NULL,
    start_date       DATE         NOT NULL,
    end_date         DATE         NOT NULL,
    total_amount     FLOAT        NOT NULL,
    reason           VARCHAR(100) NOT NULL,  -- 100자 이내로 사유를 쓸 수 있음
    created_at       TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP    NOT NULL DEFAULT NOW(),
    FOREIGN KEY (employee_id) REFERENCES employee (id),
    FOREIGN KEY (day_off_id) REFERENCES day_off (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 당직 테이블 생성
CREATE TABLE duty_history
(
    id          BIGINT PRIMARY KEY AUTO_INCREMENT,
    employee_id BIGINT       NOT NULL,
    status      VARCHAR(20)  NOT NULL,
    date        DATE         NOT NULL,
    reason      VARCHAR(100) NOT NULL,  -- 100자 이내로 사유를 쓸 수 있음
    created_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    updated_at  TIMESTAMP    NOT NULL DEFAULT NOW(),
    FOREIGN KEY (employee_id) REFERENCES employee (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
