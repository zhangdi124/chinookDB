<%@page import="com.chinookDB.beans.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.chinookDB.services.CustomerService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<%
	CustomerService service = CustomerService.getInstance();

	List<Customer> customers = service.getAllCustomers();
	
	pageContext.setAttribute("customers", customers);
	
	String login = request.getParameter("login");
	
	if(login != null){
		session.setAttribute("login", login);
		if(login.equals("customer") || login.equals("employee")){
			int customerId = Integer.parseInt(request.getParameter("customerId"));
			session.setAttribute("customerId", customerId);
			response.sendRedirect("/chinookDB/product-search.jsp");
		}else if(login.equals("admin")){
			response.sendRedirect("/chinookDB/admin.jsp");
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Chinook Login</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-default">
  			<div class="container-fluid">
    			<div class="navbar-header">
      				<a class="navbar-brand" href="#">Chinook Music</a>   				
   				</div>
			</div>
		</nav>
	</header>
	<div class="container">
	<div class="panel panel-default">
 		<div class="panel-heading">
	    	<h3 class="panel-title">Chinook Login</h3>
  		</div>
	  	<div class="panel-body">
	  		<form>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-xs-12">
		    				<div class="input-group">
			    				<select name="customerId">
			    					<c:forEach items="${customers}" var="customer">
			    						<option value="${customer.id}">${customer.firstName} ${customer.lastName}</option>
			    					</c:forEach>
			    				</select>
		    				</div>
		    				<br/>
		    				<div class="btn-group" role="group" aria-label="Login Type">
		    					<button type="submit" name="login" value="customer" class="btn btn-primary">Login as Customer</button>
		    					<button type="submit" name="login" value="employee" class="btn btn-success">Login as Employee</button>
		    				</div>    				
		    			</div>
		    		</div>
		    	</div>
	    		<hr/>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-xs-12">
		    				<button type="submit" name="login" value="admin" class="btn btn-danger">Login as Admin</button>
		    			</div>
		    		</div>
		    	</div>	
			</form>   		    	
	  	</div>
	</div>
	</div>
</body>
</html>