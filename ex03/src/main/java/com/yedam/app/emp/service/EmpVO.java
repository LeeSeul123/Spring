package com.yedam.app.emp.service;



import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class EmpVO {
	//db열어서 참고하는 게 좋음
	//bean으로 등록될 필요는 없지만 getter, setter가 필요
	//세팅했을 땐 언더바하면 정상작동 안함
	
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	
	private Date hireDate;
	private String jobId;
	private double salary;
	private double commissionPct;
	private int managerId;
	private int departmentId;
}
