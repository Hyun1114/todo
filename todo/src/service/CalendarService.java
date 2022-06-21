package service;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import commons.DBUtil;
import dao.TodoDao;
import vo.Todo;


public class CalendarService {
	private TodoDao todoDao;
	
	public Map<String, Object> getTargetCalendar(String memberId, String currentYear, String currentMonth, String option) { // option : pre, mext
		
		// 1. 달력(모델)
		Map<String, Object> map = new HashMap<>();
		
		Calendar c = Calendar.getInstance(); // 오늘 날짜의 년도와 월을 가진다.
		Calendar c2 = Calendar.getInstance();
		if(currentYear != null && currentMonth != null) {
			int y = 0;
			int m = 0;
			y = Integer.parseInt(currentYear);
			m = Integer.parseInt(currentMonth);
		if(option != null && option.equals("pre")) {
			m = m - 1; // ISSUE : 0일 때
			if(m == 0) {
				m = 12;
				y--;
			}
		} else if(option != null && option.equals("next")) {
			m = m + 1; // ISSUE : 13일 때
			if(m == 13) {
				m = 1;
				y++;
				}
			}
			c.set(Calendar.YEAR, y);
			c.set(Calendar.MONTH, m-1); // 0월 ~ 11월
		}
		
		c.set(Calendar.DATE, 1); // c 객체가 오늘의 정보 -> 같은 달 1일을 변경
		
		// 달력에 필료한 데이터(모델)
		int targetYear = c.get(Calendar.YEAR);
		int targetMonth = c.get(Calendar.MONTH) + 1;
		int endDay = c.getActualMaximum(Calendar.DATE);
		c2.set(Calendar.MONTH, targetMonth-2);
		int preEndDay = c2.getActualMaximum(Calendar.DATE);
		
		// 달력 앞, 뒤 공백의 개수
		int startBlank = 0; // 타겟이 되는 달의 1일의 요일 -> 일요일이면 0, 월요일이면 1... 토요일이면 6
		startBlank = c.get(Calendar.DAY_OF_WEEK) - 1;
				
		int endBlank = 0; // 전체의 <td> 개수 = startBlank+endDay+endBlank -> 이 값이 7로 나누어 떨어지도록
		endBlank = 7 - (startBlank+endDay)%7;
		if(endBlank == 7) {
			endBlank = 0;
		}
	
		map.put("targetYear", targetYear);
		map.put("targetMonth", targetMonth);
		map.put("endDay", endDay);
		map.put("startBlank", startBlank);
		map.put("endBlank", endBlank);
		map.put("preEndDay", preEndDay);
		
		List<Todo> list = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
			todoDao = new TodoDao();
			Todo todo = new Todo(); // todoDate의 년, 월 -> targetYear와 targetMonth 이용하여 구하기
			todo.setMemberId(memberId); // memberId -> 매개변수로 입력
			
			// 디버깅 코드
			System.out.println(todo + " <-- todo");
			
			String strYear = "" + targetYear;
			String strMonth = "" + targetMonth;
			if(targetMonth < 10) {
				strMonth = "0" + targetMonth;
			}
			
			todo.setTodoDate(strYear + "-" + strMonth);
			list = todoDao.selectTodoListByMonth(conn, todo);
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		map.put("todoList", list);
		return map;
	}
}
