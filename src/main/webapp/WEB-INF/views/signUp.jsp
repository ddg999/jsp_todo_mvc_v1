<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 가입 페이지</title>
<link rel="stylesheet" type="text/css" href="../css/styles.css">
</head>
<body>
	<h2>회원가입</h2>
	<!-- 에러 메세지 출력 -->
	<c:if test="${not empty userList}">
		<p style="color:red">${erroMessage}</p>
	</c:if>
	<!-- 절대경로 주소 설계 -->
	<form action="/mvc/user/signUp" method="post">
		<label for="username">사용자 이름 : </label>
		<input type="text" id="username" name="username" value="야스오1">
		<label for="password">비밀번호 : </label>
		<input type="password" id="password" name="password" value="1234">
		<label for="email">이메일 : </label>
		<input type="text" id="email" name="email" value="a@nate.com">
		<button type="submit">회원가입</button>
	</form>
</body>
</html>