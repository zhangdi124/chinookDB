<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/chinook-services.tld" prefix="services" %>    

<services:getCustomer/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="./GlobalPageHead.jsp">
	<jsp:param value="Shopping Cart" name="pageTitle"/>
</jsp:include>
<body>
	<jsp:include page="./customer-header.jsp"/>
	<div class="jumbotron">
		<h1>Order Shipped!</h1>
		<p>The order for customer ${customer.firstName} ${customer.lastName} has been shipped.</p>
		<p>Please inform the customer and make any appropriate recommendations.</p>
	</div>
</body>
</html>