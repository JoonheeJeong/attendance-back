package com.toy4.domain.dayoff.type;

import com.toy4.domain.dayoff.exception.DayOffException;
import com.toy4.global.response.type.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum DayOffType {

    FIRST_HALF_DAY_OFF("오전 반차"),
    SECOND_HALF_DAY_OFF("오후 반차"),
    NORMAL_DAY_OFF("연차"),
    SPECIAL_DAY_OFF("특별 휴가")
    ;

    private final String type;

    public boolean isHalfDayOff() {
        return (this == FIRST_HALF_DAY_OFF || this == SECOND_HALF_DAY_OFF);
    }

    public static DayOffType getByTypeString(String type) {
        return Arrays.stream(values())
                .filter(dayOffType -> dayOffType.getType().equals(type))
                .findFirst()
                .orElseThrow(() -> new DayOffException(ErrorCode.INVALID_DAY_OFF_TYPE));
    }
}
