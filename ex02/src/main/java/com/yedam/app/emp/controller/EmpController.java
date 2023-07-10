package com.yedam.app.emp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.yedam.app.emp.mapper.EmpMapper;
import com.yedam.app.emp.service.EmpVO;

@Controller
//그 어노테이션이 가져야 하는 부분들이 있어서 Component로 퉁치지 않음
public class EmpController {
	
	//mapper가 필요
	@Autowired
	EmpMapper empMapper;
	
	
	//매핑어노테이션 : 요구할 때 어떤식으로 요구할지 정함(대표적으로 경로와 메소드)
	//클라에서 접근할때 어떤 경로, 어떤 컨텐트타입으로 접근해야하는지 결정을 해주는거(클라는 반드시 명세서를 확인해야함) 경로,메소드 결정짓고 
	@RequestMapping(value = "emp", method = RequestMethod.GET)	//get방식으로만 접근하라고 제한을 걺
	
	public String empList(Model model,EmpVO empVO) {	//메소드 시그니처-> empVO는 클라이언트가 보내온 데이터를 받음
		model.addAttribute("emp", empMapper.getEmp(empVO));	//addAtribute -> 값을 보낼때 사용. emp라는 이름으로 특정한 사원에 대한 정보를 보냄.(출력은 emp라는 jsp파일이함) 어떤타입으로 값을 받느냐에따라 매개변수가 달라짐
		return "emp";	//스프링은 기본적으로 사용->jsp파일. 문자열이지만 jsp파일을 찾음(jsp문구가 없더라도)
	}
	
}
