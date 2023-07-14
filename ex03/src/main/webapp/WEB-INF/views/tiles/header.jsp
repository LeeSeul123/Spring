<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<link rel="canonical" href="https://getbootstrap.com/docs/5.2/examples/headers/">
<!-- 브라우저는 서버 내 물리적위치 모름. 브라우저는 서버에 요청하는 거. 어떤 경로에 파일이 있다고하는데(url기반). 루트(/) -> webapp. 물리적위치가 아니라 네트워크 상의 위치 매핑.@RequestMapping -> 네트워크에 걔가 존재한다. 브라우저가 읽을 수 있는 공간은 네트워크에 존재함 -->
<!-- pc 내의 경로, 서버 기준으로 파일찾으려면 물리적 위치는 모름. 해당 pc내부 구조 모름. 브라우저에서 header.css하면 같은 레벨에 css파일 있다고 생각함. 루트이상은 못감 -->
<!--  link href="app/resources/headers.css" rel="stylesheet"  404뜸. 물리적위치는 visualStudio 라이브서버에서만 가능함. WAS는 자바와는 다른 부분임. 자바가 컴파일해서 들어가다보니 경로가 다름. link, script html에서 경로생각할 때 더이상 물리적위치가아님. webapp밑에 매핑되어있는 경로 어떻게 되어있는지 기억. 절대경로를 하면 항상 contextPath(jsp에서 지원)를 가지고 올 수 있도록(이름을 바꾸면 자동으로 바뀔수있도록)-->
<link href="${pageContext.request.contextPath}/resources/headers.css" rel="stylesheet">
<!-- link href="resources/headers.css"도 되긴한데 app/emp/List를 방지함-->

<!-- 실행되는 순간 가지는 contextPath를 가지고옴 -> 이게 페이지(브라우저 입장에서 절대경로임) -->
<!--  local/app/empInsert에서 link href="headers.css" rel="stylesheet"하면 local/app/headers.css찾음 -->
<!-- 원래 경로 : http://localhost/app/dept/deptList -->
<!-- 상대경로 : http://localhost/app/dept/headers.css 파일만 덜렁있으면 같은경로-->
<!-- 원래 경로 : http://localhost/app/empList -->
<!-- 상대 경로 : http://localhost/app/headers.css 서버는 내부에 있는 파일에 대해서 경로로 찾아감(물리적 구조,위치와 상관이 없다) -->
<div class="container">
    <header class="d-flex justify-content-center py-3">
      <ul class="nav nav-pills">
        <li class="nav-item"><a href="${pageContext.request.contextPath}/" class="nav-link active" aria-current="page">Home</a></li>
        <!-- /는 그냥 명시해주는거. contextPath찾아가면 원래 /임. HomeController에 매핑시켜놓은 거 명시할 겸 -->
        <li class="nav-item"><a href="${pageContext.request.contextPath}/empList" class="nav-link">사원관리</a></li>
        <li class="nav-item"><a href="${pageContext.request.contextPath}/deptList" class="nav-link">부서관리</a></li>
      </ul>
    </header>
</div>