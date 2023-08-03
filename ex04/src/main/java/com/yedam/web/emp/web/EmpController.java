package com.yedam.web.emp.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.web.common.PagingVO;
import com.yedam.web.emp.service.EmpService;
import com.yedam.web.emp.service.EmpVO;

@Controller
public class EmpController {
	
	@Autowired
	EmpService empService;
	
	//전체조회(조회는 무조건 GET)
	@GetMapping("empList")
	//생성자를 통해서만 데이터를 연산하도록 처리해놨는데 매개변수에서 VO 커맨드 객체로 처리하면 TOTALDATA가 없게 됨. 없으면 LAST페이지를 계산할 수 없음 등 정상적으로 생성되지 않음.  페이지에서 DB로 직접접근 할 수 없기때문에. 
	//현재 페이지 & 페이지당 보여줄 데이터 수가 필요함. requestParam은 값이 안넘어오면 defaultValue에서 값을 지정해줄 수 있음
	//RequestParam은 int를 안쓰는 게 좋음. 객체 타입으로 타입을 설정하는 게 가장 편함
	
	public String empList(Model model, @RequestParam(value="nowPage", defaultValue="1") Integer nowPage
			//정상적으로 값이 생성될 수 있을 때 커맨드객체를 사용함. 지금은 setter도 없음.
							, @RequestParam(value="cntPerPage", defaultValue="10") Integer cntPerPage) {
		//defaultValue가 null로 될 수도 있고, 숫자가 문자열로 있기 때문에 문자열로 받을 수 있는 객체타입이 나음. 그래서 어차피 자동 언박싱 되기도 해서 @RequestParam은 wrapper객체로 써야한다고 기억하는 게 나음
		int total = empService.empCount();
		PagingVO pagingVO = new PagingVO(total, nowPage, cntPerPage);
		List<EmpVO> empList = empService.getEmpList(pagingVO);
		
		model.addAttribute("empList", empList);
		model.addAttribute("paging", pagingVO);
		//페이징을 하면 List뿐만아니라 paging객체도 꼭 같이넘겨야 함
		return "emp/empList";
	}
	
}
