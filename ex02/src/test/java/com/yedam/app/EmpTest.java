package com.yedam.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

//스프링의 mapper 테스트
@RunWith(SpringJUnit4ClassRunner.class)
//클래스 패스 밑에는 빌드와는 상관없는 설정만있음?
//root컨텍스트에 db정보있음
//root context 우클릭 -> properties들어가서 src부터 복사 file을 명시해줘야함
@ContextConfiguration(locations ="file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class EmpTest {
	@Autowired
	EmpMapper empMapper;
	//mapper관련된 파일에 어노테이션 없었음 -> 어노테이션으로 만드는 애 아님.root context에 mapper scan은 인터페이스를 읽어들임. 인터페이스에는 일반적으로 어노테이션X
	
	
	//조회
	@Test
	public void getEmp() {
		EmpVO vo = new EmpVO();
		vo.setEmployee_id("100");
		EmpVO findVO = empMapper.getEmp(vo);
		assertEquals(findVO.getFirst_name(), "Steven");
	}
}
