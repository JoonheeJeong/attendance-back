package com.toy4.domain.schedule;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.dayOffHistory.repository.DayOffHistoryRepository;
import com.toy4.domain.dutyHistory.domain.DutyHistory;
import com.toy4.domain.dutyHistory.repository.DutyHistoryRepository;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.toy4.global.response.type.ErrorCode.EMPLOYEE_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ScheduleMainServiceTest {

    @InjectMocks
    private ScheduleMainService scheduleMainService;
    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private DayOffHistoryRepository dayOffHistoryRepository;
    @Mock
    private DutyHistoryRepository dutyHistoryRepository;

    @DisplayName("[성공] 스케쥴 목록")
    @Test
    public void whenGetSchedules_success() {
        final Employee employee = mock(Employee.class);
        final Long employeeId = 1L;
        final String employeeName = "김두한";
        final String employeeEmail = "doohankim@email.com";
        final Float employeeDayOffRemains = 15.f;

        when(employee.getName()).thenReturn(employeeName);
        when(employee.getEmail()).thenReturn(employeeEmail);
        when(employee.getDayOffRemains()).thenReturn(employeeDayOffRemains);

        final List<DayOffHistory> dayOffHistories = new ArrayList<>();
        dayOffHistories.add(mock(DayOffHistory.class));
        dayOffHistories.add(mock(DayOffHistory.class));

        final List<DutyHistory> duties = new ArrayList<>();
        duties.add(mock(DutyHistory.class));
        duties.add(mock(DutyHistory.class));
        duties.add(mock(DutyHistory.class));

        when(dayOffHistoryRepository.findByEmployeeId(employeeId)).thenReturn(dayOffHistories);
        when(dutyHistoryRepository.findByEmployeeId(employeeId)).thenReturn(duties);
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        ScheduleResponse scheduleResponse = scheduleMainService.getSchedules(employeeId);

        verify(employeeRepository, times(1)).findById(employeeId);
        verify(dayOffHistoryRepository, times(1)).findByEmployeeId(employeeId);
        verify(dutyHistoryRepository, times(1)).findByEmployeeId(employeeId);

        assertThat(scheduleResponse).isNotNull();
        assertThat(scheduleResponse.getName()).isEqualTo(employeeName);
        assertThat(scheduleResponse.getEmail()).isEqualTo(employeeEmail);
        assertThat(scheduleResponse.getDayOffRemains()).isEqualTo(employeeDayOffRemains);
        assertThat(scheduleResponse.getDayOffHistories()).hasSize(dayOffHistories.size());
        assertThat(scheduleResponse.getDuties()).hasSize(duties.size());
    }

    @DisplayName("[예외] 스케쥴 목록")
    @Test
    public void whenGetSchedules_throwEmployeeExceptionNotFound() {
        when(employeeRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> scheduleMainService.getSchedules(anyLong()))
                .isInstanceOf(EmployeeException.class)
                .hasFieldOrPropertyWithValue("errorCode", EMPLOYEE_NOT_FOUND);
    }
}
