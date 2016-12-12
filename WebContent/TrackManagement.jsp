<%@page import="com.chinookDB.beans.Track"%>
<%@page import="com.chinookDB.services.TrackService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	if(request.getParameter("command") != null){
		String command = request.getParameter("command");
		
		TrackService service = TrackService.getInstance();
		
		if(command.equals("add")){
			Track track = new Track();
			track.setAlbum(request.getParameter("album"));
			track.setComposer(request.getParameter("composer"));
			track.setGenre(request.getParameter("genre"));
			
			if(request.getParameter("price") != null && !request.getParameter("price").trim().isEmpty())
				track.setPrice(Double.parseDouble(request.getParameter("price")));
			
			track.setTitle(request.getParameter("trackTitle"));

			service.addTrack(track);
			
			session.setAttribute("message", String.format("Added new track: %s", track.getTitle()));
			response.sendRedirect("/chinookDB/admin.jsp");
		}else if(command.contains("remove")){
			int trackId = Integer.parseInt(command.split(":")[1]);
			
			service.removeTrack(trackId);
			
			session.setAttribute("message", String.format("Removed Track ID #%d", trackId));
			response.sendRedirect("/chinookDB/admin.jsp");			
		}
	}
%>