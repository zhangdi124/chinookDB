package com.chinookDB.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.chinookDB.services.InfoLookupService;

public class GenreLookupTagSupport extends SimpleTagSupport {
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		InfoLookupService infoLookup = InfoLookupService.getInstance();
		
		if(var == null)
			pageContext.setAttribute("genres", infoLookup.lookupGenres());
		else
			pageContext.setAttribute(var, infoLookup.lookupGenres());
	}

	public void setVar(String var){ this.var = var; }
}
