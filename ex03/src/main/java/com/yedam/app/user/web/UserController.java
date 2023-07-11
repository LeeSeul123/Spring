package com.yedam.app.user.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yedam.app.emp.service.EmpService;
import com.yedam.app.user.service.UserListVO;
import com.yedam.app.user.service.UserVO;

@Controller
public class UserController {
	//요청정보 받기
	
	//커맨드 객체
	// ? 뒤에 key, value -> 일반객체로 받음(key가 객체의 필드명이 되어줘야함. 넘어오는 값에 대해서 적절한 타입을 선언해야 함. type mismatch시 못받음)
	//mypage.do?name=choi&age=20
	//private String name;
	//private Integer age;
			
	//커맨드 객체로 리스트를 받을 때 -> 까다로움
	//폼태그로 하나의 배열을 보낼 때는 name의 이름 형태가 -> <imput name=list[0]머시기.	배열, 몇번째 필드값에 들어가는 name인지 처리를 해줘야함. 일반 통신할 때 key, value형태가 되면 직접적으로 어떤 list형태 이름에 몇번째 인덱스로 들어가는지 다 붙어야함. 받는 쪽에서는 객체형태로 받아줘야함. 내부에 list하나만 받는다고해도 됨. 커맨드 형태로 배열 받을때는 넘어오는 name의 배열 이름과 같아야함(requestParam으로 처리안됨) 내부에 List를 담아놓은 형태로 처리. list[0].name list[0].age. 커맨드 객체로 배열, 객체 받기 가능하지만 매개변수로는 객체형태로 들어가야한다.(json쓰는게 나을수도있음)
			
	
	
	//필드 선언 -> 거의 서비스가 될 것
	@Autowired
	EmpService empService;
	
	
	
	//경로를 매핑
	
	@RequestMapping("/getObject")	// 앞에 / 안붙이고 getObject로만 써도됨. value, method, param 가능. 보통 value, method 두가지 사용. 특정한 속성 지정안하고 문자를 넣으면 메소드에는 영향 X. 이 정보 기반으로 매핑됨. 그래서 requestMapping은 메소드 제한 안할 때 많이 사용.
	//메소드 -> 경로 하나하나가 매핑 될 것. String 이름 형태, 정보를 어떤식으로 받을지 구상
	public String getCommandObject(UserVO userVO) {
		
		
		
		System.out.printf("============ %s \n", userVO.getName());
		System.out.printf("============ %d \n", userVO.getAge());
		return "";	
		
		//부메랑 사용(서버가 필요해서) -> 데이터 보낼 때 사용, 결과는 콘솔로 확인 http://localhost/app/getObject -> 404뜸.반환 페이지가 없어서
		//커맨드 객체는 값이 없더라도 상관없음. 없는 상태로 존재할뿐. 커맨드 객체는 데이터가 있으면 값을 담고, 데이터가 없으면 값을 안담고 동작
		//부메랑1.http://localhost/app/getObject -> 데이터가 없어도 된다.
		
		//부메랑2.QueryString에 (get방식) name,age,gender등록. -> 커맨드객체로 처리할 수 있는 것만 받고 나머지는 버림(클라이언트는 원래 서버에서 정한 데이터만 보내야 해서 종속적임)
		//3개의 값만 보내도 2개의 값만 처리가 됨
		//1개의 값만 보내도 하나만 처리하고 null처리.
		//커맨드 객체는 굉장히 유연하게 대응 가능. 값이 없어도 오류가 안남
		//커맨드 객체가 불편한 건 배열 처리가 불편함
		
		//부메랑3.post, body말고 FORM체크
		//커맨드객체는 get방식이나 form 데이터 방식으로 넘어오는 건 get이든 post든 대응이 가능하다
		
		//get이든 post든 key와 value형태로 보낸다. queryString을 만들든가, form태그로 key,value -> 커맨드
		
		
		
	}
	
		//커맨드 객체 - 배열
		
		//어노테이션 사용안할 경우 객체를 사용해야함(데이터 포맷과 컬럼명 신경)
		//서버에 객체 하나 더 만들고 내부에 List타입 만듦. 대신 보내는 쪽에서 name속성이 이상해짐 list[0].name       form태그는 내부에 존재하는게 객체인지 배열인지 모름. 그래서 어디서 어디까지 한개의 객체인지 알려줘야 함
		@RequestMapping("getList")
		//경로 다시 잡음
		
		public String getCommandArray(UserListVO listVO) {
			//겉으로만 봐선 무엇이 배열을 담는지 알 수 없음
			//requestbody를 제외하고는 List형태를 만들 수 없음 -> List를 객체 내부에 저장
			for(UserVO userVO : listVO.getList()) {			//데이터를 가져와서 출력
				//몇개인 지 모르니까 향상된 for문 사용
				System.out.printf("============ %s\n", userVO.getName());
				System.out.printf("============ %d\n", userVO.getAge());
			}

			//일반객체와, 내부에 객체를 집어넣은 상태로 데이터를 보내는 건 다름
			//부메랑4.http://localhost/app/getList queryString (get방식인데, 안되는 이유: 경로에 매핑되어있는 특수문자에 대해 매핑이 안됨. -> list[2]이런거?)
			
			//post:경로에 따로 인코딩할 필요 없어서 post 사용
			//부메랑4.http://localhost/app/getList & Form : list[0].name, Han / list[0].age,30 / list[1].name, Lee / list[2].name, Bark
			return "";
		}
		
		
		
		//requestParam -> 객체단위로 데이터를 받아야할 경우X. 방식 -> 1.매개변수별로 requestParam붙음 2.map, 배열, List형태로 값 받기 가능
		//배열과 리스트로 받을 때는 같은 이름을 가진 대량의 데이터 -> 동일한 타입으로 하나의 변수에 담을 수있음. 배열을 담아야 할 때 객체배열에는 requestParam 적합X. 배열을 다뤄야할 땐 command List아니면 json.
		
		@RequestMapping("getValues")
		//사용하고자하는 변수 개별하나하나 앞에 어노테이션 추가
		public String getParamValues(@RequestParam(required = false) String name, @RequestParam(defaultValue = "1") Integer age) {
			System.out.printf("============ %s\n", name);
			System.out.printf("============ %d\n", age);
			return "";
			
			//커맨드객체: 일부필드가 값이 존재하지 않더라도 문제가 안 됨(객체 내부의 필드값 없어도됨)
			//requestParam : 필수값임.(필드값이 기본 required -> 없으면 정상적으로 실행되지 않음)
			//requestParam이 붙는다 = 이 값은 무조건 있어야 한다. 필수로 요구하는 것도 requestParam의 역할(하나의 값을 가져오는 것 뿐만아니라)
			//필수인데 값이 없으면 이것으로 대체하겠ㅅ브니다: @RequestParam(defaultValue = "1")
			//부메랑5.http://localhost/app/getValues (GET방식)
			//필수값인데 못받을 경우 어떻게 처리할지 requestParam이 처리해 줄 수 있음
			
			//Map : 들어오는 모든 데이터 대응. Map이 가진 key값을 모른다
			//커맨드객체는 내가 생성. 필드에 대해서 내가 알고있고 이것에 대해서만 값을 달라고 할 수 있음
			//requestParam으로 Map사용하면 들어오는 데이터에서 내가 골라낼 수 없음
			//Object : List, map도 받을 수 있음
			//데이터가 어떻게 들어오는지 모를때
			//@RequestParam Map<String, Object> map -> 매개변수로 사용
			
			//객체배열 제외 기본 단일타입(String포함) 배열은 requestParam이 처리가능함
		}
		
		
		
		//PathVariable - 경로에 중괄호를 이용해서 이 위치에 있는 값은 어떤 변수에 담겠다 , 경로에 값이 붙어있음 -> 경로인지, 값요청인지 구분 힘듦. 데이터를 숨김. pathVariable은 경로를 이용해서 데이터를 보내는 것 임에도 데이터 요청인지, 그냥 경론지 모름 , 정보 보안 강화
		@RequestMapping("users/{empid}")
		public String getPathValues(@PathVariable("empid") String id) {	//항상 변수명이 key값으로 사용되지 않음
			//?못써서 queryString못씀
			System.out.printf("========== %s \n", id);
			return "";
			
			//부메랑6.http://localhost/app/users/Kang
		}
		
		
		
		//RequestBody(커맨드 객체다음으로 많이 쓰임)
		//json받는 컨트롤러?
		@RequestMapping("getJsonVal")
		//RequestBody -> 보내는 쪽에서 달라짐. 받는쪽에서는 커맨드객체와 비슷함?.
		//RequestBody는 body에 데이터가 필요해서 get은 안되고 post또는 put방식. 그리고 body를 사용하지 않고 json포맷을 사용해야 함. 보낼때 객체와 배열 구분, 그리고 키와 value 따옴표로 구분
		//보내는 값이 숫자여도됨. json은 text를 요구함
		public String getJsonValues(@RequestBody UserVO userVO) {
			System.out.printf("============ %s\n", userVO.getName());
			System.out.printf("============ %d\n", userVO.getAge());
			
			return "";
			
			
			//보내는 타입 json으로 변형?
			//json : 두가지형태(객체, 배열) requestBody는 객체또는 List앞에 붙음
			//부메랑7.http://localhost/app/getJsonVal
		}
		
		
		
		
		
}
