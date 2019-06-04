package ch.bbcag.bubblegum.util;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ch.bbcag.bubblegum.bean.HelperBean;

public class HomeRedirectionFilter implements Filter {

	 @Inject
	 private HelperBean helperBean;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;

		String contextPath = ((HttpServletRequest) request).getContextPath();

		if (helperBean.isLogedIn()) {
			((HttpServletResponse) response).sendRedirect(contextPath + "/overview.xhtml");
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
