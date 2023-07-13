<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>부서 등록</title>
</head>
<body>
	<form action="deptInsert" method="post">
		<label> 부서이름 : <input type="text" name="departmentName" required></label>
		<label> 팀장번호 : <input type="text" name="managerId"></label>
		<label> 위치번호 : <input type="text" name="locationId" ></label>	
		<button type="submit">등록</button>
		<button type="button" onclick="location.href='deptList'">목록</button>
	</form>
</body>
</html>