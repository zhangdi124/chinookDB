<%@page import="com.chinookDB.services.TrackService"%>
<%@page import="com.chinookDB.beans.Track"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="/WEB-INF/chinook-services.tld" prefix="services" %>

<services:queryTracks sort="TrackId desc"/>
<services:getGenres/>
<services:getAlbums/>

<!DOCTYPE html>
<html>
	<jsp:include page="./GlobalPageHead.jsp">
		<jsp:param value="Admin Panel" name="pageTitle"/>
	</jsp:include>
	<body>
		<services:message/>
		<div class="container">
			<h3>Tracks Table</h3>
			<form action="/chinookDB/TrackManagement.jsp">
			<table class="table table-condensed table-bordered table-striped">
				<thead>
					<tr class="primary">
						<th>
							ID
						</th>
						<th>
							Title
						</th>
						<th>
							Composer
						</th>
						<th>
							Album
						</th>
						<th>
							Genre
						</th>
						<th>
							Price
						</th>
						<th>
						</th>
					</tr>
				</thead>
				<tbody>
					<tr class="info">
						<td>
						</td>
						<td>
							<input type="text" name="trackTitle"/>
						</td>
						<td>
							<input type="text" name="composer"/>
						</td>
						<td>
							<select name="album">
								<c:forEach items="${albums}" var="album">
									<option value="${album.title}">${album.truncatedTitle}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<select name="genre">
								<c:forEach items="${genres}" var="genre">
									<option>${genre}</option>
								</c:forEach>
							</select>
						</td>
						<td>
							<input type="number" name="price"/>
						</td>
						<td>
							<button type="submit" name="command" value="add" class="btn btn-success">Add</button>
						</td>
					</tr>
					<c:forEach items="${tracks}" var="track">
						<tr>
							<td>${track.id}</td>
							<td>${track.title}</td>
							<td>${track.composer}</td>
							<td title="${track.album}">
								<c:choose>
									<c:when test="${fn:length(track.album) > 30}">
										${fn:substring(track.album, 0, 30)}...
									</c:when>
									<c:otherwise>
										${track.album}
									</c:otherwise>
								</c:choose>
							</td>
							<td>${track.genre}</td>
							<td>${track.price}</td>
							<td>
								<div class="btn-group" role="group">
									<button name="command" value="remove:${track.id}" type="submit" class="btn btn-danger">Remove</button>
								</div>						
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</form>
		</div>
	</body>
</html>