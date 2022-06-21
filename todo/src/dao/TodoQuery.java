package dao;

public class TodoQuery {
	public static final String UPDATE_TODO;
	public static final String DELETE_TODO_By_Member;
	public static final String DELETE_TODO;
	public static final String SELECT_TODO_LIST_BY_DAYE;
	public static final String INSERT_TODO;
	public static final String SELECT_TODO_LIST_BY_MONTH;
	
	static {
		UPDATE_TODO ="UPDATE todo SET todo_content=?, update_date=NOW() WHERE todo_no=? AND member_id=?";
		DELETE_TODO = "DELETE FROM todo WHERE todo_no=? AND member_id=?";
		DELETE_TODO_By_Member = "DELETE FROM todo WHERE member_id=?";
		SELECT_TODO_LIST_BY_DAYE = "SELECT todo_no todoNo, todo_date todoDate, todo_content todoContent, create_date createDate, update_date updateDate FROM todo WHERE todo_date=? AND member_id=?";
		INSERT_TODO = "INSERT INTO todo(member_id, todo_date, todo_content, create_date, update_date) values(?, ?, ?, NOW(), NOW())";
		SELECT_TODO_LIST_BY_MONTH = "SELECT todo_date todoDate, SUBSTR(todo_content, 1, 5) todoContent5 FROM todo WHERE member_id=? AND SUBSTR(todo_date, 1, 7)=? ORDER BY todo_date";
	}
}
