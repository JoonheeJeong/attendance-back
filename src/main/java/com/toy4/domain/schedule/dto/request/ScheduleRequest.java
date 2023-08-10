package com.toy4.domain.schedule.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScheduleRequest {

    @NotNull
    private Long employeeId;
}
