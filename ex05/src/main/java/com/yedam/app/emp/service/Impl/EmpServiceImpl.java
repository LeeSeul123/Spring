package com.yedam.app.emp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Service
public class EmpServiceImpl implements EmpService {

	@Autowired
	EmpMapper empMapper;
	
	@Override
	public List<EmpVO> getEmpAll() {
		
		return empMapper.selectAllEmp();
	}

	@Override
	public EmpVO getEmpInfo(EmpVO empVO) {
		
		return empMapper.selectEmpInfo(empVO);
	}

	@Override
	public int insertEmpInfo(EmpVO empVO) {
		
		return empMapper.insertEmpInfo(empVO);
	}

}
