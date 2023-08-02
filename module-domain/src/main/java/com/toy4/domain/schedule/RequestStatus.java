package com.toy4.domain.schedule;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RequestStatus {
    REQUESTED("요청됨"),
    CANCELLED("취소됨"),
    ACCEPTED("승인됨"),
    REJECTED("거절됨")
    ;

    private final String description;
}
