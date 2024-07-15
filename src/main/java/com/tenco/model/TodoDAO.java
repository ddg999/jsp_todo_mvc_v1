package com.tenco.model;

import java.util.List;

public interface TodoDAO {
	// 저장 기능
	void addTodo(TodoDTO todoDTO, int principalId);

	// Todo Id 기준으로 찾기
	TodoDTO getTodoById(int id);

	// 사용자 아이디 기준으로 todoList
	List<TodoDTO> getTodosById(int userId);

	List<TodoDTO> getAllTodos();

	// 수정
	void updateTodo(TodoDTO todoDTO, int principalId);

	void deleteTodo(int id, int principalId);
}
