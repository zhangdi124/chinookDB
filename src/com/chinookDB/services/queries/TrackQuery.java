package com.chinookDB.services.queries;

public final class TrackQuery extends QueryBase{
	public String getArtist() {
		return convertToParameter(artist);
	}
	public TrackQuery setArtist(String artist) {
		this.artist = artist;
		return this;
	}
	public String getTitle() {
		return convertToParameter(title);
	}
	public TrackQuery setTitle(String title) {
		this.title = title;
		return this;
	}
	public String getGenre() {
		return convertToParameter(genre);
	}
	public TrackQuery setGenre(String genre) {
		this.genre = genre;
		return this;
	}
	
	public String getSort(){
		return sort;
	}
	
	public TrackQuery setSort(String sort){
		this.sort = sort;
		return this;
	}
	private String artist;
	private String title;
	private String genre;
	private String sort;
	
}