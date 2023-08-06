package com.toy4.domain.schedule;

import java.util.Arrays;

import com.toy4.domain.dayoff.exception.DayOffException;
import com.toy4.global.response.type.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    REQUESTED("요청됨"),
    CANCELLED("취소됨"),
    APPROVED("승인됨"),
    REJECTED("거절됨")
    ;

    private final String description;

    public static RequestStatus getByTypeString(String type) {
        return Arrays.stream(values())
            .filter(requestStatus -> requestStatus.getDescription().equals(type))
            .findFirst()
            .orElseThrow(() -> new DayOffException(ErrorCode.INVALID_DAY_OFF_TYPE));
    }
}
