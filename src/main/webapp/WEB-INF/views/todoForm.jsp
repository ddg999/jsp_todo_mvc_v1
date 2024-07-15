<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>새 할 일 추가</title>
<style type="text/css">
body {
    font-family: Arial, sans-serif;
    background-color: #f0f0f0;
    padding: 20px;
}

form {
    max-width: 600px;
    margin: 0 auto;
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

label {
    font-weight: bold;
}

input[type="text"],
input[type="date"],
textarea {
    width: 100%;
    padding: 8px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 14px;
}

textarea {
    resize: vertical; /* Allow vertical resizing */
}

button {
    background-color: #4caf50;
    color: white;
    padding: 10px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-size: 14px;
}

button:hover {
    background-color: #45a049;
}

/* Flexbox for checkbox alignment */
.checkbox-container {
    display: flex;
    align-items: center;
}

.checkbox-container label {
    margin-left: 5px;
}

/* Link styles */
a {
    display: block;
    margin-top: 10px;
    text-decoration: none;
    color: #007bff;
}

a:hover {
    text-decoration: underline;
}
</style>
</head>
<body>
	<h1>ToDo Page</h1>
	<%-- http://localhost:8080/mvc/todo/add --%>
	<form action="add" method="post">
		<label for="title">제목: </label>
		<input type="text" id="title" name="title" value="코딩연습 무한반복"><br><br>
		
		<label for="description">설명: </label>
		<textarea rows="20" cols="30" id="description" name="description">
			그래야 성공하고 높은 연봉은 기본 ... 아니면 워라벨
		</textarea><br><br>
		
		<label for="dueDate">마감기한: </label>
		<input type="date" id="dueDate" name="dueDate" value="2024-07-11"><br><br>
		
		<label for="completed">완료 여부: </label>
		<input type="checkbox" id="completed" name="completed"><br><br>
		
		<button type="submit">추가</button><br><br>
	</form>
	<a href="list">목록으로 돌아가기</a>
</body>
</html>