package com.toy4.domain.dutyHistory.dto;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class DutyCancellationRequest {

    @NotNull(message = "employeeId는 입력 필수입니다.")
    private Long employeeId;
    @NotNull(message = "상태는 입력 필수입니다.")
    private String status;
}
