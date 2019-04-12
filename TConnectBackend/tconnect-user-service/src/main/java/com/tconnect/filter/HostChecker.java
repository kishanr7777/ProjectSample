package com.tconnect.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.tatva.tconnectGeneralConfigs.URLConfigs;

@Component
public class HostChecker implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {
		String isNotAuthorized = URLConfigs.isRequestHostAuthorised(req.getRemoteHost()); 

		if(isNotAuthorized == null) {
			filterChain.doFilter(req, res);
			return;
		}
		
		HttpServletResponse response = (HttpServletResponse) res; 	
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.getOutputStream().print(isNotAuthorized);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
