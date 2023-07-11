package com.yedam.app.emp.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.emp.service.EmpVO;

@Controller
public class EmpController {
	
	//기능을 EmpService가 가지고 있음
	@Autowired
	EmpService empService;
	
	//조회(데이터, 일반페이지) -> GET
	//등록, 수정, 삭제 		-> POST ( 데이터 자체를 조작해서 )
	//보내는 데이터가 오픈되어도 되는지로 구분
	
	//조회방식은 외부에 데이터 오픈해도 됨
	//헤더정보기반 -> 어디로 보내면 되는지, 잘 찾아왔는지 등 메타정보 받은 후 통과되면 body -> body는 body내부에 데이터 숨긴거. 보여주고 있는 데이터는 아님
	//post -> 목적지까지 서버를 거치다보면 어디 보내야할지 알아야해서 헤더 해석함. post가 보안상 get보단 나은거지 보안에 특화X. get은 가리는 게 없음. 그래서 일반조회
	
	
	//전체조회
	@GetMapping("/empList")
	public String getEmpAllList(Model model) {
		//model -> 받는 건 없지만 보내주는 건 있어서 사용
		model.addAttribute("empList", empService.getEmpAll());		//empService를 통해서 전체 데이터를 가져옴 그리고 그걸 보낼 때 key값을 empList로 사용
		
		return "emp/empList";	// /(슬러시)는 경로를 표시한 거. string이 기본적으로 파일. 특정 폴더밑에 어떤 파일을 요구합니다 로 할 수도 있음. 한 단어만 사용하진 않음(기본적으로 앞뒤에 붙는건 servlet-context)
		// /WEB-INF/views/emp/empList.jsp -> viewresolver는 접두사와 접미사 기반으로 어디에 존재하는 어떤 파일인지 알려줌
		//이경로를 기반으로 생성만 해주면 됨
	}
	
	
	
	
	//단건조회
	@GetMapping("/empInfo")
	public String getEmpInfo(EmpVO empVO, Model model) {
		//정보(primary key필요 -> 커맨드 객체 or RequestParam필요) 근데 mapper에서 vo객체가 필요하다고 정의해서커맨드 객체 사용
		EmpVO findVO = empService.getEmp(empVO);
		model.addAttribute("empInfo", findVO);
		//모델에 남아서는 비동기 처리 못함 -> model에 담았다 = 페이지를 요구한다(String)
		return "emp/empInfo";
	}
	
	
	
	//등록 - Form
	//하나의 기능 -> 하나의 컨트롤러(페이지 만들고 사용자가 입력한 것을 등록하는 작업)
	//경로자체는 같지만 method를 다르게 해서 처리
	@GetMapping("/empInsert")
	public String empInsertForm() {
		return "emp/empInsert"; 
		//단순히 페이지만 요청
	}
	
	
	//등록 - Process
	@PostMapping("/empInsert")
	public String empInsertProcess(EmpVO empVO, RedirectAttributes rtt) {	//Model은 새로 요청하면 값을 유지 못함(request, response가 그럼)
		//가지고 온 데이터를 특정한 커맨드 객체에 담음 그리고 그걸 empService를 통해 등록시킬 거
		int empId = empService.insertEmp(empVO);
		String result = null;
		if(empId == -1) {
			result = "정상적으로 등록되지 않았습니다.";
		} else {
			result = "정상적으로 등록되었습니다."
					+ "\n 등록된 사원의 사번은 "+ empId + "입니다.";
		}
		rtt.addFlashAttribute("result", result);
		//등록이 완료되고 나면 페이지를 전체조회 페이지로 보냄(전체 조회 메소드의 return값이랑 똑같으면 안됨)
		return "redirect:empList";
		
		//redirect : view resolver 동작X . dispatcher servlet이 그냥 보내버림(재요청)
		//:외부에서 접근하고자하는 경로
		
		//사용자가 원하든 안원하든 강제로 redirect일어남
		
	}
//또 다른 controller호출 방법 2가지 : 1.redirect 2.(dispatcher) forward
		//클라이언트가 요청한 건 controller인데 실제로는 페이지가 반환됨 -> controller가 페이지를 호출해서 그걸 넘겨주는 거. 원래는 2개의 단계를 거치는 게 맞음
		//forward : 서버에서 몇단계를 거치고, 뭘하는 지 클라이언트가 모름. forward는 호출할 때 브라우저가 바뀌지 않음 -> views/emp/empInfo.jsp를 호출하는데 실제 페이지 uri랑 다름. 근데 사용자. request와 response가 여러단계를 거쳐도 알 수 없음. 최초의 request와 response를 그대로 유지(처음 데이터 유지해서 컨트롤러 동작. request의 response내용이 계속 유지됨). 보안상 위험이 있는 내부 처리할 때 forward사용
		//최초의 경로 유지. 그냥 model에 계속 값을 담으면 됨
		//redirect: 사용자에게 보여주기 위해 컨트롤러 한번 더 거침. 응답이 가는데 사용자가 인식하기전에 재요청이 들어감. 사용자가 요청한거를 해당 컨트롤러가 온전하게 처리할 수 없어서 사용자가 재요청. 최초 요청한 request와 reponse가 유지가 안 됨. redirect를 요청해서 데이터를 유지해야하면 get방식으로만 가능. body에 값을 넣어도 다음 컨트롤러에서는 사라져있음. 단,get방식으로 요청할 때에는 경로로 요청하기 때문에 get은 유지됨-> get방식만 가능
		//경로가 아예 바뀜(통신 2개 일어남. 첫번째 통신에서 300번뜸). model에 기껏 담아서 돌려줘도 클라이언트가 반응하면 request와 response깨지고 다시 요청하면 새로운 request,response생성
		//get방식 문제 : 보안이 하나도 안됨
		
		//response.sendReDirect : 모델의 데이터는 의미 X
		//.getRequestDispatcher("/list.jsp") 	.forward(request, response);
		
		//redirect : 모델하면 클라이언트한테 갔을 떄 깨지게 됨. -> RedirectAttibutes (redirect로 데이터 여러개 보내야 할 경우 새로운 클래스 사용하라고 함. 얘는 사용할 수 있는 메소드2개있음)
		//addAttribute : 메소드를 이용해서 값을 집어넣은 것처럼 보이지만, 경로에 값이 붙는 형태로 감 ?page=value (보안 취약). 경로에 붙어서 requestParam, 커맨드 객체 형태로 데이터 받을 수 있음
		//addFlashAttribute : 위 보안을 보완. redirect하기 전에 session에 값을 복사함. 그리고 redirect 발생하는 순간 session에 있는 값을 request와 response에 집어넣어서 보내버림(재사용 불가능함). Flash = 일회성. 처리방식이 어려움. 경로뿐만아니라 body에도 값이 없음
		//addAttribute 꺼내올때는 RequestContextUtils.getInputFlashMap(request); -> request가 가진 FlashMap이라는 일회성 공간에서 직접 끄집어내야함
		//새로운 컨트롤러를 거쳐 새로운 페이지가도 addFlashAttribute쓰면 값 유지됨
		//redirect : 경로가 아예달라짐. 최초의 경로 없어지고 강제로 컨트롤러 요청 들어감
		//데이터 보낼떄 저장방식이 다름
	
	
	//수정은 결과를 데이터로 보냄(페이지를 요청하진 않음)-> getMapping없음
	//수정 - Process
	// 1) Client -JSON-> SERVER : @RequestBody /Client가 보내온 데이터가 JSON -> Server
	// 2) Server -JSON-> Client : @ResponseBody
	@PostMapping("/empUpdate")
	//특정 사원에 대한 정보가 필요해서 매개변수로 EmpVO사용 (서비스를 이용해서 데이터를 넘길때도 용이함)
	
	//객체 타입으로 데이터를 넘길 때는 페이지를 반환하지 않는다고 알려줘야함(메소드위에)
	@ResponseBody	//return하는 거에대해서 json으로 변환
	public Map<String, String> empUpdateProcess(@RequestBody EmpVO empVO){	//json으로 들어오는 변수앞에 선언
		return empService.updateEmp(empVO);	//받은 거 그대로 넘김
	}
	//비동기 통신으로 통신할 때 json으로 받고 json으로 처리하는 형태를 띔
	
}