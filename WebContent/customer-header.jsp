<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/chinook-services.tld" prefix="services" %>

<jsp:include page="./SessionVerification.jsp"/>
<services:getCustomer/>
<services:getGenres/>

<script>
	(function(document){
		document.addEventListener('DOMContentLoaded', function(){
			var clearSearchButton = document.getElementById('clearSearchBtn');
			
			
			clearSearchButton.addEventListener('click', function(){
				window.location.href = window.location.href.replace(/\?.*$/, '');
			});
		});
	})(document)
</script>
<header>
	<nav class="navbar navbar-default">
 			<div class="container-fluid">
   			<div class="navbar-header">
     				<a class="navbar-brand" href="#">Chinook Music</a>   				
  				</div>
				<form id="searchForm" class="navbar-form navbar-left" role="search">
			  		<div class="form-group">
			  			<label for="title">Track/Album Title</label>
			  			<input name="title" id="title" class="form-control" value="${param.title}"/>
		  			</div>
		  			<div class="form-group">
			  			<label for="artist">Artist</label>
			  			<input name="artist" id="artist" class="form-control" value="${param.artist}"/>
		  			</div>
		  			<div class="form-group">
			  			<label for="genre">Genre</label>
				    	<select name="genre" id="genre" class="form-control">
				    		<option value="">--Select Genre--</option>
				    		<c:forEach items="${genres}" var="genre">
				    			<c:set var="selected" value="${genre.toLowerCase() == param.genre}"/>
				    			<c:choose>
				    				<c:when test="${selected}">
						    			<option value="${genre.toLowerCase()}" selected>${genre}</option>
				    				</c:when>
				    				<c:otherwise>
						    			<option value="${genre.toLowerCase()}">${genre}</option>
				    				</c:otherwise>
				    			</c:choose>
				    		</c:forEach>
				    	</select>
				  	</div>
				  	<div class="form-group">
					  	<button type="submit" class="btn btn-primary">Search</button>
					  	&nbsp;&nbsp;
					  	<button id="clearSearchBtn" type="button" class="btn btn-default">Clear</button>
				  	</div>
				</form>       				
			</div>		
 			<div class="container-fluid">
   			<div class="navbar-header">
     				<b>Logged in: ${customer.firstName} ${customer.lastName}</b>  
     				<a class="btn btn-danger btn-sm" href="/chinookDB/Logout.jsp">Logout</a>			
  				</div>
		</div> 				
		</nav>	
</header>