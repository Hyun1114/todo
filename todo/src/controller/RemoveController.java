package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.MemberService;

@WebServlet("/member/removeMember")
public class RemoveController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/view/removeMember.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 생성
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 디버깅 코드
		System.out.println(memberId+ " <-- memberId");
		System.out.println(memberPw+ " <-- memberPw");
		
		// 회원탈퇴 Action 페이지
		memberService = new MemberService();
		if(memberService.removeMember(memberId, memberPw)) {
			// 회원 탈퇴 성공 시 login 페이지로 리턴
			System.out.println("회원탈퇴 성공");
			response.sendRedirect(request.getContextPath()+"/logout");
			return;
		} else {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
		}
	}
}
