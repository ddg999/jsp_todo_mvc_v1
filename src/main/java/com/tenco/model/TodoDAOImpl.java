package com.tenco.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jakarta.security.auth.message.callback.PrivateKeyCallback.Request;
import jakarta.servlet.http.HttpSession;

public class TodoDAOImpl implements TodoDAO {

	private DataSource dataSource;

	public TodoDAOImpl() {
		try {
			InitialContext ctx = new InitialContext();
			dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/MyDB");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void addTodo(TodoDTO todoDTO, int principalId) {
		String sql = " INSERT INTO todos(user_id, title, description, due_date, completed) VALUES (?, ?, ?, ?, ?) ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, principalId);
				pstmt.setString(2, todoDTO.getTitle());
				pstmt.setString(3, todoDTO.getDescription());
				pstmt.setString(4, todoDTO.getDueDate());
				pstmt.setBoolean(5, Boolean.parseBoolean(todoDTO.getCompleted()));
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public TodoDTO getTodoById(int id) {
		String sql = " SELECT * FROM todos WHERE id = ? ";
		TodoDTO todoDTO = null;
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				todoDTO = new TodoDTO();
				todoDTO.setId(rs.getInt("id"));
				todoDTO.setUserId(rs.getInt("user_id"));
				todoDTO.setTitle(rs.getString("title"));
				todoDTO.setDescription(rs.getString("description"));
				todoDTO.setDueDate(rs.getString("due_date"));
				todoDTO.setCompleted(rs.getString("completed"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return todoDTO;
	}

	@Override
	public ArrayList<TodoDTO> getTodosById(int userId) {
		String sql = " SELECT * FROM todos WHERE user_id = ? ";
		ArrayList<TodoDTO> list = new ArrayList<TodoDTO>();
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, userId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				TodoDTO todoDTO = new TodoDTO();
				todoDTO.setId(rs.getInt("id"));
				todoDTO.setUserId(rs.getInt("user_id"));
				todoDTO.setTitle(rs.getString("title"));
				todoDTO.setDescription(rs.getString("description"));
				todoDTO.setDueDate(rs.getString("due_date"));
				todoDTO.setCompleted(rs.getString("completed"));
				list.add(todoDTO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArrayList<TodoDTO> getAllTodos() {
		String sql = " SELECT * FROM todos ";
		ArrayList<TodoDTO> list = new ArrayList<TodoDTO>();
		try (Connection conn = dataSource.getConnection()) {
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					TodoDTO todoDTO = new TodoDTO();
					todoDTO.setId(rs.getInt("id"));
					todoDTO.setUserId(rs.getInt("user_id"));
					todoDTO.setTitle(rs.getString("title"));
					todoDTO.setDescription(rs.getString("description"));
					todoDTO.setDueDate(rs.getString("due_date"));
					todoDTO.setCompleted(rs.getString("completed"));
					list.add(todoDTO);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void updateTodo(TodoDTO todoDTO, int principalId) {
		String sql = " UPDATE todos SET title = ?, description = ?, due_date = ?, completed = ? WHERE id = ? ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setString(1, todoDTO.getTitle());
				pstmt.setString(2, todoDTO.getDescription());
				pstmt.setString(3, todoDTO.getDueDate());
				pstmt.setBoolean(4, Boolean.parseBoolean(todoDTO.getCompleted()));
				pstmt.setInt(5, todoDTO.getId());
				pstmt.executeUpdate();
				conn.commit();
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 	삭제 기능
	 * 	id - Todos PK
	 *  principalId - session id
	 */
	@Override
	public void deleteTodo(int id, int principalId) {
		String sql = " DELETE FROM todos WHERE id = ? ";
		try (Connection conn = dataSource.getConnection()) {
			conn.setAutoCommit(false);
			try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
				pstmt.setInt(1, id);
				pstmt.executeUpdate();
				conn.commit();
				System.out.println("삭제완료");
			} catch (Exception e) {
				conn.rollback();
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
