package com.provisioning.gateway.soap.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.stereotype.Component;
//this method map the ?wsdl to .wsdl 
@Component
public class WSDLQuestionMarkReplaceFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// put init logs
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		if ("wsdl".equalsIgnoreCase(httpRequest.getQueryString())) {
			HttpServletRequestWrapper requestWrapper = new HttpServletRequestWrapper(httpRequest) {
				@Override
				public String getQueryString() {
					return null;
				}

				@Override
				public String getRequestURI() {
					return super.getRequestURI() + ".wsdl";
				}
			};
			chain.doFilter(requestWrapper, response);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// put destroy logs

	}
}
