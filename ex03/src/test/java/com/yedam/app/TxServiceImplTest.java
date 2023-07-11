package com.yedam.app;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yedam.app.tx.service.AaaService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "file:src/main/webapp/WEB-INF/spring/**/*-context.xml")
public class TxServiceImplTest {
	
	@Autowired
	AaaService aaaService;
	
	//ServiceImpl에서 @Transactional 붙이고 안붙이고 확인
	@Test
	public void txTest() {
		aaaService.insert();
		
		//결과 : insert는 실패했다고 뜨지만 101은 들어감. 롤백이 일어나더라도 이전꺼가 먼저 오토커밋이 되어버려서.
		
	}
	
}
