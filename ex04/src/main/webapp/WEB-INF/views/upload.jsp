<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이미지 업로드</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
	<div>
		<input name="uploadFiles" type="file" multiple>
		<button class="uploadBtn">Upload</button>
	</div>
	
	<script>
		// 자바스크립트
		
		document.querySelector('.uploadBtn')
				.addEventListener('click', function(e){
					//현재 폼태그 존재X. 자바스크립트는 formData로 좀 편하게 처리 가능함
					let formData = new FormData();
					
					let inputTag = document.querySelector('input[name="uploadFiles"]');	//파일 들고 있는 input 태그 가져옴
					
					//파일 자체는 해당 태그가 가진 files라는 속성을 이용해서 가져옴
					let files = inputTag.files;
					//files는 fileList객체 가리키는 객체임
					
					for(let i=0; i < files.length; i++){
						//files를 통째로 넣는 건 아니고. files가 가진 파일들을 순차적으로 넣음
						console.log(files[i]);
						formData.append("uploadFiles", files[i]);
					}
					
					//비동기 통신 //경로와 메소드, 데이터 지정
					/*
					fetch('uploadAjax', {
						method : 'post',
						body : formData
					})	
					.then(response => response.json())
					//responsebody에 값이 붙었기 때문에 json으로 파싱
					.then(data => console.log(data))
					.catch(err => console.log(err));
					*/
					//jQuery.ajax(formData를 자바스크립트만 쓰는 거 아님. 번거롭긴 하지만 jquery Ajax도 가능함)
			        $.ajax({
			             url: 'uploadAjax',	
			             type: 'POST',
			             processData: false,	//기본값은 true, ajax 통신을 통해 데이터를 전송할 때, 기본적으로 key와 value값을 Query String으로 변환해서 보냅니다.
			             contentType: false,	// multipart/form-data타입을 사용하기위해 false 로 지정합니다.
			             //차이점 : boolean 타입. processData 는 쿼리스트링형태로 보내는 것을 자동으로 처리해주는 부분(객체로 넘기면 알아서 쿼리스트링으로 만들어주는게 processData). jqeury ajax는 get이든 post든 쿼리스트링이 디폴트임. 그 쿼리스트링 만드는 디폴트를 풀어버림.
			             //contentType사용하지 않겠다 지정하면 formData 자동으로 multipart가 됨
			             //jquery를 쓰면 2개나 더 써야함 -> fetch를 더 자주 쓴다
			             data: formData,               
			             success: function(result){
			             	for(let images of result){
			             		
			             		let path = '${pageContext.request.contextPath}/images/' + result;
			             		//DB에 저장할 땐 images까진 저장할 필요없음. 업로드 fileName을 기반으로 저장하면 되고(그냥 그 밑에 있는 파일명만 저장), 불러올 때는 매핑된 경로를 이용해서 파일 가져오면 됨
			             		let imgTag = $('<img/>').prop('src', path);
			             		$('div').append(imgTag);
			             		//DB저장 대신 태그가 추가되는지 확인
			             	}
			             },
			             error: function(reject){	
			                 console.log(reject);
			             }
			         }); 
				})
				
		
	</script>
</body>
</html>