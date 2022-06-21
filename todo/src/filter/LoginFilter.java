package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/member/*")
public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		// 로그인 상태에서만 요청할 수 있도록 필터링 -> 필터링 사용(LoginFilter)
		System.out.println("LoginFilter.ServletRequest() 실행");
		HttpSession session = ((HttpServletRequest)request).getSession();
		if(session.getAttribute("loginMember") == null) {
			((HttpServletResponse)response).sendRedirect(((HttpServletRequest)request).getContextPath()+"/login");
			return;
		}
		chain.doFilter(request, response);
	}
}
