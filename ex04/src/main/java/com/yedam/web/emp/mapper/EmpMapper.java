package com.yedam.web.emp.mapper;

import java.util.List;

import com.yedam.web.common.PagingVO;
import com.yedam.web.emp.service.EmpVO;

public interface EmpMapper {
	//전체조회 & 페이징
	
	//총 데이터 조회 쿼리문
	public int getTotalCount();
	
	//전체 조회. 원래 매개변수가 없는데 페이징을 할 경우 매개변수가 필요함
	public List<EmpVO> selectEmpAll(PagingVO pagingVO);
}
