package com.spring.anotation;

import org.springframework.context.support.GenericXmlApplicationContext;

public class AnotationMainExample {
	public static void main(String[] args) {
		//xml파일 기반 bean 등록, bean 가져오기
		
		//자동 생성되는 컨테이너가 아니라서 생성
		GenericXmlApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		//꺼내옴
		TV tv = (TV)ctx.getBean("tv");
		tv.on();
		
		//빈등록하는 방법만 다르고 컨테이너 생성, 꺼내오는건 같음
		
	}
}
