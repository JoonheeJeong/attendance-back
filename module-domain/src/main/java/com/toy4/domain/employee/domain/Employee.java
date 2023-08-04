package com.toy4.domain.employee.domain;

import com.toy4.domain.BaseEntity;
import com.toy4.domain.department.domain.Department;
import com.toy4.domain.employee.dto.EmployeeDto;
import com.toy4.domain.employee.type.EmployeeRole;
import com.toy4.domain.position.domain.Position;
import com.toy4.domain.status.domain.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee extends BaseEntity {

    @Column(length = 36, nullable = false)
    private String authToken;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "position_id", referencedColumnName = "id", nullable = false)
    private Position position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id", referencedColumnName = "id", nullable = false)
    private Status status;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(length = 255)
    private String profileImagePath;

    @Column(name = "hire_date", nullable = false)
    private LocalDate hireDate;

    @Column(name = "quit_date")
    private LocalDate quitDate;

    @Column(name = "day_off_remains", nullable = false)
    private Float dayOffRemains;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private EmployeeRole role;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "birthdate")
    private LocalDate birthdate;

    @Column(name = "zip_address", columnDefinition = "CHAR(5)")
    private String zipAddress;

    @Column(name = "road_address", length = 255)
    private String roadAddress;

    @Column(name = "detail_address", length = 255)
    private String detailAddress;

    public void update(EmployeeDto employeeDto, String profileImagePath) {
        this.department = employeeDto.getDepartment();
        this.profileImagePath = profileImagePath;
        this.phone = employeeDto.getPhone();
    }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateNewAuthToken(String uuid) {
        this.authToken = uuid;
    }

}
