package com.yedam.app.emp.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	@Autowired
	EmpService empService;
	
	//전체조회
	@GetMapping("empList")
	public String empList(Model model) {
		model.addAttribute("empList", empService.getEmpAll());
		return "emp/empList"; 
	}
	
	//단건조회
	@GetMapping("empInfo")
	public String empInfo(Model model, EmpVO empVO) {
		EmpVO findVO = empService.getEmpInfo(empVO);
		model.addAttribute("empInfo", findVO);
		
		return "emp/empInfo";
	}
	
	//등록 - form
	@GetMapping("empInsert")
	public String empInsertForm(Model model) {
		model.addAttribute("empVO", new EmpVO());
		//빈 객체를 넘겨줌
		//타임리프 - 해당 페이지의 폼 태그에 연결할 수 있음
		return "emp/empInsert";
	}
	
	
	//등록 - 처리
	@PostMapping("empInsert")
	public String empInsertProcess(EmpVO empVO) {
		empService.insertEmpInfo(empVO);
		return "redirect:/empList";
	}
}
