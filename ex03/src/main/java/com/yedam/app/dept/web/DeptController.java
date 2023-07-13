package com.yedam.app.dept.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.dept.service.DeptService;
import com.yedam.app.dept.service.DeptVO;

@Controller
public class DeptController {
	
	@Autowired
	DeptService deptService;
	
	//전체조회
	@GetMapping("/deptList")
	public String getDeptList(Model model) {
		model.addAttribute("deptList", deptService.selectDeptList());
		
		return "dept/deptList";
	}
	
	//단건조회
	@GetMapping("/deptInfo")
	public String getDeptInfo(DeptVO deptVO, Model model) {
		DeptVO findVO = deptService.selectDeptInfo(deptVO);
		
		model.addAttribute("dept", findVO);
		
		return "dept/deptInfo";
	}
	
	//등록 - Form
	@GetMapping("/deptInsert")
	public String getDeptInsertForm() {
		return "dept/deptInsertForm";
	}
	
	//등록 - Process(페이지 변환하지만 돌려줄 곳이 없음)
	@PostMapping("/deptInsert")
	public String getDeptInsertProcess(DeptVO deptVO, RedirectAttributes rtt) {
		int result = deptService.insertDeptInfo(deptVO);
		String message = null;
		String uri = null;
		if(result == -1) {
			message = "정상적으로 등록되지 않았습니다.";
			
			//정상적으로 등록되지 않으면 전체조회로 redirect
			uri = "deptList";
		} else {
			message = "정상적으로 등록되었습니다.\n";
			message += "부서번호 : " + result;
			
			//정상적으로 등록되면 단건조회로 redirect
			uri = "deptInfo?departmentId=" + result;
		}
		rtt.addFlashAttribute("message", message);
		//return "redirect:deptList";		//전체 조회로 페이지를 돌려보낼 때
		
		//addAttribute로 result를 보내도됨(내가 사용하는 controller에 맞춰서 데이터를 보냄)
		return "redirect:" + uri;
	}
	
	//수정(JSON으로 데이터 주고받음) 돌려줄때도 객체타입으로 , 값을 받을 때도 객체타입으로 데이터 받음
	@PostMapping("/deptUpdate")
	@ResponseBody
	public Map<String, String> deptUpdate(@RequestBody DeptVO deptVO){
		return deptService.updateDeptInfo(deptVO);
	}
	
	//삭제
	@PostMapping("/deptDelete")
	@ResponseBody
	public String deptDelete(@RequestParam(name="id") int deptId) {
		Map<String, String> map= deptService.deleteDeptInfo(deptId);
		
		return map.get("결과");
	}
}
