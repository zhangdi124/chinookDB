<%@page import="com.chinookDB.beans.Customer"%>
<%@page import="java.util.List"%>
<%@page import="com.chinookDB.CustomerFactory"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	CustomerFactory cf = CustomerFactory.getInstance();
	List<Customer> customers = cf.getAllCustomers();
%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Chinook Customers</title>
		<style>
			body{
			background:white;
			} 
			table{
				border-collapse: collapse;
			}
			td, th{
				border: 1px solid black;
			}
			th{
				background: #CCC;
			}
			tr:nth-child(even){
				background: #DDD;
			}
			
			footer p{
				margin: 0;
			}
			footer{
				background: #000;
				color: #FFF;
			}
		</style>
	</head>
	<body>
	<header>
		<div class="container">
			<h1>Chinook</h1>
			<nav>
				<a href="#">FAQ</a>
				<a href="#">Support</a>
			</nav>
	
		</div>
	</header>
	<h1>Customers</h1>
		<table>
			<tr>
				<th>Customer ID</th>
				<th>Name</th>
				<th>Support Rep</th>
			</tr>
		<%for(Customer c : customers){%>
			<tr>
				<td>
					<a href="./customerDetails.jsp?customerId=<%=c.getId()%>">
						<%=c.getId()%>
					</a>
				</td>
				<td>
					<a href="./customerDetails.jsp?customerId=<%=c.getId()%>">
					<%=c.getFirstName()%> <%=c.getLastName()%>
					</a>
				</td>
				<td>	
					<a href="./customerDetails.jsp?customerId=<%=c.getId()%>">
					<%=c.getSupportRepId()%> 
					</a>	
				</td>	
				
			</tr>
		<%}%>
		</table>
		 <footer>
  			<p>Contact Us</p>
  			<p>Chinook</p>
  			<p>555 Fifth Avenue</p>
  			<p>New York, NY, 10010</p>
  			<p>Email: <a href="mailto:someone@example.com">
  			someone@example.com</a>.</p>
		</footer> 
	</body>
</html>