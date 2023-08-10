package com.toy4.domain.dutyHistory.service;

import com.toy4.domain.dutyHistory.dto.response.ApprovedDutyResponse;
import com.toy4.domain.dutyHistory.dto.DutyStatusUpdate;
import com.toy4.global.response.dto.CommonResponse;

import java.util.List;

public interface DutyHistoryService {

	List<ApprovedDutyResponse> getApprovedDuties(Long employeeId);
	CommonResponse<?> getDutyHistories();
	void updateDutyStatus(DutyStatusUpdate dto);
}
