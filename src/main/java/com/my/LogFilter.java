package com.my;

import clases.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.LogRecord;

public class LogFilter implements Filter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		System.out.println("\n\n\nfilter is worked\n\n\n");
		HttpServletRequest req = (HttpServletRequest) servletRequest;

		HttpServletResponse res = (HttpServletResponse) servletResponse;
		if ((req.getSession().getAttribute("user") != null) || (req.getSession().getAttribute("admin") != null))
			filterChain.doFilter(servletRequest, servletResponse);
		else
			res.sendRedirect("index.jsp");

	}

}
