package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.TodoService;
import vo.Member;
import vo.Todo;

@WebServlet("/member/addTodo")
public class AddTodoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TodoService todoService;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 수집
		String memberId = ((Member)(request.getSession().getAttribute("loginMember"))).getMemberId();
		String todoDate = request.getParameter("todoDate");
		String todoContent = request.getParameter("todoContent");
		
		// 디버깅 코드
		System.out.println(memberId + " <-- memberId");
		System.out.println(todoDate + " <-- todoDate");
		System.out.println(todoContent + " <-- todoContent");
		
		// VO 에서 데이터 불러오기
		Todo todo = new Todo();
		todo.setMemberId(memberId);
		todo.setTodoDate(todoDate);
		todo.setTodoContent(todoContent);
		
		// todoService 호출
		todoService = new TodoService();
		todoService.getInsertTodo(todo);
		
		// 년, 월, 일
		String y = todoDate.substring(0,4);
		String m = todoDate.substring(5,7);
		String d = todoDate.substring(todoDate.length()-2, todoDate.length());
		
		// 페이지 이동
		response.sendRedirect(request.getContextPath()+"/member/todoList?y="+y+"&m="+m+"&d="+d);
	}
}
