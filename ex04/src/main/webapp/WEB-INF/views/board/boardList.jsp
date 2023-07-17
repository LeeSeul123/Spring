<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 숫자, 날짜 둘다 정해진 포맷에 맞춰서 출력가능. jsp에서 포맷을 결정할 경우 fmt태그 사용 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 목록</title>
<style>
*{
color:red;
}
body{
background:url()
}
</style>
</head>
<body>
	<table border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${boardList }" var="board">
			<!-- items에는 컨트롤러에서 보내준 데이터가 들어감 -->
				<tr onclick="location.href='boardInfo?bno=${board.bno}'">
				<!-- boardList가 가지고 있는 객체는 boardVO, boardVO가 가진 필드명을 이용해서 뿌려줘야함 -->
					<td>${board.bno }</td>
					<td>${board.title }</td>
					<td>${board.writer }</td>
					<!-- util.Date타입은 원하는 대로 못보내줌 -->
					<!-- value = 원데이터 pattern = 출력하고자하는 데이터. mm은 시간의 분, MM은 날짜의 월-->
					<td><fmt:formatDate value="${board.regdate }" pattern="yyyy년MM월dd일"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<h1>안녕하세용 ㅎㅎ 주현이에용 ㅎㅎ</h1>
</body>
</html>