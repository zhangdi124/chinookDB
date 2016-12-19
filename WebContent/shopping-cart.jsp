<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/chinook-services.tld" prefix="services" %>

    
<services:getCustomer/>
<services:getInvoice/>
<!DOCTYPE html>
<html>
	<jsp:include page="./GlobalPageHead.jsp">
		<jsp:param value="Shopping Cart" name="pageTitle"/>
	</jsp:include>
	<body>
		<jsp:include page="./customer-header.jsp"/>
		<main class="container">
			<services:message/>
			<div class="panel panel-default">
				<div class="panel panel-heading">
					<h4 class="panel-title">Shopping Cart | Order Summary</h4>
				</div>
				<div class="panel-body">
					<table class="table table-condensed table-bordered table-striped">
						<thead>
							<tr class="primary">
								<th>ID</th>
								<th>Title</th>
								<th>Quantity</th>
								<th>Unit Price</th>
								<th></th>
							</tr>
						</thead>
						<c:forEach items="${invoice.invoiceLines}" var="invoiceLine">
							<c:set var="track" value="${invoiceLine.track}"/>
							<tr>
								<td>${track.id}</td>
								<td>${track.title}</td>
								<td>${invoice.getQuantity(track)}</td>
								<td>$${track.price}</td>
								<td>
									<form action="/chinookDB/CustomerInvoiceManagement.jsp">
										<div class="input-group quantity-input">
											<input type="hidden" name="track" value="${track.id}"/>
											<input class="form-control" name="quantity" value="1">
												<span class="input-group-btn">
													<button class="btn btn-success" name="command" value="add" type="submit">Add</button>
													<button class="btn btn-danger" name="command" value="remove" type="submit">Remove</button>
												</span>
										</div>
									</form>							
								</td>
							</tr>				
						</c:forEach>
							<tr class="primary">
								<td></td>
								<td></td>
								<td></td>
								<td><b>Total: $${invoice.totalPrice}</b></td>
								<td>
									<form action="/chinookDB/submit-invoice.jsp">
										<button type="submit" class="btn btn-primary">Submit</button>
									</form>
								</td>
							</tr>							
					</table>
				</div>
			</div>
		</main>
	</body>
</html>