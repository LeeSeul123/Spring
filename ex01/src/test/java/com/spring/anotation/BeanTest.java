package com.spring.anotation;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.junit.Restaurant;

@RunWith(SpringJUnit4ClassRunner.class)
//컨테이너를 만들어서 관리. 무엇을 등록할 지는 따로 지정해줘야 함

@ContextConfiguration(locations = "classpath:applicationContext.xml")//컨테이너 만들 때 사용할 xml파일 위치 지정

public class BeanTest {
	//junit은 메소드 위에 test적어주면 됨(기본형태). 실행은 run as
	
	//@Test -> 테스트가 완료되면 주석처리해서 다음 테스트엔 안 돌도록 함
	public void test() {
		System.out.println("단순 테스트");
	}
	
	
	//spring에서 junit
	@Autowired
	ApplicationContext ctx;
	//applicationContext는 인터페이스고 이걸 구현한 클래스를 씀. 컨테이너를 관리하는 그 주체자체를 끌고옴
	
	//@Test
	public void createBeanTest() {
		TV tv = (TV)ctx.getBean("tv");
		//TV tv = (TV)ctx.getBean("xmlTv"); -> 빈에 등록되지 않아서 오류남
		assertNotNull(tv);
	}
	
	//applicationcontext 아예 들고와서 끄집어내거나
	
	//필드로 들고와서 테스트 해도됨
	@Autowired
	Restaurant res;
	
	
	@Test
	public void createRestaurantTest() {
		res.open();
	}
	
}
