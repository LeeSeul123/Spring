package com.yedam.app.emp.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;
import com.yedam.app.tx.mapper.AaaMapper;


@Service

//어노테이션 먼저 붙이기(빈 누락될 수 있음)
//VO 클래스는 @Data
//매퍼는 mybatis - scan
//serviceImpl은 @Service
//controller는 @Controller
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	//@Autowired는 필드별로 적어야 함
	//하나의 클래스 위에 어노테이션이 여러개 붙을 수는 있음. 어노테이션 하나가 필드 2개에 대해서 동작하진 않음
	EmpMapper empMapper;
	
	@Autowired
	AaaMapper aaaMapper;

	@Override
	public List<EmpVO> getEmpAll() {
		//전체 조회와 단건조회는 중간에서 하는 역할이 없어서 매퍼 실행결과를 바로 넘김
		return empMapper.selectEmpAllList();
	}

	@Override
	public EmpVO getEmp(EmpVO empVO) {
		
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int insertEmp(EmpVO empVO) {
		
		int result = empMapper.insertEmpInfo(empVO);
		if(result == 1) {
			return empVO.getEmployeeId();
		} else {
			return -1;	//실패했다고 알려주기 위해서 -1반환(primary key는 무조건 양수. 시퀀스도 양수로 되어있음. 의도적으로 - 넣지 않는 이상 - 될 일 없음) => 정상적으로 동작하지 않았다는 걸 알려줌
		}
	}

	@Override
	public String updateEmpSal(int empId, int raise) {
		String message = null;	//리턴시키기 위한 변수선언과 동시에 초기화
		
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(empId);
		//vo객체를 mapper에 넘겨야 해서 변형시킴
		
		int result = empMapper.updateEmpSal(empVO, raise);
		
		//alert을 띄울 수 있도록 처리한 것
		if(result == 1) {
			message = "정상적으로 급여를 갱신했습니다.";
		} else {
			message = "작업이 실패했습니다. 정보를 확인해주세요.";
		}
		return message;
	}

	@Override
	//VO -> 보내고자하는 데이터가 여러개일때
	public Map<String, String> updateEmp(EmpVO empVO) {
		Map<String, String> map = new HashMap<>();
		
		map.put("사원번호", String.valueOf(empVO.getEmployeeId()));					//업데이트 시도하는 번호를 값으로 집어넣음
		
		int result = empMapper.updateEmpInfo(empVO);
		
		if(result == 1) {
			map.put("결과", "Success");
		} else {
			map.put("결과", "Fail");
		}
		return map;
	}
	
	//맵은 그냥 key, value

	@Override
	public Map<String, String> deleteEmp(int empId) {
		Map<String, String> map = new HashMap<>();
		
		map.put("사원번호", String.valueOf(empId));					//업데이트 시도하는 번호를 값으로 집어넣음
		
		int result = empMapper.deleteEmpInfo(empId);
		
		if(result == 1) {
			map.put("결과", "Success");
		} else {
			map.put("결과", "Fail");
		}
		return map;
	}

}
