package com.chinookDB.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Album {
	public Album(int id){
		this.id = id;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	
	public String getTruncatedTitle(){
		if(this.title.length() < 30)
			return this.title;
		
		return this.title.substring(0, 30) + "...";
	}
	
	public static Album fromResultSet(ResultSet rs) throws SQLException{
		Album album = new Album(rs.getInt("AlbumId"));
		album.setArtist(rs.getString("Artist"));
		album.setTitle(rs.getString("Title"));
		
		return album;
	}
	int id;
	String title;
	String artist;
}
