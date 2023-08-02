package com.toy4.domain.employee.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.employee.dto.EmployeeDto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter
@Builder
public class PersonalInfoRequest {

	@NotNull
	private Long employeeId;
	private DepartmentType department;

	@Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$"
		, message = "전화번호 형식이 맞지 않습니다.(01X-XXX(X)-XXXX)")
	private String phone;

	public static EmployeeDto to(PersonalInfoRequest request) {
		return EmployeeDto.builder()
			.id(request.getEmployeeId())
			.departmentType(request.getDepartment())
			.phone(request.getPhone())
			.build();
	}
}
