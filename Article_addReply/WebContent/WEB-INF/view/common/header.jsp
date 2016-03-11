<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/resource/css/header.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/login.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/list.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/detail.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/join.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/logout.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/write.css" />
<script type="text/javascript" src="/resource/js/jquery-1.12.1.js"></script>

<title>회원제 게시판</title>
</head>
<body>
<!-- html이 제일 먼저 시작하는 페이지 -->
<div id="wrapper">

	<div id="header">
		<a href="/">
			<img src="/resource/img/icon.png" />
		</a> 
		<div>안녕하세요. 회원제 게시판입니다. <br/>
		Welcome to leina's board! <br/>
		Visit kiyeonlee.com!</div>	
	</div>
	
	<div id="welcome">
		<c:if test="${ not empty sessionScope._MEMBER_.memberId }" >
		${sessionScope._MEMBER_.memberId} 님 안녕하세요! 
		</c:if>
	</div>
	
	<div class="clear"></div>
