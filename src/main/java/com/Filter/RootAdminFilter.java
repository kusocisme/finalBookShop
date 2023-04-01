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

@WebFilter("/admin/*")
public class RootAdminFilter implements Filter {

	public RootAdminFilter() {

	}

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		int roleId;
		try {
			roleId = (int) session.getAttribute("roleId");
		} catch (NullPointerException e) {
			request.getRequestDispatcher("/WEB-INF/admin/noPermission.jsp").forward(request, response);
			return;
		}
		if (roleId != 1) {
			request.getRequestDispatcher("/WEB-INF/admin/noPermission.jsp").forward(request, response);
			return;
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

}
