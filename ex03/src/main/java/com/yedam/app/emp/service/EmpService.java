package com.yedam.app.emp.service;

import java.util.List;
import java.util.Map;

public interface EmpService {
	//전체조회
	public List<EmpVO> getEmpAll();
		
	//단건조회
	public EmpVO getEmp(EmpVO empVO);	
	
	//등록
	public int insertEmp(EmpVO empVO);
	
	//수정 - 급여 갱신
	public String updateEmpSal(int empId, int raise);
	
	//수정 - 사원 정보
	public Map<String, String> updateEmp(EmpVO empVO);
	//Map은 객체와 가장 유사한 형태. 기능을 수행했을 때 객체형태로 담을 때 반드시 객체로 담을 필요는 없음.
	//가장 많이 쓰는 형태 : String, Object(자바 안에 존재하는 모든 값 담기 가능)
	
	//삭제
	public Map<String, String> deleteEmp(int empId);
}
