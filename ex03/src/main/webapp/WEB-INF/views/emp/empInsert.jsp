<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원등록</title>
</head>
<body>
	<form action="empInsert" method="post" >
	<!-- form은 데이터 보낼 때 input태그의 내부의 name속성(key값 -> 커맨드 객체의 필드값 이용), value속성 제외 무시함. -->
	<!-- method는 명시 안하면 get -->
		<div>
		<!-- form 태그의 name속성 : server에서 key값 -->
		<!-- Spring에서는 getParameter해서 이름 기반으로 찾아오지 않고 자동으로 가져와짐 -->
			<label>first_name : <input type="text" name="firstName"></label>
		</div>
		<div>
			<label>last_name : <input type="text" name="lastName"></label>
		</div>
		<div>
			<label>email : <input type="email" name="email"></label>
		</div>
		<div>
			<label>job_id : <input type="text" name="jobId"></label>
		</div>
		<div>
			<label>salary : <input type="number" name="salary"></label>
		</div>
		<button type="submit">등록</button>
		<button type="button">목록</button>
	</form>
</body>
</html>