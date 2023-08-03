package com.toy4.domain.dayOffHistory.dto;

import com.toy4.domain.dayOffHistory.domain.DayOffHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DayOffHistoryResponse {

    private final Long dayOffId;
    private final String type;
    private final String status;
    private final String startDate;
    private final String endDate;
    private final Float amount;
    private final String reason;

    public static DayOffHistoryResponse from(DayOffHistory entity) {
        return DayOffHistoryResponse.builder()
                .dayOffId(entity.getId())
                .type(entity.getDayOff().getType().getType())
                .status(entity.getStatus().getDescription())
                .startDate(entity.getStartDate().toString())
                .endDate(entity.getEndDate().toString())
                .amount(entity.getTotalAmount())
                .reason(entity.getReason())
                .build();
    }
}
