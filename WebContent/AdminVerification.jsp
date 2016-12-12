<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if(session.getAttribute("login") == null || !session.getAttribute("login").equals("admin")){
		response.sendRedirect("/chinookDB/Login.jsp");
		return;
	}
%>