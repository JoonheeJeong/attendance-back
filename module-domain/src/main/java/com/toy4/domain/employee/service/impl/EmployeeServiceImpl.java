package com.toy4.domain.employee.service.impl;

import com.toy4.domain.RefreshToken.domain.RefreshToken;
import com.toy4.domain.RefreshToken.repository.RefreshTokenRepository;
import com.toy4.domain.dayOffByPosition.domain.DayOffByPosition;
import com.toy4.domain.dayOffByPosition.exception.DayOffByPositionException;
import com.toy4.domain.dayOffByPosition.repository.DayOffByPositionRepository;
import com.toy4.domain.department.domain.Department;
import com.toy4.domain.department.exception.DepartmentException;
import com.toy4.domain.department.repository.DepartmentRepository;
import com.toy4.domain.department.type.DepartmentType;
import com.toy4.domain.employee.domain.Employee;
import com.toy4.domain.employee.dto.EmployeeDto;
import com.toy4.domain.employee.dto.MyPageResponse;
import com.toy4.domain.employee.dto.PersonalInfoResponse;
import com.toy4.domain.employee.exception.EmployeeException;
import com.toy4.domain.employee.repository.EmployeeRepository;
import com.toy4.domain.employee.service.EmployeeService;
import com.toy4.domain.position.domain.Position;
import com.toy4.domain.position.exception.PositionException;
import com.toy4.domain.position.repository.PositionRepository;
import com.toy4.domain.position.type.PositionType;
import com.toy4.domain.status.domain.Status;
import com.toy4.domain.status.exception.StatusException;
import com.toy4.domain.status.repository.StatusRepository;
import com.toy4.domain.status.type.StatusType;
import com.toy4.global.file.component.EmployeeProfileImageService;
import com.toy4.global.jwt.JwtProvider;
import com.toy4.global.response.dto.CommonResponse;
import com.toy4.global.response.service.ResponseService;
import com.toy4.global.response.type.ErrorCode;
import com.toy4.global.toekn.dto.TokenDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;

import static com.toy4.domain.employee.type.EmployeeRole.USER;
import static com.toy4.domain.position.type.PositionType.STAFF;
import static com.toy4.domain.status.type.StatusType.JOINED;
import static com.toy4.global.response.type.ErrorCode.*;
import static com.toy4.global.response.type.SuccessCode.*;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final ResponseService responseService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final DayOffByPositionRepository dayOffByPositionRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;
    private final StatusRepository statusRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final EmployeeProfileImageService employeeProfileImageService;

    @Override
    @Transactional
    public CommonResponse<?> updateEmployeeInfo(EmployeeDto employeeDto, MultipartFile profileImageFile) {
        Employee employee = employeeRepository.findById(employeeDto.getId())
                .orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));

        String profileImagePath = employeeProfileImageService.getDefaultFile();

        if (!profileImageFile.isEmpty()) {
            profileImagePath = employeeProfileImageService.saveFile(profileImageFile);
        }

        employeeProfileImageService.removeIfFileExists(employee.getProfileImagePath());

        employee.update(employeeDto, profileImagePath);

        return responseService.success(employee.getId(), COMPLETE_PERSONAL_INFO_UPDATE);
    }

	@Override
	@Transactional(readOnly = true)
	public CommonResponse<?> getEmployeeInfo(Long id) {
		Employee employee = employeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));
		PersonalInfoResponse response = PersonalInfoResponse.from(employee);
		return responseService.success(response, SUCCESS);
	}

	@Override
	public CommonResponse<?> getMyPage(Long id) {
		Employee employee = employeeRepository.findById(id)
			.orElseThrow(() -> new EmployeeException(ErrorCode.ENTITY_NOT_FOUND));
		MyPageResponse response = MyPageResponse.from(employee);
		return responseService.success(response, SUCCESS);
	}

   
   @Override
   public CommonResponse<?> validateUniqueEmail(String email) {
       // 1. 유효성 검사(이메일 중복 확인)
       validateEmailDuplication(email);
       return responseService.successWithNoContent(AVAILABLE_EMAIL);
   }

   @Override
   @Transactional
   public CommonResponse<?> signup(EmployeeDto request, MultipartFile profileImageFile) {
       // 1. 유효성 검사(이메일 및 전화번호 중복 확인)
       validateEmailDuplication(request.getEmail());
       validatePhoneDuplication(request.getPhone());

       // 2. 유효성 검사(비밀번호 일치 여부 확인)
       validatePasswordMatch(request.getPassword(), request.getConfirmPassword());

       // 3. 이미지 정보 확인
       String profileImagePath = profileImageFile.isEmpty() ?
               employeeProfileImageService.getDefaultFile()
//               : employeeProfileImageService.saveFile(request.getId(), profileImageFile);
               : employeeProfileImageService.saveFile(profileImageFile);

       // 4. 저장 정보 확인
       Department department = getDepartmentByType(request.getDepartmentType());
       Position position = getPositionByType(STAFF);
       Status status = getStatusByType(JOINED);

       // 5. 직급별 연차 개수 확인
       DayOffByPosition dayOffByPosition = getDayOffByPosition(position.getId());

       // 6. 회원 정보 저장
       Employee employee = employeeRepository.save(Employee.builder()
                .profileImagePath(profileImagePath)
                .position(position)
                .department(department)
                .status(status)
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .hireDate(request.getHireDate())
                .dayOffRemains((float) dayOffByPosition.getAmount())
                .role(USER)
                .build());

        return responseService.success(employee.getId(), COMPLETE_SIGNUP);
    }

   @Override
   @Transactional
   public CommonResponse<?> login(EmployeeDto request) {
       // 1. 이메일로 회원 정보 확인
       Employee employee = getEmployeeByEmail(request.getEmail());
        
       // 2. 비밀번호 확인
       validatePasswordWithDB(request.getPassword(), employee.getPassword());

       // 3. 토큰 발급
       TokenDto token = jwtProvider.generateToken(request.getEmail(), request.getRole());
       String refreshToken = token.getRefreshToken();

       Long employeeId = employee.getId();
       RefreshToken currentToken = refreshTokenRepository.findByKey(employeeId).orElse(null);
       if (currentToken != null) {
           currentToken.updateToken(refreshToken);
           refreshTokenRepository.save(currentToken);
       } else {
           refreshTokenRepository.save(
                   RefreshToken.builder()
                           .key(employeeId)
                           .token(refreshToken)
                           .build());
       }

       HashMap<String, Object> map = new HashMap<>();
       map.put("id", employeeId);
       map.put("token", token);

       return responseService.success(map, SUCCESS);
   }

    /** 입력받은 비밀번호가 올바른지 확인 */
   private void validatePasswordWithDB(String inputPassword, String encodedPassword) {
       if (!passwordEncoder.matches(inputPassword, encodedPassword)) {
           throw new EmployeeException(MISMATCH_PASSWORD);
       }
   }

   /** 이메일로 회원 조회 */
   private Employee getEmployeeByEmail(String email) {
       return employeeRepository.findByEmail(email)
               .orElseThrow(() -> new EmployeeException(INVALID_EMAIL));
   }

   /** 이메일 중복 여부 확인 */
   private void validateEmailDuplication(String email) {
       if (employeeRepository.existsByEmail(email)) {
           throw new EmployeeException(ALREADY_EXISTS_EMAIL);
       }
   }

   /** 전화번호 중복 여부 확인 */
   private void validatePhoneDuplication(String phone) {
       if (employeeRepository.existsByPhone(phone)) {
           throw new EmployeeException(ALREADY_EXISTS_PHONE);
       }
   }

   /** 비밀번호 일치 여부 확인 */
   private void validatePasswordMatch(String password, String confirmPassword) {
       if (!password.equals(confirmPassword)) {
          throw new EmployeeException(MISMATCH_PASSWORD);
       }
   }

   /** 직급 조회 */
   private Position getPositionByType(PositionType type) {
       return positionRepository.findByType(type)
               .orElseThrow(() -> new PositionException(INVALID_REQUEST_POSITION_TYPE));
    }

   /** 회원 상태 조회 */
   private Status getStatusByType(StatusType type) {
       return statusRepository.findByType(type)
               .orElseThrow(() -> new StatusException(INVALID_REQUEST_STATUS_TYPE));
   }

   /** 회원 부서 조회 */
   private Department getDepartmentByType(DepartmentType type) {
       return departmentRepository.findByType(type)
               .orElseThrow(() -> new DepartmentException(INVALID_REQUEST_DEPARTMENT_TYPE));
   }

   /** 직급별 연차 개수 조회 */
   private DayOffByPosition getDayOffByPosition(Long positionId) {
       return dayOffByPositionRepository.findByPositionId(positionId)
               .orElseThrow(() -> new DayOffByPositionException(INVALID_REQUEST_POSITION_ID));
   }
}
