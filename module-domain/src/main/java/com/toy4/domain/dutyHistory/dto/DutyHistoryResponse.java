package com.toy4.domain.dutyHistory.dto;

import com.toy4.domain.dutyHistory.domain.DutyHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DutyHistoryResponse {

    private final Long dutyId;
    private final String status;
    private final String date;
    private final String reason;

    public static DutyHistoryResponse from(DutyHistory entity) {
        return DutyHistoryResponse.builder()
                .dutyId(entity.getId())
                .status(entity.getStatus().getDescription())
                .date(entity.getDate().toString())
                .reason(entity.getReason())
                .build();
    }
}
