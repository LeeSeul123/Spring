<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 사원 조회</title>
</head>
<body>
	<script>
		document.addEventListener('DOMContentLoaded', function(e){
			//태그를 건드는 경우 태그 생성 후에 이벤트가 걸려야 함 -> 그래서 Script태그가 맨 아래 들어가는 경우가 많음
			//간혹 위에 script태그 걸 때는 읽히기 전이니까 DOMContentLoaded로 돔 완성 후 이벤트 걸라고 설정(딜레이 시킴)할 수 있음
			//모든 자바스크립트X 태그에 이벤트를 걸 때 사용하는거임. 함수를 정의할 때는 여기 적으면 안됨
			//함수는 바깥에서 정의해야 함(아무리 이벤트 내용에서 해당 함수를 쓰더라도 바깥임)
			//자바스크립트는 scope가 함수레벨임. 그래서 함수정의할 땐 바깥에 선언 후 이벤트 등록할 때 호출. 내부에 선언되어있는데 바깥에서 호출하면 not define뜸
			document.getElementById('cntPerPage')
					.addEventListener('change', changeHandler);
		});
		
		function changeHandler(e){
			//let selected = document.getElementById('cntPerPage').value; 도 가능
			let selected = e.currentTarget.value;
			location.href = 'empList?nowPage=${paging.nowPage}&cntPerPage=' + selected;
			//페이지 다시 호출하는 거라서 비동기 통신 사용하지 않음
		}
	</script>
	<div>
		<div>
			<!-- 한페이지에 보고자 하는 데이터 개수 조절 -->
			<!-- c태그로 select박스 초기값 설정하기. html보다 c태그가 먼저 컴파일되기 때문에 C태그가 컴파일 되고나서 브라우저가 읽어들임. 초기값을 설정하는 경우에 C태그를 내부에 혼합해서 쓰는 경우가 있다. 자바에서 먼저 해석하는 걸 이용하는 것. jsp뿐만 아니라 서버템플릿 엔진 전부 적용됨 -->
			<select id="cntPerPage">
				<option value="5" <c:if test="${paging.cntPerPage == 5} ">selected</c:if>>5줄 보기</option>	
				<option value="10" <c:if test="${paging.cntPerPage == 10} ">selected</c:if>>10줄 보기</option>
				<option value="15" <c:if test="${paging.cntPerPage == 15} ">selected</c:if>>15줄 보기</option>
				<option value="20" <c:if test="${paging.cntPerPage == 20} ">selected</c:if>>20줄 보기</option>
			</select>
			<!-- 선택된 값은 selected가 처리함. -->
		</div>
		<table border="1">
			<thead>
				<tr>
					<th>employee_id</th>
					<th>first_name</th>
					<th>last_name</th>
					<th>email</th>
					<th>job_id</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${empList }" var="empInfo">
					<tr>
						<td>${empInfo.employeeId }</td>
						<td>${empInfo.firstName }</td>
						<td>${empInfo.lastName }</td>
						<td>${empInfo.email }</td>
						<td>${empInfo.jobId }</td>
					</tr>
				</c:forEach>
			</tbody>	
		</table>
		<div style="text-align:center;">
			<!-- 페이징 부분 추가 -->
			<!-- 1.이전(forEach에 들어가지 않는 이유 : 독립적으로 확인해야 해서. 항상 태그가 생성되지 않고 조건을 만족해야 완성됨 -->
			<c:if test="${paging.startPage != 1 }">
				<a href="empList?nowPage=${paging.startPage - 1 }&cntPerPage=${paging.cntPerPage}">&lt;</a>
				<!-- &lt;는 꺾쇄 -->
			</c:if>
			<!-- 2.View 안에 보여지는 페이지들 -->
			<!-- PagingVO기반으로 forEach돌리면 됨. forEach는 배열상대하는 게 아니라 연속되는 반복문임. 그래서 items 안쓰고 특정 값부터 끝나는 값 -->
			<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<!-- p가 start ~ end까지의 값을 가지게 됨 -->
				<!-- choose = 현재 페이지는 link 걸 필요 없음. 현재 페이지 제외 페이지만 링크 걺 -->
				<c:choose>
					<c:when test="${p eq paging.nowPage }">
						<b>${p }</b>
					</c:when>
					<c:otherwise>
						<!-- empList를 다시 호출 -->
						<a href="empList?nowPage=${p }&cntPerPage=${paging.cntPerPage }">${p }</a>
					</c:otherwise>
				</c:choose>
			</c:forEach>
			<!-- 3.다음 -->
			<c:if test="${paging.endPage != paging.lastPage }">
				<a href="empList?nowPage=${paging.endPage + 1 }&cntPerPage=${paging.cntPerPage}">&gt;</a>
				<!-- &lt;는 꺾쇄 -->
			</c:if>
		</div>
	</div>
</body>
</html>