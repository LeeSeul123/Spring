package com.yedam.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

//빈으로 등록된 mapper를 확인할 것이기 때문에
@RunWith(SpringJUnit4ClassRunner.class)
//classpath부터 시작하는 게 아니면 file부터 잡아야함
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/database-context.xml")
public class EmpMapperClient {
	
	
	//mapper불러옴
	@Autowired
	EmpMapper empMapper;
	
	
	
	
	//전체 조회 테스트
	//@Test
	public void selectAllEmp() {
		List<EmpVO> empList = empMapper.selectEmpAllList();
		assertTrue(!empList.isEmpty());	//isEmpty -> 값이 비어있을 때 true. 데이터가 넘어온다면 List가 비어있지 않을 것. assertTrue는 true를 원해서 원하는 방향으로 반전시킨 것
		
	
		
	}
	
	//단건 조회 테스트
	@Test
	public void selectEmpInfo() {
		//조회할 번호를 객체로 넘김
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		//empMapper를 실행시켰을 때 결과를 별도의 변수에 담음
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");	//정상적으로 데이터가 왔다면 LastName이 King이여야함
	}
	
	//Run as -> run configuration : 실행시킬 때 설정 변경(하나만 골라서 실행시키고 싶을 때, annotation을 지우는 것도 하나의 방법이지만 이 방법도 있다)
}
