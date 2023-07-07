package com.spring.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.spring.anotation.TV;

public class XmlMainExample {
	public static void main(String[] args) {
		//xml파일 기반 bean 등록, bean 가져오기
		
		//자동 생성되는 컨테이너가 아니라서 생성
		GenericXmlApplicationContext ctx
		= new GenericXmlApplicationContext("classpath:applicationContext.xml");
		
		//꺼내옴
		TV tv = (TV)ctx.getBean("xmlTv");
		tv.on();
		
		
	}
}
