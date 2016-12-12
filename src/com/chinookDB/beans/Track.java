package com.chinookDB.beans;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Track {
	public Track(){
		id = -1;
	}
	public Track(int id){
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
	 * @return the album
	 */
	public String getAlbum() {
		return album;
	}
	/**
	 * @return the composer
	 */
	public String getComposer() {
		return composer;
	}
	/**
	 * @return the artist
	 */
	public String getArtist() {
		return artist;
	}
	/**
	 * @return the genre
	 */
	public String getGenre() {
		return genre;
	}
	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @param album the album to set
	 */
	public void setAlbum(String album) {
		this.album = album;
	}
	/**
	 * @param composer the composer to set
	 */
	public void setComposer(String composer) {
		this.composer = composer;
	}
	/**
	 * @param artist the artist to set
	 */
	public void setArtist(String artist) {
		this.artist = artist;
	}
	/**
	 * @param genre the genre to set
	 */
	public void setGenre(String genre) {
		this.genre = genre;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	public static Track fromResultSet(ResultSet rs) throws SQLException{
		int id = rs.getInt("ID");
		String title = rs.getString("TITLE");
		String composer = rs.getString("COMPOSER");
		String artist = rs.getString("ARTIST");
		String genre = rs.getString("GENRE");
		String album = rs.getString("ALBUM");
		double price = rs.getDouble("PRICE");
		
		Track track = new Track(id);
		track.setTitle(title);
		track.setComposer(composer);
		track.setArtist(artist);
		track.setAlbum(album);
		track.setGenre(genre);
		track.setPrice(price);
		
		return track;		
	}
	
	int id;
	String title;
	String album;
	String composer;
	String artist;
	String genre;
	double price;
}
