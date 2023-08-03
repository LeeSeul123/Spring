package com.yedam.web.emp.service;

import java.util.Date;

import lombok.Data;

@Data
//값을 넣기도 하고 받기도 하면 @Data사용해야함
public class EmpVO {
	private int employeeId;
	private String firstName;
	private String lastName;
	private String email;
	private Date hireDate;
	private String jobId;
}
