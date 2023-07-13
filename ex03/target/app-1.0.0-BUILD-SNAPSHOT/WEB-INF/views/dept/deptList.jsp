<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 부서 조회</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<button type="button" onclick="location.href='deptInsert'">등록</button>
	<table>
		<thead>
			<tr>
				<th>department_id</th>
				<th>department_name</th>
				<th>manager_id</th>
				<th>location_id</th>
				<th>Delete</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${deptList }" var="dept">
				<tr onclick="location.href='deptInfo?departmentId=${dept.departmentId}'">
					<td>${dept.departmentId }</td>
					<td>${dept.departmentName }</td>
					<td>${dept.managerId }</td>
					<td>${dept.locationId }</td>
					<td><button type="button">Del</button></td>				
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<script>
		//redirect했을 때 
		let msg = `${message}`;	//따옴표 붙여야함
		if(msg != null && msg != '') alert(msg);
	
	
		$('tbody > tr button[type="button"]').on('click', ajaxDeptDelete);
		
		function ajaxDeptDelete(e){
			//let deptId = e.currentTarget.closest('tr').firstElementChild.textContent;
			let deptId = $(e.currentTarget).parent() -> td .siblings() -> 자신 제외 찾음 .eq(0) -> 0번째 인덱스의 . text(); -> 텍스트값
			//console.log(deptId); -> 확인용
			$.ajax({
				url : 'deptDelete',
				type : 'post',
				data : { id : deptId }
			//@RequestParam에 따로 name지정 안했으면 변수명
			})
			.done ( data => {
				if(data == 'Success'){
					$(e.currentTarget).parent().parent().remove();
				} else {
					alert('해당 정보는 삭제되지 않았습니다.');
				}
			})
			
			.fail( reject => console.log(reject);)
			
			return false;
		}
	</script>
</body>
</html>