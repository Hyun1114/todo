package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MemberService;
import vo.Member;

@WebServlet("/AddMember")
public class AddMemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 이미 로그인 되었다면 요청 처리 불가
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		request.getRequestDispatcher("/WEB-INF/view/addMember.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 데이터 수집
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 디버깅 코드
		System.out.println(memberId + " <-- memberId");
		System.out.println(memberPw + " <-- memberPw");
		
		Member member = new Member();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
		
		memberService = new MemberService();
		memberService.getInsertMember(member);
		
		// 디버깅 코드
		System.out.println(member + " <-- member");
		
		// 새로운 요청
		response.sendRedirect(request.getContextPath()+"/login");
		
	}
}
