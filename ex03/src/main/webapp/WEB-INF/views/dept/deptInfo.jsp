<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<form>
		<div>
			<label> 부서번호 : <input type="number" name="departmentId" value="${dept.departmentId }" readonly></label><br>
		</div>
		<div>
			<label> 부서이름 : <input type="text" name="departmentName" value="${dept.departmentName }"></label><br>
		</div>
		<div>
			<label> 팀장번호 : <input type="number" name="managerId" value="${dept.managerId }"></label><br>
		</div>
		<div>
			<label> 위치번호 : <input type="number" name="locationId" value="${dept.locationId }"></label>	<br>
		</div>
		<button type="submit">수정</button>
		<button type="button" onclick="location.href='deptList'">목록</button>
	</form>
	<script>
		//자바스크립트로 form이 모아놓은 데이터 가져오기(jQuery의 serializeArray())
		/* let inputList = document.querySelectorAll('form input');
		let formObj = {};
		inputList.forEach(tag => {
			formObj[tag.name] = tag.value;
		});
		console.log(formObj); */
		//객체면서 input이 가지는 값들 다 가짐 -> form태그의 input 태그 값 가져와서 name, value 객체 하나 만들어서 보내면 됨
		
	
		//redirect했을 때 
		let msg = `${message}`;	//따옴표 붙여야함
		if(msg != null && msg != '') alert(msg);
	
		//submit 막음
		$('form').on('submit', ajaxDeptUpdate);
		
		//원하는 형태로 변형시킴
		function serializeObject(){
			//jQuery메소드사용(자바스크립트의 querySelectorAll로도 가능함. 알고리즘은 같음)
			let formData = $('form').serializeArray();
			
			let formObject = {};
			
			$.ajax(formData, function(idx, obj){
				formObj[obj.name] = obj.value;
			});
			
			return formObject;
		}
		
		function ajaxDeptUpdate(e){
			e.preventDefault();
			
			let obj = serializeObject();	//form태그의 모든 데이터 가져옴
			
			//update작업
			$.ajax({
				url : 'deptUpdate',
				type : 'post',
				contentType : 'application/json',		//json으로 데이터 보낼 때 명시. controller에서 매개변수가 @RequestBody라서
				data : JSON.stringify(obj)				//json 포맷으로 변환시킴. json이면 같이 움직여야함(contentType과 data가)
			})
			/*.done( result => {
				function test(result){
					console.log(result);
				}
				test(result);
			})*/
			.done( data => {
				//console.log(data);
				if(data != null && data['결과'] == 'Success'){
					let msg = '수정되었습니다.\n부서번호 : ' + data['부서번호'];
					alert(msg);
				} else {
					alert('수정되지 않았습니다. 정보를 확인해주세요.');
				}
			})
			
			//화살표 함수 + 익명함수 -> 변수를 이용해서 정의만 한 거임
			/*.fail( reject => function(reject){
				console.log(reject);
			})*/
			.fail(reject => console.log(reject));
		};
		
		
	</script>
</body>
</html>