<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="com.tenco.model.TodoDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>할 일 목록</title>
<style type="text/css">
/* Reset default margin and padding */
* {
    margin: 0;
    padding: 0;
    box-sizing: border-box;
}

/* Body styling */
body {
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background-color: #f8f9fa;
    color: #333;
    line-height: 1.6;
    padding: 20px;
}

/* Container for page content */
.container {
    max-width: 900px;
    margin: 0 auto;
}

/* Header styling */
header {
    text-align: center;
    margin-bottom: 20px;
}

header h1 {
    font-size: 2.5rem;
    margin-bottom: 10px;
}

/* Error message styling */
.message {
    color: #dc3545;
    margin-bottom: 20px;
}

/* Todo list section */
.todo-list {
    margin-top: 20px;
    background-color: #fff;
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.todo-list h2 {
    background-color: #007bff;
    color: #fff;
    padding: 15px;
    margin: 0;
}

/* Add todo link */
.add-todo {
    text-align: center;
    padding: 10px;
    background-color: #007bff;
    color: #fff;
    text-decoration: none;
    display: inline-block;
    margin-top: 10px;
    border-radius: 4px;
    transition: background-color 0.3s;
}

.add-todo:hover {
    background-color: #0056b3;
}

/* Table styling */
table {
    width: 100%;
    border-collapse: collapse;
}

table th,
table td {
    padding: 12px;
    text-align: center;
}

table th {
    background-color: #007bff;
    color: #fff;
}

table td {
    border-bottom: 1px solid #ddd;
}

/* Action buttons */
.action-buttons {
    display: flex;
    justify-content: center;
    gap: 10px;
}

.action-buttons button {
    padding: 8px 16px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
    transition: background-color 0.3s;
}

.action-buttons button.delete {
    background-color: #dc3545;
    color: #fff;
}

.action-buttons button.delete:hover {
    background-color: #c82333;
}

.action-buttons button.detail {
    background-color: #28a745;
    color: #fff;
}

.action-buttons button.detail:hover {
    background-color: #218838;
}

/* Responsive adjustments */
@media screen and (max-width: 600px) {
    header h1 {
        font-size: 2rem;
    }

    .container {
        padding: 10px;
    }

    .action-buttons {
        flex-direction: column;
        align-items: center;
    }

    .action-buttons button {
        width: 100%;
        margin-top: 10px;
    }
}
</style>
<!--<link rel="stylesheet" type="text/css" href="../css/styles.css">-->
</head>
<body>
	<%
		String message = (String) request.getParameter("message");
		if (message != null) {
	%>
		<p style="color:red"><%=message %></p>
	<% } %>
	<%
	ArrayList<TodoDTO> todoList = (ArrayList<TodoDTO>) request.getAttribute("todoList");

	if (todoList != null && !todoList.isEmpty()) {
	%>
	<h2>할 일 목록</h2>
	<a href="todoForm">새 할일 추가</a>
	<table border="1">
		<tr>
			<th>제목</th>
			<th>설명</th>
			<th>마감일</th>
			<th>완료여부</th>
			<th>(액션-버튼)</th>
		</tr>
		<%
		for (TodoDTO todo : todoList) {
		%>
		<tr>
			<td><%=todo.getTitle()%></td>
			<td><%=todo.getDescription()%></td>
			<td><%=todo.getDueDate()%></td>
			<td><%=todo.getCompleted().equals("1") ? "완료" : "미완료"%></td>
			<td><a href="detail?id=<%=todo.getId()%>">상세보기</a>
				<form action="delete" method="get">
					<input type="hidden" name="id" value="<%=todo.getId()%>">
					<input type="hidden" name="userId" value="<%=todo.getUserId()%>">
					<button type="submit">삭제</button>
				</form>
			</td>
		</tr>
		<%
		}
		%>
	</table>
	<%
	} else {
	%>
	<hr>
	<p>등록된 할 일이 없습니다.</p>
	<a href="todoForm">새 할일 추가</a>
	<%
	}
	%>
</body>
</html>