package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Todo;

@WebServlet("/member/updateTodo")
public class UpdateTodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoService todoService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 생성
		String todoNo = request.getParameter("todoNo");
		String memberId = request.getParameter("memberId");
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		// 디버깅 코드
		System.out.println(todoNo+ " <-- todoNo");
		System.out.println(memberId+ " <-- memberId");
		System.out.println(todoDate+ " <-- todoDate");
		System.out.println(todoContent+ " <-- todoContent");
		
		Todo todo = new Todo();
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		
		// 수정폼에서도 일정 목록을 볼 수 있도록 일정 목록을 불러옴
		todoService = new TodoService();
		List<Todo> todoList = todoService.getTodoListByDate(todo);

		// 수정에 필요한 데이터들을 전달
		request.setAttribute("todoList", todoList);
		request.setAttribute("todoDate", todoDate);
		request.setAttribute("todoNo", todoNo);
		request.setAttribute("todoContent", todoContent);
		request.setAttribute("memberId", memberId);
		request.getRequestDispatcher("/WEB-INF/view/updateTodo.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 생성
		int todoNo = Integer.parseInt(request.getParameter("todoNo"));
		String memberId = request.getParameter("memberId");
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		// 디버깅 코드
		System.out.println(todoNo+ " <-- todoNo");
		System.out.println(memberId+ " <-- memberId");
		System.out.println(todoDate+ " <-- todoDate");
		System.out.println(todoContent+ " <-- todoContent");
		
		Todo todo = new Todo();
		todo.setTodoNo(todoNo);
		todo.setTodoDate(todoDate);
		todo.setMemberId(memberId);
		todo.setTodoContent(todoContent);
		
		// 오늘 일정 수정 Action 페이지
		todoService = new TodoService();
		todoService.updateTodo(todo);
		
		// 년, 월, 일
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(todoDate.length()-2, todoDate.length());
		
		// 페이지 이동
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
	}
}
