package com.toy4.domain.schedule;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import com.toy4.domain.dutyHistory.domain.DutyHistory;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScheduleResponse {

    private String name;
    private String email;
    private Float dayOffRemains;
    private List<DayOffHistory> dayOffHistories;
    private List<DutyHistory> duties;
}
