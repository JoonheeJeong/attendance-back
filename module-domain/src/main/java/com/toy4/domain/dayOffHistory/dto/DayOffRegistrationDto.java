package com.toy4.domain.dayOffHistory.dto;

import com.toy4.domain.dayoff.type.DayOffType;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class DayOffRegistrationDto {

    private final Long employeeId;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final DayOffType type;
    private final String reason;
}
