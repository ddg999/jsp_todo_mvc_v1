<%@page import="com.tenco.model.TodoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기 페이지</title>
</head>
<body>
	<h2>상세 보기</h2>
	<c:choose>
	
	<c:when test="${not empty todo}">
		<p>제목 : ${todo.title}</p><br>
		<p>내용 : ${todo.description}</p><br>
		<p>마감일 : ${todo.dueDate}</p><br>
		<p>완료여부 : ${todo.completed eq '1' ? '완료' : '미완료'}</p><br>
	</c:when>
	<c:otherwise>
		<p>정보를 불러오는데 실패</p>
	</c:otherwise>
	</c:choose>
		<hr><br>
		<form action="update" method="post">
			<input type="hidden" name="id" value="${todo.id}">
			<label>제목 : </label>
			<input type="text" id="title" name="title" value="${todo.title}">
			<br>
			<label>내용 : </label>
			<input type="text" id="description" name="description" value="${todo.description}">
			<br>
			<label>마감일 : </label>
			<input type="date" id="dueDate" name="dueDate" value="${todo.dueDate}">
			<br>
			<label>완료여부 : </label>
			<input type="checkbox" id="completed" name="completed" ${todo.completed eq '1' ? 'checked' : ''}>
			<br>
			<button type="submit">수정하기</button>
		</form>
</body>
</html>