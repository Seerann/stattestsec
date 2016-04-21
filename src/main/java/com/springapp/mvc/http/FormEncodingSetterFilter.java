package com.springapp.mvc.http;

import javax.servlet.*;
import java.io.IOException;

/**
 * Created by Slame on 07.09.2015.
 */
public class FormEncodingSetterFilter implements Filter{
	private static final String FILTERABLE_CONTENT_TYPE="application/x-www-form-urlencoded";

	private static final String ENCODING_DEFAULT = "UTF-8";

	private static final String ENCODING_INIT_PARAM_NAME = "encoding";

	private String encoding;

	@Override
	public void destroy(){
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
						 FilterChain chain) throws ServletException, IOException {
		String contentType = req.getContentType();
		if (contentType != null && contentType.startsWith(FILTERABLE_CONTENT_TYPE))
			req.setCharacterEncoding(encoding);
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig config) throws ServletException{
		encoding = config.getInitParameter(ENCODING_INIT_PARAM_NAME);
		if (encoding == null)
			encoding = ENCODING_DEFAULT;
	}
}
