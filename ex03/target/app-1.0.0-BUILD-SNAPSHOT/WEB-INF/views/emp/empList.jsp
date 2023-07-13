<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- c태그 포함 총5개 -> jstl -->
<!-- 원본 데이터 손대지 않고 보여지는 데만 제어 -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style type="text/css">
	table, th, td {
		border : 1px solid black;
	}
	
</style>
</head>
<body>
	<button type="button" onclick="location.href='empInsert'">등록</button>
	<!-- empInsert는 get방식으로 요청하면 페이지를 보여줌 -->
	<table>
		<thead>
			<tr>
				<th>employee_id</th>
				<th>first_name</th>
				<th>last_name</th>
				<th>email</th>
				<th>hire_date</th>
				<th>job_id</th>
				<th>salary</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<!-- tbody는 넘어오는 데이터에 따라 동적으로 생성 c태그의 foreach사용해서 반복되는 tr만듦 -->
			<!-- forEach의 필수속성 : items(컨트롤러가 보내온 데이터를 담음. EL을 이용해서 MODEL에 담긴 empList를 선언), var(하나씩 끄집어냈을 때 사용할 임시 변수)-->
			<c:forEach items="${empList }" var="emp">
				<tr onclick="location.href='empInfo?employeeId=${emp.employeeId}'">
				<!-- 경로를 이동시키는 거라서 event객체 쓸 일도 없어서 on속성을 사용함. on속성은 원래 안쓰는 게 좋음 -->
				<!-- get방식으로 값을 보냄. 키값은 필드의 이름과 같도록 구성 -->
					<td>${emp.employeeId }</td>
					<td>${emp.firstName}</td>
					<td>${emp.lastName}</td>
					<td>${emp.email}</td>
					<td>
					<fmt:formatDate value="${emp.hireDate }" pattern="yyyy년MM월dd일"/>
					<!-- 기본 형태가 우리나라랑 안맞아서 fmt로 바꾸는 거. pattern은 그냥 보여주고 싶은 형태.  -->
					<!-- java.util.Date의 toString형태로 출력됨(@DateTimeFormat은 입력format만 바뀜) -->
					</td>
					<td>${emp.jobId}</td>
					<td>
						<fmt:formatNumber value="${emp.salary }" pattern="$#,###"/>
						<!-- 원본 데이터 수정하지 않고 보여지는 형식 출력할 때 쓰는게 fmt -->
					</td>
					<td><button type="button">삭제</button></td>
				</tr>
			</c:forEach>
		</tbody>
		<!-- input type="date"가 출력시킬 땐 yyyy-MM-dd형태 -> 입력할 때도 이 형식으로 집어넣어야 함. fmt는 단순히 출력할 때 뿐만아니라 태그에서 인식하기전에 자바에서 데이터 뿌려줌. td와같이 데이터 뿌려줄때만 사용X 태그의 value속성에서도 사용할 수 있음. fmt는 날짜뿐만아니라 number에도 사용가능하다 -->
		<!-- html주석안에 jstl이 들어가면 오류남 예) fmt:     ㅇㅇㅇㅇㅇ                     formatNumber jsp는 자바코드. jsp는 자바가 먼저 컴파일 되고나서 html로 돌리고나서 주석봄? jstl, el태그를 주석안에 쓰면 안됨.주석안에 태그를 그대로 표기X. servlet으로 컴파일된 후(자바는 html주석을 모름) response바디에 담아서 브라우저에 보냄(html,css,javascript를 읽음). 역이용하기도함-->
	</table>
	<script>
		//함수를 호출할 떄 el태그를 이용해서 값 넘겨줌. 따옴표 안에 EL태그 사용해야함. 자바스크립트가 인식하기 전에 자바가 컴파일해서 괜찮음(원래는 자바스크립트에서 ''안에는 문자로 인식)
		printMessage(`${result}`);	//insert의 redirect, 따옴표는 줄바꿈 인식못함(자바스크립트 깨짐). 그래서 백틱씀
	
		//원래는 잘 사용X. 간단한 데이터 주고받을 때 javascript에서 el태그 사용. 원래는 다른 문법끼리 침범하면 안됨
		function printMessage(msg){
			if(msg == null || msg == '') return;
			alert(msg);
		}
		
		//버튼에 삭제기능 추가
		//1. 등록버튼과 삭제버튼이있어서 구별(jQuery는 text값으로도 검색 가능). 이건 jquery만 가능함(자바스크립트는 구조적으로 접근해야함)
		$('button:contains("삭제")').on('click', ajaxDeleteEmp);	//ajaxDeleteEmp라는 이벤트 핸들러를 이용
		//버튼의 클릭이벤트는 bubbling 주의.없는 데이터에대해 단건조회할 수도있음
		
		function ajaxDeleteEmp(e){
			//event객체만 넘어왔기 때문에 id를 찾아와야함
			//event 객체 : 필드 2개(target은 이벤트가 발생한 고정태그. handler를 동작시키는 애가 필요할 때는 currentTarget을 이용)
			let empId = e.currentTarget.closest('tr').firstElementChild.textContent;		//currentTarget = 버튼. 버튼은 td안에 있음
			
			$.ajax({
				url : 'empDelete',
				type : 'post',
				data : { id : empId }		//모든 통신은 기본적으로 객체타입(key와 value규칙 지키기 위해서)
				//@RequestParam -> 매개변수명이 key가 됨	or @RequestParam("id") -> key가 "id"임. 디폴트로 지정되는 속성이 name임. 만약 required등 다른 속성도 쓰면 name이라고 지정해야함. required, defaultValue속성도 있음
				//@RequestParam은 데이터를 따로 json으로 보낼건아니라서 contentType은 필요없음
				//{ id : <- 컨트롤러의 매개변수 기반으로 필드명을 정함
			})
			.done( data => {
				//console.log(data);	//Success돌아오는 지 확인(후속작업 필요)
				if(data == 'Success'){
					//reload안하고 페이지에서 해당 건 삭제(jQuery사용)
					let btn = e.currentTarget;
					$(btn).closest('tr').remove();
					//closest는 javascript도 있음.버튼을 감싼 tr을 제거해야함
				} else {
					alert('삭제되지 않았습니다.');
					
				}
			})
			.fail( reject => {
				console.log(reject);
			})
			
			//e.stopPropagation();		//흐름은 흐름대로 하고, 전파는 막는 메소드
			return false;
		}
	</script>
</body>
</html>