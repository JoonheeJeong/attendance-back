package com.toy4.domain.dutyHistory.service.impl;

import com.toy4.domain.dutyHistory.domain.DutyHistory;
import com.toy4.domain.dutyHistory.dto.ApprovedDutyResponse;
import com.toy4.domain.dutyHistory.dto.DutyHistoriesResponse;
import com.toy4.domain.dutyHistory.dto.DutyStatusUpdate;
import com.toy4.domain.dutyHistory.exception.DutyHistoryException;
import com.toy4.domain.dutyHistory.repository.DutyHistoryRepository;
import com.toy4.domain.dutyHistory.service.DutyHistoryService;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.schedule.RequestStatus;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.toy4.global.response.type.SuccessCode.SUCCESS;

@RequiredArgsConstructor
@Service
public class DutyHistoryServiceImpl implements DutyHistoryService {

	private final EmployeeRepository employeeRepository;
	private final DutyHistoryRepository dutyHistoryRepository;
	private final ResponseService responseService;

	@Override
	@Transactional
	public List<ApprovedDutyResponse> getApprovedDuties(Long employeeId) {
		employeeRepository.findById(employeeId)
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));

		List<DutyHistory> approvedDuties =
				dutyHistoryRepository.findByEmployeeIdAndStatus(employeeId, RequestStatus.APPROVED);

		return approvedDuties.stream()
			.map(ApprovedDutyResponse::from)
			.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public CommonResponse<?> getDutyHistories() {
		List<DutyHistory> dutyHistories = dutyHistoryRepository.findAll();

		if (dutyHistories.isEmpty()) {
			return responseService.failure(ErrorCode.DUTY_HISTORIES_NOT_FOUND);
		}

		List<DutyHistoriesResponse> responses = dutyHistories.stream()
			.map(DutyHistoriesResponse::from)
			.collect(Collectors.toList());

		return responseService.successList(responses, SUCCESS);
	}

	@Override
	@Transactional
	public void updateDutyStatus(DutyStatusUpdate dto) {
		DutyHistory dutyHistory = dutyHistoryRepository.findById(dto.getId())
			.orElseThrow(() -> new DutyHistoryException(ErrorCode.DUTY_NOT_FOUND));

		dutyHistory.updateStatus(dto.getStatus());
	}
}
