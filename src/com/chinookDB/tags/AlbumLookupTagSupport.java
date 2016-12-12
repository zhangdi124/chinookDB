package com.chinookDB.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.chinookDB.services.AlbumService;

public class AlbumLookupTagSupport extends SimpleTagSupport {
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		
		AlbumService service = AlbumService.getInstance();
		
		if(var == null)
			pageContext.setAttribute("albums", service.getAlbums());
		else
			pageContext.setAttribute(var, service.getAlbums());
	}

	public void setVar(String var){ this.var = var; }
}
