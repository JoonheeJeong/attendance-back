package com.toy4.domain.dayOffHistory.dto.request;

import com.toy4.domain.dayOffHistory.dto.DayOffStatusUpdate;
import com.toy4.domain.schedule.RequestStatus;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class DayOffStatusUpdateRequest {

	@NotNull
	private Long dayOffId;
	@NotBlank(message = "상태가 누락되었습니다.")
	private String status;

	public DayOffStatusUpdate toDto() {
		return DayOffStatusUpdate.builder()
			.id(dayOffId)
			.status(RequestStatus.getByDescription(status))
			.build();
	}
}
