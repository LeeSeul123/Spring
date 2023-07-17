<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 수정</title>
<!-- jQuery CDN : 공통적으로 사용하는 CDN은 layout으로 몰아 넣음 -->
</head>
<body>
	<form name="UpdateForm">
		<table>
		<!-- 커맨드 객체의 필드명을 name으로 사용해야 함. 기본적으로 통신은 다 text라서 타입으로는 구분 불가능하기때문에 필드명으로 key값 참고 -->
			<tr>
				<th>번호</th>
				<td><input type="number" name="bno" value="${boardInfo.bno }" readonly></td>
			</tr>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" value="${boardInfo.title }"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="writer" value="${boardInfo.writer }" readonly></td>
			</tr>
			<tr>
				<th>내용</th>
				<!-- textarea는 input처럼 value로 다이렉트로 값을 집어넣지 않음 -->
				<td><textarea rows="10" cols="97" name="contents">${boardInfo.contents }</textarea></td>
			</tr>
			<tr>
				<th>첨부이미지</th>
				<td><input type="text" name="image" value="${boardInfo.image }"></td>
			</tr>
			<tr>
				<th>수정일자</th>
				<!-- 값은 오는데 input type이 date로 고정되어있어서 원하는 포맷으로 설정해야함. jstl한테 이 포맷으로 해달라고 요청. 인식할 수 있는 값으로 넘겨줘야 함. 데이터는 넘겨오지만 출력이 안됨(input이 깨지진 않지만 없는 것처럼대응). 입력태그가 원하는 쪽으로 fmt사용해줘야함 -->
				<td><input type="date" name="updatedate" value='<fmt:formatDate value="${boardInfo.updatedate }" pattern="yyyy-MM-dd"/>'></td>
			</tr>
		</table>
		<button type="submit">수정</button>
		<!-- 수정 전 페이지가 단건조회라서 -->
		<button type="button" onclick="location.href='boardInfo?bno=${boardInfo.bno}'">취소</button>	
	</form>
	<script>
		function serializeObject(){
			//serialize는 비동기로 데이터를 보낼 떄 폼 태그 내부 데이터 한꺼번에 가져오는 함수가 필요해서 만든 것. form을 지칭하지 않으면 작동하지 않음(table, div안됨. 무조건 form태그). 오류는 안나지만 빈 객체가 돌아옴
			let formData = $('form').serializeArray();
			// [ { name : 'title', value : 'Hello'}, { name : 'writer', value : '여행자'}, ..] serialize는 배열은 배열인데, input태그 하나가 객체. key가 name인 건 없어서 우리가 필요한 데이터를 따로 끄집어내는 작업이 필요함
			
			let formObj = {};
			
			$.each(formData, function(idx, obj){
				formObj[obj.name] = obj.value;
			})
			
			return formObj;
		}
		
		$('form').on('submit', function(e){
			
			e.preventDefault();
			
			let objData = serializeObject();
			
			$.ajax({
				url : 'boardUpdate',
				type : 'post',
				data : objData	//ajax가 알아서 post, get 구분해서 데이터 넣어줌
					//서버쪽으로 json으로 보내라고 할 때 json으로 하는거고, default는 쿼리스트링임(get이든 post든). get이든 post든 데이터 형태는 같음
					//비동기면 무조건 json으로 보내라고 하는 법 없음
					
					//json은 선택사항임. 데이터 양이 많지 않다면 굳이 json을 써야 할 이유가 없음
			})
			.done(data => {
				//console.log(data);
				if(data.result){
					//map안에는 baordInfo도 있고 result도 있음
					let message = '수정되었습니다.\n사원번호 : ' + data.boardInfo.bno;
					alert(message);	//alert은 무조건 text형태로만 처리가능함
				} else {
					alert('수정되지 않았습니다.\n정보를 확인해주세요');
				}
			})
			.fail(reject => console.log(reject));
		})
		
	</script> 
</body>
</html>