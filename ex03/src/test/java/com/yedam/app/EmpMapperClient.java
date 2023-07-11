package com.yedam.app;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
	//@Test
	public void selectEmpInfo() {
		//조회할 번호를 객체로 넘김
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(100);
		
		//empMapper를 실행시켰을 때 결과를 별도의 변수에 담음
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		assertEquals(findVO.getLastName(), "King");	//정상적으로 데이터가 왔다면 LastName이 King이여야함
	}
	
	//Run as -> run configuration : 실행시킬 때 설정 변경(하나만 골라서 실행시키고 싶을 때, annotation을 지우는 것도 하나의 방법이지만 이 방법도 있다)
	
	//등록 테스트
	//@Test
	public void insertEmpInfo() {
		//insert 전에 어떤 번호를 가질지 사용자에게 보여줘야할 때에는 두개를 분리.
		//selectKey는 연속적으로 작업하기 좋음
		
		EmpVO empVO = new EmpVO();
		//객체 생성시 기본값 -> 참조타입은 NULL, 기본타입은 각각마다 가지는 값이 있음
		//지금 employeeId는 null이 아니라 0을 가짐
		empVO.setLastName("Kang");
		empVO.setFirstName("San-Ha");
		empVO.setEmail("shKang@google.com");
		empVO.setJobId("IT_PROG");	//job_id는 foriegn key라서 등록된 것만 입력가능
		empVO.setSalary(5000);
		
		//DB등록시 유니크키, foriegn키 주의
		
		empMapper.insertEmpInfo(empVO);
		assertNotEquals(empVO.getEmployeeId(), 0);	//값이 제대로 들어갔다면 0은 아닐 것. assert는 참,거짓만 판별가능함
	}
	
	
	
	//급여 수정 테스트
	//@Test
	public void updateEmpSal() {
		//급여 갱신
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(1001);
		int result = empMapper.updateEmpSal(empVO, 10);
		assertEquals(result, 1);
	}
	
	
	//사원 정보 수정
	//@Test
	public void updateEmpInfo() {
		
		EmpVO empVO = new EmpVO();
		empVO.setEmployeeId(1001);
		
		EmpVO findVO = empMapper.selectEmpInfo(empVO);
		System.out.println(findVO);
		
		
		//update로 first, email, salary 변경가능(3개) -> 근데 값을 넘기는건 2개만 넘김
		empVO.setEmail("sanH@naver.com");
		empVO.setSalary(6200);
		//로그 결과보면 , 알아서 처리해줘서 없어짐. set과 if가 같이 동작해서 salary뒤의 ,제거해줌 -> 미완성 SQL문 예방
		
		int result=empMapper.updateEmpInfo(empVO);
		assertEquals(result,1);
	}
	
	
	
	//삭제 테스트
	@Test
	public void deleteEmpInfo() {
		
		int result = empMapper.deleteEmpInfo(1001);
		assertEquals(result,1);
	}
}
