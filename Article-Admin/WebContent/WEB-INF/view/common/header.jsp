<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/header.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/login.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/list.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/detail.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/join.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/logout.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/write.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/memberList.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/memberDetail.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/footer.css"/>" />
<link rel="stylesheet" type="text/css" href="<c:url value="/resource/css/editMemberInfo.css"/>" />
<script type="text/javascript" src="<c:url value="/resource/js/jquery-1.12.1.js"/>"></script>

<title>회원제 게시판: 관리자</title>
</head>
<body>
<!-- html이 제일 먼저 시작하는 페이지 -->
<div id="bodyWrapper">
<div id="headerwrapper">

	<div id="header">
		<a href= "<c:url value="/" />" >
			<img src="<c:url value="/resource/img/icon.png"/>" id="headerImg"/>
		</a> 
		<div> 
			<img src="<c:url value="/resource/img/title.png"/>" id="headerTitle"/> <br/>
		</div>	
	</div> <!-- header -->
	<div class="clear"></div>
	
</div> <!-- headerwrapper -->
<jsp:include page="/WEB-INF/view/common/topMenu.jsp"></jsp:include>
