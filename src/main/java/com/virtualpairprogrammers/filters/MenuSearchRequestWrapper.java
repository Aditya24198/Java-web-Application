package com.virtualpairprogrammers.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MenuSearchRequestWrapper extends HttpServletRequestWrapper {
    private String newSearchTerm;
	public MenuSearchRequestWrapper(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
	}
	public void setNewSearchTerm(String newSearchTerm) {
		this.newSearchTerm=newSearchTerm;
	}
	@Override
	public String getParameter(String Key) {
		if(Key.equals("searchTerm")) {
		return newSearchTerm;
		}else {
			return super.getParameter(Key);
		}
	}

}
