package com.toy4.domain.dutyHistory.service;

import com.toy4.domain.dutyHistory.dto.DutyStatusUpdate;
import com.toy4.domain.dutyHistory.dto.response.ApprovedDutyResponse;
import com.toy4.domain.dutyHistory.dto.response.DutyHistoriesResponse;

import java.util.List;

public interface DutyHistoryService {

	List<ApprovedDutyResponse> getApprovedDuties(Long employeeId);
	List<DutyHistoriesResponse> getDutyHistories();
	void updateDutyStatus(DutyStatusUpdate dto);
}
