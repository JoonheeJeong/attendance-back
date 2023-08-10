package com.toy4.domain.dayOffHistory.service;

import com.toy4.domain.dayOffHistory.dto.DayOffStatusUpdate;
import com.toy4.domain.dayOffHistory.dto.response.ApprovedDayOffResponse;
import com.toy4.domain.dayOffHistory.dto.response.DayOffHistoriesResponse;

import java.util.List;

public interface DayOffHistoryService {
	List<ApprovedDayOffResponse> getApprovedDayOffsOfEmployee(Long EmployeeId);
	List<DayOffHistoriesResponse> getDayOffs();
	void updateDayOffStatus(DayOffStatusUpdate dto);
}
