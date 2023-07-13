package com.yedam.app.dept.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.dept.mapper.DeptMapper;
import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Service
public class DeptServiceImpl implements DeptService {

	@Autowired
	DeptMapper deptMapper;
	
	@Override
	public List<DeptVO> selectDeptList() {
		
		return deptMapper.selectDeptList();
	}

	@Override
	public DeptVO selectDeptInfo(DeptVO deptVO) {
		// TODO Auto-generated method stub
		return deptMapper.selectDeptInfo(deptVO);
	}

	@Override
	public int insertDeptInfo(DeptVO deptVO) {
		int result = deptMapper.insertDeptInfo(deptVO);
		
		if(result == 1) {
			return deptVO.getDepartmentId();
			//selectKey값을 가져옴
		} else {
			return -1;
		}

	}

	@Override
	public Map<String, String> updateDeptInfo(DeptVO deptVO) {
		Map<String, String> map = new HashMap<>();
		
		map.put("부서번호", String.valueOf(deptVO.getDepartmentId()));
		
		int result = deptMapper.updateDeptInfo(deptVO);
		if(result > 0) {
			map.put("결과", "Success");
		} else {
			map.put("결과", "Fail");
		}
		return map;
	}

	@Override
	public Map<String, String> deleteDeptInfo(int deptId) {
		Map<String, String> map = new HashMap<>();
		map.put("부서번호", String.valueOf(deptId));
		int result = deptMapper.deleteDeptInfo(deptId);
		
		if(result > 0) {
			map.put("결과", "Success");
		} else {
			map.put("결과", "Fail");
		}
		return map;
	}

}
