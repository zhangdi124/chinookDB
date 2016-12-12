package com.chinookDB.tags;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.chinookDB.beans.Customer;
import com.chinookDB.services.CustomerService;

public class CustomerTagSupport extends SimpleTagSupport {
	private String var;
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext = (PageContext)getJspContext();
		HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
		HttpSession session = request.getSession();
		
		Customer customer = null;
		if(session.getAttribute("customerId") != null){
			int customerId = (Integer)session.getAttribute("customerId");
			customer = CustomerService.getInstance().findCustomerById(customerId);
		}

		if(var == null)
			pageContext.setAttribute("customer", customer);
		else
			pageContext.setAttribute(var, customer);
	}

	public void setVar(String var){
		this.var = var;
	}
}
