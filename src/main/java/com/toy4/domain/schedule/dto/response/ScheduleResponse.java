package com.toy4.domain.schedule.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScheduleResponse {

    private String name;
    private String email;
    private Float dayOffRemains;
    private List<ScheduleDayOffResponse> dayOffs;
    private List<ScheduleDutyResponse> duties;
}
