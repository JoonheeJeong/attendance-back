package com.toy4.domain.schedule;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.dayOffHistory.repository.DayOffHistoryRepository;
import com.toy4.domain.dutyHistory.domain.DutyHistory;
import com.toy4.domain.dutyHistory.repository.DutyHistoryRepository;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.global.response.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ScheduleMainService {

    private final EmployeeRepository employeeRepository;
    private final DayOffHistoryRepository dayOffHistoryRepository;
    private final DutyHistoryRepository dutyHistoryRepository;

    @Transactional
    public ScheduleResponse getSchedules(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new EmployeeException(ErrorCode.EMPLOYEE_NOT_FOUND));
        List<DayOffHistory> dayOffHistories = dayOffHistoryRepository.findByEmployeeId(employeeId);
        List<DutyHistory> duties = dutyHistoryRepository.findByEmployeeId(employeeId);

        return ScheduleResponse.builder()
                .name(employee.getName())
                .email(employee.getEmail())
                .dayOffRemains(employee.getDayOffRemains())
                .dayOffHistories(dayOffHistories)
                .duties(duties)
                .build();
    }
}
