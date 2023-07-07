package com.spring.anotation;

import org.springframework.stereotype.Component;

@Component(value = "tv")
// = @Component("tv") 빈 등록하면서 이름 붙이기 -> 
public class SamsungTV implements TV {

	@Override
	public void on() {
		System.out.println("어노테이션 방식)삼성 TV를 켭니다.");
	}

}
