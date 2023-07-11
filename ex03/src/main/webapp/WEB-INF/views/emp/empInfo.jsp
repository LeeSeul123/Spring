<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원조회</title>
</head>
<body>
	<!-- 단건조회지만 수정, 삭제 같이 처리할거라서 form으로 함 -->
	<!-- 수정, 삭제를 form으로 처리 시 controller에서 데이터를 받을 때 뭘 쓸건지 결정해야 함 -->
	<!-- 단건조회만 할 때는 name속성이 필요 없음. name속성 -> 수정할 때 서버쪽에서 값을 받을 때 사용됨 -->
	<form>
		<div>
			<label>id : <input type="number" name="" value="${empInfo.employeeId }"></label>
			<!-- Model에 들어있는 데이터로 접근해야 해서 empInfo로 접근 -->
		</div>
		<div>
			<label>first_name : <input type="text" name="" value="${empInfo.firstName }"></label>
		</div>
		<div>
			<label>last_name : <input type="text" name="" value="${empInfo.lastName }"></label>
		</div>
		<div>
			<label>email : <input type="text" name="" value="${empInfo.email }"></label>
		</div>
		<div>
			<label>job_id : <input type="text" name="" value="${empInfo.jobId }"></label>
		</div>
		<div>
			<label>salary : <input type="number" name="" value="${empInfo.salary }"></label>
		</div>
		<button type="submit">수정</button>
		<button type="button">취소</button>
	</form>
</body>
</html>