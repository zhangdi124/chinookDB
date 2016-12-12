<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/chinook-services.tld" prefix="services" %>

<services:queryTracks var="tracks" artist="${param.artist}" genre="${param.genre}" title="${param.title}"/>
<services:getCustomer/>
<services:getInvoice/>

<!DOCTYPE html>
<html>
<jsp:include page="./GlobalPageHead.jsp">
	<jsp:param value="Chinook Products Search" name="pageTitle"/>
</jsp:include>
<body>
	<jsp:include page="./customer-header.jsp"/>
	<main>
		<services:message/>
		<div class="container">
			<c:choose>
				<c:when test="${empty param}">
					<c:choose>
						<c:when test="${sessionScope.login == 'customer'}">
							<h1>Welcome ${customer.firstName}! Please select options for tracks you would like to view.</h1>
						</c:when>
						<c:otherwise>
							<h1>Currently modifying order for Customer ID: #${customer.id} ${customer.firstName} ${customer.lastName}. Please select options for filtering.</h1>
						</c:otherwise>
					</c:choose>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${fn:length(tracks) == 0}">
							<h1>We are sorry, no tracks could be found that match your search criteria.</h1>
						</c:when>
						<c:otherwise>
							<h2>Found ${fn:length(tracks)} track(s) that match your search criteria:</h2>
							<c:forEach items="${tracks}" var="track" varStatus="status">
								<hr/>
								<div class="row">
									<div class="col-xs-6">
										<h3 class="track-title">${track.title}</h3>
										<h4><small><b>Artist:</b>&nbsp;${track.artist}</small></h4>
										<h4><small><b>Album:</b>&nbsp;${track.album}</small></h4>
										<h4><small><b>Genre:</b>&nbsp;${track.genre}</small></h4>
									</div>
									<div class="col-xs-6">
										<form action="/chinookDB/CustomerInvoiceManagement.jsp">
											<div>
												${invoice.getQuantity(track.id)} in cart
											</div>
											<div class="input-group quantity-input">
												<input type="hidden" name="track" value="${track.id}"/>
												<input class="form-control" name="quantity" value="1">
													<span class="input-group-btn">
														<button class="btn btn-success" name="command" value="add" type="submit">Add</button>
														<button class="btn btn-danger" name="command" value="remove" type="submit">Remove</button>
													</span>
											</div>
											<div>
												<b>$${track.price}</b>
											</div>
										</form>
									</div>
								</div>
							</c:forEach>							
						</c:otherwise>
					</c:choose>			
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>