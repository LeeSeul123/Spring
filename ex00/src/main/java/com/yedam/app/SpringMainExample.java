package com.yedam.app;

import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainExample {

	public static void main(String[] args) {
		//컨테이너 생성
		GenericXmlApplicationContext ctx
		
		 = new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		
		//BEAN 호출시 object 타입으로 반환돼서 인터페이스타입으로 변환시킴	
		
		TV tv = (TV)ctx.getBean("tv");	
		tv.on();	
		//new연산자 사용하지 않음
		
		
	}

}
