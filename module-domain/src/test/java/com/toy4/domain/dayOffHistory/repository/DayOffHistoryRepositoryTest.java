package com.toy4.domain.dayOffHistory.repository;

import com.toy4.domain.dayOffByPosition.domain.DayOffByPosition;
import com.toy4.domain.dayOffByPosition.repository.DayOffByPositionRepository;
import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.dayoff.domain.DayOff;
import com.toy4.domain.dayoff.repository.DayOffRepository;
import com.toy4.domain.department.domain.Department;
import com.toy4.domain.department.repository.DepartmentRepository;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.employee.type.EmployeeRole;
import com.toy4.domain.position.domain.Position;
import com.toy4.domain.position.repository.PositionRepository;
import com.toy4.domain.schedule.RequestStatus;
import com.toy4.domain.status.domain.Status;
import com.toy4.domain.status.repository.StatusRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
class DayOffHistoryRepositoryTest {

    @Autowired
    private DayOffHistoryRepository dayOffHistoryRepository;
    @Autowired
    private DayOffRepository dayOffRepository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private DayOffByPositionRepository dayOffByPositionRepository;

    @DisplayName("employeeId로 연차 목록 검색")
    @Test
    void findByEmployeeId() {
        Position position = positionRepository.findById(1L).orElseThrow();
        Department department = departmentRepository.findById(1L).orElseThrow();
        Status status = statusRepository.findById(1L).orElseThrow();
        DayOffByPosition dayOffByPosition = dayOffByPositionRepository.findByPositionId(position.getId()).orElseThrow();

        Employee employee;
        employee = Employee.builder()
                .authToken(UUID.randomUUID().toString())
                .position(position)
                .department(department)
                .status(status)
                .name("테스트")
                .email("test@gmail.com")
                .password("testtest1234")
                .hireDate(LocalDate.now())
                .dayOffRemains(dayOffByPosition.getAmount() - 0.5f)
                .role(EmployeeRole.USER)
                .phone("010-1234-5678")
                .birthdate(LocalDate.of(1996, 6, 20))
                .build();
        employee = employeeRepository.save(employee);
//        employee = employeeRepository.findById(1L).orElseThrow();

        DayOff type = dayOffRepository.findById(3L).orElseThrow();

        DayOffHistory dayOffHistoryToSave = DayOffHistory.builder()
                .employee(employee)
                .dayOff(type)
                .status(RequestStatus.REQUESTED)
                .startDate(LocalDate.of(2023, 7, 31))
                .endDate(LocalDate.of(2023, 8, 2))
                .totalAmount(3f)
                .reason("쉼과 재정비를 위하여.")
                .build();
        DayOffHistory dayOffHistorySaved = dayOffHistoryRepository.save(dayOffHistoryToSave);
        assertThat(dayOffHistorySaved).isSameAs(dayOffHistoryToSave);

        List<DayOffHistory> dayOffHistories = dayOffHistoryRepository.findByEmployeeId(employee.getId());

        assertThat(dayOffHistories).hasSize(1);

        DayOffHistory dayOffHistory = dayOffHistories.get(0);
        assertThat(dayOffHistory).isSameAs(dayOffHistorySaved);
    }
}