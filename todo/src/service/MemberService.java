package service;

import java.sql.Connection;
import java.sql.SQLException;

import commons.DBUtil;
import dao.MemberDao;
import dao.TodoDao;
import vo.Member;

public class MemberService {
	private MemberDao memberDao;
	private TodoDao todoDao;
	
	public void getInsertMember(Member member) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			memberDao.insertMember(conn, member);
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
	
	public boolean removeMember(String memberId, String memberPw) {
		boolean result = false;
		Connection conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
		try {
			conn.setAutoCommit(false);
			todoDao = new TodoDao();
			memberDao = new MemberDao();
			todoDao.deleteTodoByMember(conn, memberId);
			if(memberDao.deleteMember(conn, memberId, memberPw) != 1) {
				throw new Exception();
			}
			conn.commit();
			result = true;
		} catch(Exception e) {
			e.printStackTrace();
			try {
				System.out.println("롤백 실행");
				conn.rollback();
				return false;
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public Member login(Member member) {
		Member loginMember = null;
		Connection conn = null;
		try {
			conn = DBUtil.getConnection("jdbc:mariadb://3.39.229.136:3306/todo", "root", "java1004");
			memberDao = new MemberDao();
			loginMember = memberDao.login(conn, member);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return loginMember;
	}
}