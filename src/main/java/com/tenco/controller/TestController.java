package com.tenco.controller;

import java.io.IOException;
import java.util.Date;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDAOImpl;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDAO;
import com.tenco.model.UserDAOImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/test/*")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAO userDAO;
	private TodoDAO todoDAO;

	public TestController() {
		super();
	}

	@Override
	public void init() throws ServletException {
		userDAO = new UserDAOImpl();
		todoDAO = new TodoDAOImpl();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();
		switch (action) {
		case "/todoDAO":
			// http://localhost:8080/mvc/test/byId
//			userDAO.getUserById(1);
//			userDAO.getUserByUsername("홍길동");
//			List<UserDTO> list = userDAO.getAllUsers();
//			System.out.println(userDAO.deleteUser(9));
//			UserDTO userDTO = UserDTO.builder().password("bbcc").email("addd@naver.com").build();
//			int count = userDAO.updateUser(userDTO, 8);
//			System.out.println("count : " + count);

//			List<TodoDTO> list = todoDAO.getTodosById(1);
//			System.out.println(list.toString());
//			System.out.println(todoDTO.toString());
//			todoDAO.deleteTodo(1, 1);
			break;
		default:
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
