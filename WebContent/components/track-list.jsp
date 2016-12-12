<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<%= request.getParameter("tracks") %>
<c:forEach items="${param.tracks}" var="track" varStatus="status">
	<hr/>
	<div class="row">
		<div class="col-xs-6">
			<h3 class="track-title">${track.title}</h3>
			<h4><small><b>Artist:</b>&nbsp;${track.artist}</small></h4>
			<h4><small><b>Album:</b>&nbsp;${track.album}</small></h4>
			<h4><small><b>Genre:</b>&nbsp;${track.genre}</small></h4>
		</div>
		<div class="col-xs-6">
			<form>
				<div class="input-group quantity-input">
					<input class="form-control" value="1">
						<span class="input-group-btn">
							<button class="btn btn-success" type="button">Add to Cart</button>
						</span>
				</div>
				<div>
					<b>$${track.price}</b>
				</div>
			</form>
		</div>
	</div>
</c:forEach>	