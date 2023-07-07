package com.spring.junit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//관리 대상으로 등록
public class Restaurant {
	//상속, 구현관계X. Chef가 하나의 필드로 들어옴
	
	@Autowired
	//autowired먼저 체크x restaurant보다가 autowired보고 chef주입?
	//기존의 어떤 bean과 관계가 있음을 지정(기존의 bean을 적절하게 주입)
	Chef chef;
	
	//new연산자가 없어서 전제조건 : 레스토랑 생성시점에 내부를 채워줌. chef안채우면 오류남
	public void open() {
		chef.cooking();
		//메소드를 호출하는 시점엔 무조건 chef가 존재해야함
	}
}
