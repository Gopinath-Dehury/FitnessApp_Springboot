package com.fitnessApp.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // Get the session from the request. Do not create a new one.
        HttpSession session = request.getSession(false);
        String requestURI = request.getRequestURI();

        // Allow access to public pages (login, registration) without a session.
        if (requestURI.contains("/login") || requestURI.contains("/registerUsers") || requestURI.contains("/showRegistrationPage")) {
            return true;
        }

        // Check if the session is null or has expired.
        if (session == null || session.getAttribute("user") == null) {
            // If there's no active session, redirect to the login page.
            response.sendRedirect(request.getContextPath() + "/fitness/loginform");
            // Return false to stop the request from proceeding to the controller.
            return false;
        }

        // If a valid session exists, continue with the request.
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // You can add logic here to be executed after the controller method, but before the view is rendered.
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // You can add logic here to be executed after the entire request has been completed.
    }
}
