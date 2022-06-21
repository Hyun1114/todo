package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import vo.Todo;

public class TodoDao {
	
	public void updateTodo(Connection conn, Todo todo) throws SQLException {
		
		String sql = TodoQuery.UPDATE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getTodoContent());
		stmt.setInt(2, todo.getTodoNo());
		stmt.setString(3, todo.getMemberId());
		System.out.println(stmt + "<-- stmt");
		
		stmt.executeUpdate();
		stmt.close();
	}
	
	public int deleteTodo(Connection conn, Todo todo) throws SQLException {
		String sql = TodoQuery.DELETE_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, todo.getTodoNo());
		stmt.setString(2, todo.getMemberId());
		
		System.out.println(stmt + "<-- stmt");
		int row = stmt.executeUpdate();
		stmt.close();
		return row;
	}
	
	public List<Todo> selectTodoListByMonth(Connection conn, Todo todo) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODO_LIST_BY_MONTH;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent5"));
			list.add(t);
		}
		System.out.println(stmt + "<-- stmt");
		System.out.println(rs + "<-- rs");
		rs.close();
		stmt.close();
		return list;
	}
	
	public void insertTodo(Connection conn, Todo todo) throws SQLException {
		String sql = TodoQuery.INSERT_TODO;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getMemberId());
		stmt.setString(2, todo.getTodoDate());
		stmt.setString(3, todo.getTodoContent());
		stmt.executeUpdate();
		stmt.close();
	}
	
	public List<Todo> selectTodoListByDate(Connection conn, Todo todo) throws SQLException {
		List<Todo> list = new ArrayList<Todo>();
		String sql = TodoQuery.SELECT_TODO_LIST_BY_DAYE;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, todo.getTodoDate());
		stmt.setString(2, todo.getMemberId());
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Todo t = new Todo();
			t.setTodoNo(rs.getInt("todoNo"));
			t.setTodoDate(rs.getString("todoDate"));
			t.setTodoContent(rs.getString("todoContent"));
			t.setCreateDate(rs.getString("createDate"));
			t.setUpdateDate(rs.getString("updateDate"));
			list.add(t);
		}
		System.out.println(stmt + "<-- stmt");
		System.out.println(rs + "<-- rs");
		rs.close();
		stmt.close();
		return list;
	}
	
	public void deleteTodoByMember(Connection conn, String memberId) throws SQLException {
		String sql = TodoQuery.DELETE_TODO_By_Member;
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, memberId);
		stmt.executeUpdate();
		stmt.close();
	}
}
