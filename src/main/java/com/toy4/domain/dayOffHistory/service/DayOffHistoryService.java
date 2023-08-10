package com.toy4.domain.dayOffHistory.service;

import com.toy4.domain.dayOffHistory.dto.DayOffHistoryDto;
import com.toy4.domain.dayOffHistory.dto.response.DayOffHistoriesResponse;
import com.toy4.global.response.dto.CommonResponse;

import java.util.List;

public interface DayOffHistoryService {
	CommonResponse<?> getEmployeeApprovedDayOff(Long EmployeeId);
	List<DayOffHistoriesResponse> getDayOffs();
	CommonResponse<?> updateStatusDayOff(DayOffHistoryDto dto);
}
