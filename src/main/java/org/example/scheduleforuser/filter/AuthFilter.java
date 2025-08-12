package org.example.scheduleforuser.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 회원가입, 로그인 요청은 인증 처리에서 제외
        if (req.getRequestURI().startsWith("/users") || req.getRequestURI().startsWith("/login")) {
            chain.doFilter(request, response);
            return;
        }

        // 쿠키에서 sessionId 확인
        if (req.getCookies() != null) {
            for (jakarta.servlet.http.Cookie cookie : req.getCookies()) {
                if (cookie.getName().equals("sessionId")) {
                    // 실제로는 세션 저장소에서 sessionId 유효성 검증
                    chain.doFilter(request, response);
                    return;
                }
            }
        }

        // 인증 실패 시 401 Unauthorized 반환
        res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        res.getWriter().write("Unauthorized: Please log in.");
    }
}