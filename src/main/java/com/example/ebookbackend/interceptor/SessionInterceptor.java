package com.example.ebookbackend.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String s = String.valueOf(request.getRequestURL());
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            System.out.println("OPTIONS is invoked");
            return true;
        }
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("uid") != null) {
            System.out.println("Session is already logged in");
            return true;
        }
        System.out.println("Session is not logged in yet");
        response.setStatus(401);
        return false;
    }
}
