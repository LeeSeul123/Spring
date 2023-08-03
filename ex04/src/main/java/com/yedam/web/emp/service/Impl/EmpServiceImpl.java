package com.yedam.web.emp.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yedam.web.common.PagingVO;
import com.yedam.web.emp.mapper.EmpMapper;
import com.yedam.web.emp.service.EmpService;
import com.yedam.web.emp.service.EmpVO;

@Service
//Impl은 무조건 @Service가 필요함. 클래스 작성전에 해당 클래스가 요구하는 어노테이션 먼저 채우는 게 좋음
//VO = @Data  Service = @Service  Controller = @Controller 먼저 하고 다른 작업
public class EmpServiceImpl implements EmpService {
	
	@Autowired
	EmpMapper empMapper;
	
	@Override
	public int empCount() {
		
		return empMapper.getTotalCount();
	}

	@Override
	public List<EmpVO> getEmpList(PagingVO pagingVO) {
		
		return empMapper.selectEmpAll(pagingVO);
	}

}
