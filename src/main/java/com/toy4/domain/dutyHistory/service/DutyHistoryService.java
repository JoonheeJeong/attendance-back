package com.toy4.domain.dutyHistory.service;

import com.toy4.domain.dutyHistory.dto.DutyStatusUpdate;
import com.toy4.global.response.dto.CommonResponse;

public interface DutyHistoryService {

	CommonResponse<?> getEmployeeApprovedDuty(Long employeeId);
	CommonResponse<?> getDutyHistories();
	void updateDutyStatus(DutyStatusUpdate dto);
}
