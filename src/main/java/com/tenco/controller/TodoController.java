package com.tenco.controller;

import java.io.IOException;
import java.io.PrintWriter;

import com.tenco.model.TodoDAO;
import com.tenco.model.TodoDAOImpl;
import com.tenco.model.TodoDTO;
import com.tenco.model.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/todo/*")
public class TodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoDAO todoDAO;

	public TodoController() {
		todoDAO = new TodoDAOImpl();
	}

	// http://localhost:8080/mvc/todo/todoForm (권장x)
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인증 검사
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO) session.getAttribute("principal");
		if (principal == null) {
			response.sendRedirect("/mvc/user/signIn?message=invalid");
			return;
		}

		String action = request.getPathInfo();
		switch (action) {
		case "/todoForm":
			todoFormPage(request, response);
			break;
		case "/list":
			todoListPage(request, response, principal.getId());
			break;
		case "/detail":
			todoDetail(request, response, principal.getId());
			break;
		case "/delete":
			todoDelete(request, response, principal.getId());
			break;
		default:
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			break;
		}
	}

	// todo 리스트 페이지로 이동
	private void todoListPage(HttpServletRequest request, HttpServletResponse response, int principalId)
			throws IOException, ServletException {
		request.setAttribute("todoList", todoDAO.getTodosById(principalId));
		request.getRequestDispatcher("/WEB-INF/views/todoList.jsp").forward(request, response);
	}

	// todo 작성 페이지로 이동
	private void todoFormPage(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		request.getRequestDispatcher("/WEB-INF/views/todoForm.jsp").forward(request, response);
	}

	// todo 상세보기 페이지로 이동
	// http://localhost:8080/mvc/todo/detail?id=2
	private void todoDetail(HttpServletRequest request, HttpServletResponse response, int principalId)
			throws ServletException, IOException {
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			TodoDTO todoDTO = todoDAO.getTodoById(id);
			if (todoDTO.getUserId() == principalId) {
				// 상세보기 페이지로 이동
				request.setAttribute("todo", todoDTO);
				request.getRequestDispatcher("/WEB-INF/views/todoDetail.jsp").forward(request, response);
			} else {
				// 권한이 없습니다 or 잘못된 접근입니다.
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>alert('권한이 없습니다'); history.back();</script>");
//				response.sendRedirect(request.getContextPath() + "/todo/list?error=invalid");
			}
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/todo/list?error=invalid");
			e.printStackTrace();
		}
	}

	// todo 삭제 기능
	private void todoDelete(HttpServletRequest request, HttpServletResponse response, int principalId)
			throws IOException, ServletException {
		try {
			todoDAO.deleteTodo(Integer.parseInt(request.getParameter("id")), principalId);
		} catch (Exception e) {
			response.sendRedirect(request.getContextPath() + "/todo/list?error=invalid");
			e.printStackTrace();
		}
		response.sendRedirect(request.getContextPath() + "/todo/list");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// 인증 검사
		HttpSession session = request.getSession();
		UserDTO principal = (UserDTO) session.getAttribute("principal");
		if (principal == null) {
			response.sendRedirect("/mvc/user/signIn?message=invalid");
			return;
		}

		String action = request.getPathInfo();
		switch (action) {
		case "/add":
			todoAdd(request, response, principal.getId());
			break;
		case "/update":
			updateTodo(request, response, principal.getId());
			break;
		default:
			break;
		}
	}

	// todo 추가 기능
	private void todoAdd(HttpServletRequest request, HttpServletResponse response, int principalId) throws IOException {
		TodoDTO todoDTO = new TodoDTO();
		todoDTO.setTitle(request.getParameter("title"));
		todoDTO.setDescription(request.getParameter("description"));
		todoDTO.setDueDate(request.getParameter("dueDate"));

		String completed = request.getParameter("completed");
		if (completed != null && "on".equals(completed)) {
			todoDTO.setCompleted("true");
		} else {
			todoDTO.setCompleted("false");
		}
		todoDAO.addTodo(todoDTO, principalId);
		response.sendRedirect("list");
	}

	/**
	 * todo 수정 기능
	 * @param request
	 * @param response
	 * @param principalId = 세션 ID값
	 * @throws IOException
	 */
	private void updateTodo(HttpServletRequest request, HttpServletResponse response, int principalId)
			throws IOException {
		try {
			TodoDTO todoDTO = new TodoDTO();
			todoDTO.setUserId(principalId);
			todoDTO.setId(Integer.parseInt(request.getParameter("id")));
			todoDTO.setTitle(request.getParameter("title"));
			todoDTO.setDescription(request.getParameter("description"));
			todoDTO.setDueDate(request.getParameter("dueDate"));
			String completed = request.getParameter("completed");
			if (completed != null && "on".equals(completed)) {
				todoDTO.setCompleted("true");
			} else {
				todoDTO.setCompleted("false");
			}
			todoDAO.updateTodo(todoDTO, principalId);
		} catch (Exception e) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('잘못된 요청입니다'); history.back();</script>");
		}
		// 리스트 페이지로 재요청
		response.sendRedirect("list");
	}
}
