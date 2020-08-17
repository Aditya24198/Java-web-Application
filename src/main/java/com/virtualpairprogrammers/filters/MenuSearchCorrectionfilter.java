package com.virtualpairprogrammers.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
@WebFilter(value = {"/searchResults.html"})
public class MenuSearchCorrectionfilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		  String searchTerm = request.getParameter("searchTerm");
          if(searchTerm.equals("chook")) {
        	  MenuSearchRequestWrapper requestWrapper = new MenuSearchRequestWrapper((HttpServletRequest) request);
        	  requestWrapper.setNewSearchTerm("chicken");
        	  searchTerm = requestWrapper.getParameter("searchTerm");
        	  chain.doFilter(requestWrapper, response);
          }else {
        	  chain.doFilter(request, response); 
          }
          System.out.println("User Searched for: "+searchTerm);
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
