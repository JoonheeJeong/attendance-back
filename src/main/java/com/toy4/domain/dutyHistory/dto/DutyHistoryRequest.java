package com.toy4.domain.dutyHistory.dto;

import com.toy4.domain.schedule.RequestStatus;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
public class DutyHistoryRequest {

	@NotNull
	private Long dutyId;
	@NotBlank
	private String status;

	public DutyStatusUpdate toDto() {
		return DutyStatusUpdate.builder()
			.id(dutyId)
			.status(RequestStatus.getByDescription(status))
			.build();
	}
}
