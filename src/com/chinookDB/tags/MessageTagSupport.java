package com.chinookDB.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

public class MessageTagSupport extends SimpleTagSupport {
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext =  (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession();
		
		Object message = session.getAttribute("message");
		session.removeAttribute("message");
		
		if(message == null)
			return;
		
		JspWriter out = getJspContext().getOut();
		
		StringBuilder sb = new StringBuilder();
		sb.append("<div class=\"alert alert-success\" role=\"alert\">")
			.append(message.toString())
			.append("</div>");
		
		out.write(sb.toString());
	}
	
}
