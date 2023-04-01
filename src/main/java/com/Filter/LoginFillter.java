package com.Filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter({ "/profile" })
public class LoginFillter implements Filter {

    public LoginFillter() {

    }

    public void destroy() {

    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpSession session = httpRequest.getSession();
        Object username;
        try {
            username = session.getAttribute("username");
        } catch (NullPointerException e) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        if (username == null) {
            request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {

    }

}
