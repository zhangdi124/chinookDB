<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="com.chinookDB.services.TrackFactory"%>
<%@page import="com.chinookDB.beans.Track"%>
<%@page import="java.util.List"%>
<%@page import="com.chinookDB.services.InfoLookupService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
	InfoLookupService infoLookup = InfoLookupService.getInstance();

	pageContext.setAttribute("genres", infoLookup.lookupGenres());
	
	TrackFactory tf = TrackFactory.getInstance();
	List<Track> tracks = tf.getAllTracks();
	List<Track> filteredTracks = new ArrayList<Track>();
	
	String genre = request.getParameter("genre");
	String artist = request.getParameter("artist");
	String title = request.getParameter("title");
	
	if(artist != null){
		if(artist.trim().isEmpty())
			artist = null;
		else
			artist = artist.toLowerCase().replaceAll("\\++", " ");
	}
	
	if(title != null){
		if(title.trim().isEmpty())
			title = null;
		else
			title = title.toLowerCase().replaceAll("\\++", " ");;
	}
	
	if(genre != null){
		if(genre.equals("0"))
			genre = null;
		else
			genre = genre.toLowerCase().replaceAll("\\++", " ");;
	}

	boolean hasSearchParameters = genre != null || artist != null || title != null;

	
	if(hasSearchParameters){
		for(Track track : tracks){
			String trackGenre = track.getGenre().toLowerCase();
			String trackArtist = track.getArtist().toLowerCase();
			String trackTitle = track.getTitle().toLowerCase();
			String album = track.getAlbum().toLowerCase();
			
			if( 		(genre == null || trackGenre.equals(genre))
					&& (artist == null || trackArtist.contains(artist)) 
					&& (title == null || trackTitle.contains(title) || album.contains(title))){
				filteredTracks.add(track);
			}
		}		
	}

	pageContext.setAttribute("hasSearchParameters", hasSearchParameters);	
	pageContext.setAttribute("tracks", filteredTracks);
	pageContext.setAttribute("selectedGenre", genre);
	pageContext.setAttribute("selectedTitle", title);
	pageContext.setAttribute("selectedArtist", artist);
	
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8"/>
	<title>Chinook Products Search</title>
	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<body>
	<header>
		<nav class="navbar navbar-default">
  			<div class="container-fluid">
    			<div class="navbar-header">
      				<a class="navbar-brand" href="#">Chinook Music</a>   				
   				</div>
					<form class="navbar-form navbar-left" role="search">
				  		<div class="form-group">
				  			<label for="title">Track/Album Title</label>
				  			<input name="title" id="title" class="form-control" value="${selectedTitle}"/>
				  			<label for="artist">Artist</label>
				  			<input name="artist" id="artist" class="form-control" value="${selectedArtist}"/>
				  			<label for="genre">Genre</label>
					    	<select name="genre" id="genre" class="form-control">
					    		<option value="0">--Select Genre--</option>
					    		<c:forEach items="${genres}" var="genre">
					    			<c:set var="selected" value="${genre.toLowerCase() == selectedGenre}"/>
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
					  	<button type="submit" class="btn btn-default">Search</button>
					</form>       				
 			</div>			
 		</nav>
	</header>
	
	<main>
		<div class="container">
			<c:choose>
				<c:when test="${!hasSearchParameters}">
					<h1>Please select options for tracks you would like to view.</h1>
				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${fn:length(tracks) == 0}">
							<h1>We are sorry, no tracks could be found that match your search criteria.</h1>
						</c:when>
						<c:otherwise>
							<c:forEach items="${tracks}" var="track" varStatus="status">
								<c:if test="${status.index != 0}">
									<hr/>
								</c:if>
								<div class="row">
									<div class="col-xs-6">
										<h3>${track.title} <small>${track.artist}</small></h3>
										<h4>${track.album}</h4>
										<h4>${track.genre}</h4>
									</div>
									<div class="col-xs-6">
										<b>$${track.price}</b>
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