<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div><h3>게시글 상세보기</h3></div>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<td>${boardInfo.bno }</td>
			</tr>
			<tr>
				<th>작성일</th>
				<td><fmt:formatDate value="${boardInfo.regdate }" pattern="yyyy/MM/dd"/></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td>${boardInfo.writer }</td>
			</tr>
			
			<tr>
				<th>제목</th>
				<td>${boardInfo.title }</td>
			</tr>
			
			<tr>
				<th>내용</th>
				<!-- td는 끝없이 옆으로 늘어나서 -->
				<td><textarea rows="3"
							  cols="2"
							  style="width: 100px;"
							  readonly>
				${boardInfo.contents }
				</textarea>
				</td>
			</tr>
			<tr>
				<th>첨부이미지</th>
				<c:choose>
					<c:when test="${ not empty boardInfo.image }">
						<td><img src='<c:url value="/resources/${boardInfo.image }" />' style="width: 200px;"></td>
					</c:when>
					<c:otherwise>
						<td>파일없음</td>
					</c:otherwise>
				</c:choose>
			</tr>	
				<!--  img src='${pageContext.request.contextPath }/resources/${boardInfo.image }'/>-->
				<!-- c태그의 url은 value로 들어온 값에 대해서 자동으로 contextPath를 붙여줌. 경로를 생성해줌 -->
				<!--  	<img src='<c:url value="/resources/${boardInfo.image }"/>'>-->

	</table>
	<button type="button" onclick="location.href='boardUpdate?bno=${boardInfo.bno}'">수정</button>
	<button type="button" onclick="location.href='boardDelete?bno=${boardInfo.bno}'">삭제</button>
</body>
</html>