package com.yedam.app.dept.service;

import java.util.List;
import java.util.Map;

public interface DeptService {
	
	//전체
	public List<DeptVO> selectDeptList();
	
	//단건
	public DeptVO selectDeptInfo(DeptVO deptVO);
	
	//등록
	public int insertDeptInfo(DeptVO deptVO);
	
	//수정
	public Map<String, String> updateDeptInfo(DeptVO deptVO);
	
	//삭제
	public Map<String, String> deleteDeptInfo(int deptId);
}
