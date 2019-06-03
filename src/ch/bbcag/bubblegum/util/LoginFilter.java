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
import ch.bbcag.bubblegum.util.message.Message;
import ch.bbcag.bubblegum.util.message.MessageArray;
import ch.bbcag.bubblegum.util.message.MessageStyle;

public class LoginFilter implements Filter {

	@Inject
	private HelperBean helperBean;
	
	@Inject
	private MessageArray msgArray;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (!helperBean.isLogedIn()) {
			helperBean.addMessage(new Message(MessageStyle.error, "Du hast keine Berechtigung für diese Seite!"));
			String contextPath = ((HttpServletRequest) request).getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath + "/home.xhtml");
		}
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
