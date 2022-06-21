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


@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberService memberService;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 이미 로그인 되었다면 요청 처리 불가
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) {
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		// login 폼 페이지
		request.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 인코딩
		// request.setCharacterEncoding("utf8");
		// 모든 컨트롤러의 doPost() 상단에 무조건 request.setCharacterEncoding() 필요
		// -> 공통된 로직이 중복 -> 필터를 사용하자
		// 이미 로그인 되었다면 요청 처리 불가 -> 이 로직도 필터를 사용 가능
		HttpSession session = request.getSession();
		if(session.getAttribute("loginMember") != null) { // 이미 로그인 되어있는 상태다
			response.sendRedirect(request.getContextPath()+"/member/calendar");
			return;
		}
		
		// 데이터 생성
		String memberId = request.getParameter("memberId");
		String memberPw = request.getParameter("memberPw");
		
		// 디버깅 코드
		System.out.println(memberId+ " <-- memberId");
		System.out.println(memberPw+ " <-- memberPw");
		
		// login 액션 페이지
		Member paramMember = new Member();
		paramMember.setMemberId(memberId);
		paramMember.setMemberPw(memberPw);
		System.out.println(memberId + " <-- memberId");
		System.out.println(memberPw + " <-- memberPw");
		
		memberService = new MemberService();
		Member loginMember = memberService.login(paramMember);
		if(loginMember == null) {
			response.sendRedirect(request.getContextPath()+"/login");
			return;
		} 
		session.setAttribute("loginMember", loginMember);
		
		// 새로운 요청
		response.sendRedirect(request.getContextPath()+"/member/calendar");
	}
}
