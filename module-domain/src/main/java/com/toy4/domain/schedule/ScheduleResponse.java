package com.toy4.domain.schedule;

import com.toy4.domain.dayOffHistory.dto.DayOffHistoryResponse;
import com.toy4.domain.dutyHistory.dto.DutyHistoryResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ScheduleResponse {

    private String name;
    private String email;
    private Float dayOffRemains;
    private List<DayOffHistoryResponse> dayOffs;
    private List<DutyHistoryResponse> duties;
}
