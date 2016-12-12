package com.chinookDB.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.chinookDB.Invoice;
import com.chinookDB.services.InvoiceService;

public class InvoiceTagSupport extends SimpleTagSupport {
	String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext =  (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("customerId") == null)
			return;
		
		InvoiceService service = InvoiceService.getInstance();
		
		Invoice invoice = service.getInvoice((Integer) session.getAttribute("customerId"));
		
		if(var == null)
			pageContext.setAttribute("invoice", invoice);
		else
			pageContext.setAttribute(var, invoice);
	}

	public void setVar(String var){
		this.var = var;
	}
}
