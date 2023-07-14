<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ERP</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
<!-- JavaScript Bundle with Popper -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
</head>
<body>
	<!-- header, main, footer는 구간을 잡는거고 실제로 파일을 넣는 건 tiles:insertAttribute사용 -->
	<header>
		<tiles:insertAttribute name="header"/>
		<!-- ==어떤 애를 이쪽에 집어넣겠다 -> tiles.xml로 설정함 -->
	</header>
	<main>
		<tiles:insertAttribute name="content"/>
	</main>
	<footer>
		<tiles:insertAttribute name="footer"/>
	</footer>
</body>
</html>