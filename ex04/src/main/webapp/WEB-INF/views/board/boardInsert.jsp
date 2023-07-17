<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="insertForm" action="boardInsert" method="post">
		<div>
			<h3>게시글 정보</h3>
		</div>
		<table>
		<!-- 커맨드 객체의 필드명을 name으로 사용해야 함. 기본적으로 통신은 다 text라서 타입으로는 구분 불가능하기때문에 필드명으로 key값 참고 -->
			<tr>
				<th>제목</th>
				<td><input type="text" name="title"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea rows="10" cols="97" name="contents"></textarea></td>
			</tr>
			<tr>
				<th>첨부이미지</th>
				<td><input type="text" name="image"></td>
			</tr>
			<tr>
				<th>작성일자</th>
				<!-- text타입이여야 함 -->
				<td><input type="date" name="regdate"></td>
			</tr>
		</table>
		<button type="submit">등록</button>
		<button type="reset">취소</button>	
	</form> 
	<script>
		//비동기 말고도 form, submit을 이용해서 제어 가능함
	
		//required는 alert안띄워줌
		document.querySelector('form[name="insertForm"]')
				.addEventListener('submit', function(e){
					e.preventDefault;
					
					let title = document.getElementByName('title')[0];
					let writer = document.getElementByName('writer')[0];
					
					
					//개별 체크이므로 if else안됨
					if(title.value == ''){
						alert('제목이 입력되지 않았습니다.');
						title.focus();	//이벤트 강제 발생은 메소드 호출하는 방식으로 함
						return;
					}
					
					if(writer.value == ''){
						alert('작성자가 입력되지 않았습니다.');
						writer.focus();
						return;
					}
					
					//submit은 focus처럼 임의로 돌릴 수 있음
					//form태그는 name속성 가지고 있으면 중복되지 않는다는 가정 하에 select안쓰고 이름으로 바로 호출할 수 있음
					insertForm.submit();
				})
		
		
	
	</script>
</body>
</html>