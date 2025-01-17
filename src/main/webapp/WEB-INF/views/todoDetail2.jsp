<%@page import="com.tenco.model.TodoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기 페이지</title>
</head>
<body>
	<h2>상세 보기</h2>
	
	<%
		TodoDTO todo = (TodoDTO) request.getAttribute("todo");
		if(todo != null) { %>
		<p>제목 : <%=todo.getTitle() %></p><br>
		<p>내용 : <%=todo.getDescription() %></p><br>
		<p>마감일 : <%=todo.getDueDate() %></p><br>
		<p>완료여부 : <%=todo.getCompleted().equals("1") ? "완료" : "미완료"%></p><br>
		<hr><br>
		<form action="update" method="post">
			<input type="hidden" name="id" value=<%=todo.getId()%>>
			<label>제목 : </label>
			<input type="text" id="title" name="title" value="<%=todo.getTitle() %>">
			<br>
			<label>내용 : </label>
			<input type="text" id="description" name="description" value="<%=todo.getDescription() %>">
			<br>
			<label>마감일 : </label>
			<input type="date" id="dueDate" name="dueDate" value="<%=todo.getDueDate() %>">
			<br>
			<label>완료여부 : </label>
			<input type="checkbox" id="completed" name="completed" <%=todo.getCompleted().equals("1") ? "checked" : ""%>>
			<br>
			<button type="submit">수정하기</button>
		</form>
	<%} else {
		out.print("<p>정보를 불러오는데 실패</p>");
	  }
	%>
	
</body>
</html>