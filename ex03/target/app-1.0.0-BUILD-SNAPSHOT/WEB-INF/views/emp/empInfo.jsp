<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<!-- 단건조회지만 수정, 삭제 같이 처리할거라서 form으로 함 -->
	<!-- 수정, 삭제를 form으로 처리 시 controller에서 데이터를 받을 때 뭘 쓸건지 결정해야 함 -->
	<!-- 단건조회만 할 때는 name속성이 필요 없음. name속성 -> 수정할 때 서버쪽에서 값을 받을 때 사용됨 -->
	<form action="empUpdate" method="post">
		<div>
			<label>id : <input type="number" name="employeeId" value="${empInfo.employeeId }" readonly></label>
			<!-- Model에 들어있는 데이터로 접근해야 해서 empInfo로 접근 -->
		</div>
		<div>
			<label>first_name : <input type="text" name="firstName" value="${empInfo.firstName }"></label>
		</div>
		<div>
			<label>last_name : <input type="text" name="lastName" value="${empInfo.lastName }"></label>
		</div>
		<div>
			<label>email : <input type="text" name="email" value="${empInfo.email }"></label>
		</div>
		<div>
			<label>job_id : <input type="text" name="jobId" value="${empInfo.jobId }"></label>
		</div>
		<div>
			<label>salary : <input type="number" name="salary" value="${empInfo.salary }"></label>
		</div>
		<button type="submit">수정</button>
		<!-- submit은 동기식으로 데이터를 요청해서 사실상 못씀. 수정은 controller에서 json으로 데이터를 주고받게 되어있음(비동기) -->
		<!-- submit을 제어해서 접근. -->
		<button type="button">취소</button>
	</form>
	<script>
		//1) form 태그의 submit 이벤트 stop
		$('form').on('submit', ajaxUpdateEmp);
		
		//2) form 태그 내의 정보 가져옴
		function serializeObject(){
			//jQuery가 가진 serialize 관련 메소드 2개
			let formData = $('form').serializeArray();	// 객체 배열 : 객체 하나 -> input 태그 하나
			//serializeArray : form태그 안의 input 태그 하나를 객체로 해서 객체 배열을 만들어냄(하나의 변수로 담아는 주는데 그대로 사용이 불가능하다)
			
			//객체 배열을 객체로 하는 작업필요
			let objectData = {};
			$.each(formData, function(idx, obj){
				objectData[obj.name] = obj.value; 
				//객체 배열 내부의 객체는 name(내부 객체가 input이라서 name, value)이 key값
			});
			
			return objectData;
		}
		//3) ajax를 통해 통신을 요청(이벤트 핸들러 사용 -> 자동으로 매개변수로 event객체를 받아옴)
		function ajaxUpdateEmp(e){
			e.preventDefault();
			//jQuery라서 return false를 시켜도됨. -> preventDefault와 stopPropagation 동시에 해줌. 근데 얜 submit이라서 전파될 애는 아님
			
			//통신에 대한 정보
			$.ajax({	//{ ->하나의 객체를 보내는 거
				url : 'empUpdate',
				type : 'post',
				contentType : 'application/json',
				data : JSON.stringify(serializeObject())		//클라에서 서버로 보내는 데이터를 객체 ->json으로 변환  	//JSON.parse - JSON을 객체로 바꿔줌
			})
			
			//반환결과 (통신에 대한 정보와 분리)
			//비동기통신은 항상 돌아오는 결과를 확인해야함
			//promise를 랩한 객체를 돌려줌
			.done( data => {
				if(data != null && data['결과'] == 'Success'){//key가 한글로오면 'data.결과' 불가능함. 자바스크립트 코드로는 인식이 불가능함. 필드가 한글. "사원번호", "결과"
					alert('사원번호 : ' + data['사원번호'] + '의 정보가 수정되었습니다.');
				} else {
					alert('해당 사원의 정보가 정상적으로 수정되지 않았습니다.');
				}
				
			})
			.fail( reject => console.log(reject));
		};
	</script>
</body>
</html>