package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Todo;

@WebServlet("/member/removeTodo")
public class RemoveTodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoService todoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 생성
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String memberId = request.getParameter("memberId");
		String todoDate = request.getParameter("todoDate");
		
		// 디버깅 코드
		System.out.println(todoNo+ " <-- todoNo");
		System.out.println(memberId+ " <-- memberId");
		System.out.println(todoDate+ " <-- todoDate");
		
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setMemberId(memberId);
		
		// 오늘 일정 삭제 Action 페이지
		todoService = new TodoService();
		int row = todoService.removeTodo(todo);
		
		// 년, 월, 일
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(todoDate.length()-2, todoDate.length());

		// 삭제 결과에 따라 페이지 이동
		if (row == 0) {
			System.out.println("일정 삭제 실패");
			response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
		} else {
			System.out.println("일정 삭제 성공");
			response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
		}
	}		
}
