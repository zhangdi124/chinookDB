package com.chinookDB.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.chinookDB.services.TrackService;
import com.chinookDB.services.queries.TrackQuery;

public class QueryTracksTagSupport extends SimpleTagSupport {
	public void setVar(String var) {
		this.var = var;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public void setSort(String sort){
		this.sort = sort;
	}
	
	String var;
	String title;
	String artist;
	String genre;
	String sort;
	
	@Override
	public void doTag() throws JspException, IOException {
		PageContext pageContext =  (PageContext)getJspContext();
		
		TrackService service = TrackService.getInstance();
		TrackQuery query = new TrackQuery();
		
		query.setArtist(artist)
			.setTitle(title)
			.setGenre(genre)
			.setSort(sort);
		
		if(var == null)
			var = "tracks";
		
		pageContext.setAttribute(var, service.getTracks(query));
	}
}
