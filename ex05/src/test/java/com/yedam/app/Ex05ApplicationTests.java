package com.yedam.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@SpringBootTest
//스프링 관련 정보 끌고옴
class Ex05ApplicationTests {

	@Autowired
	EmpMapper empMapper;
	
	@Test
	void selectAllTest() {
		List<EmpVO> empList = empMapper.selectAllEmp();
		assertTrue(!empList.isEmpty());
		//비어있지 않으면 제대로 동작한다는 뜻
	}

}
