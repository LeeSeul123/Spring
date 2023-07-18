package com.yedam.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//인터페이스 scan하는 건 별도로 필요함 스프링에서도 <mybatis-spring:scan base-package>는 따로 동작함
@MapperScan(basePackages = "com.yedam.app.**.mapper")	//읽어들일 base패키지 설정
public class Ex05Application {

	public static void main(String[] args) {
		SpringApplication.run(Ex05Application.class, args);
	}

}
