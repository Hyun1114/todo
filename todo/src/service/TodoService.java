package service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;

public class TodoService {
	private TodoDao todoDao;
	
	public void updateTodo(Todo todo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.updateTodo(conn, todo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public int removeTodo(Todo todo) {
		Connection conn = null;
		int row = 0;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			row = todoDao.deleteTodo(conn, todo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return row;
	}
	
	public void getInsertTodo(Todo todo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			todoDao.insertTodo(conn, todo);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	 public List<Todo> getTodoListByDate(Todo todo) {
	      List<Todo> list = null;   
	      Connection conn = null;
	      try {
	         conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
	         todoDao = new TodoDao();
	         list = todoDao.selectTodoListByDate(conn, todo);
	      } catch(Exception e) {
	         e.printStackTrace();
	      } finally {
	         try {
	            conn.close();
	         } catch (SQLException e) {
	            e.printStackTrace();
	         }
	      }
	      return list;
	   }
}
