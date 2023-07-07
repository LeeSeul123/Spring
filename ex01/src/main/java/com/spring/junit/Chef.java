package com.spring.junit;

import org.springframework.stereotype.Component;

@Component
//얘를 bean으로 등록(이름은 등록하지 않음)
public class Chef {
	public void cooking() {
		System.out.println("셰프가 요리를 시작합니다.");
	}
}
