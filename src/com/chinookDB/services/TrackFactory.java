package com.chinookDB.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.chinookDB.ApplicationDAO;
import com.chinookDB.beans.Customer;
import com.chinookDB.beans.Track;

public class TrackFactory {
	private static TrackFactory instance;
	private TrackFactory(){}
	
	public static TrackFactory getInstance(){
		if(instance == null)
			instance = new TrackFactory();
		
		return instance;
	}
	
	public List<Track> getAllTracks(){
		String sql =
				"select " +
					"t.TrackId as \"ID\", "+
				    "t.Name as \"TITLE\", "+
				    "album.Title as \"ALBUM\", "+
				    "t.Composer as \"COMPOSER\", "+
				    "artist.Name as \"ARTIST\", "+
				    "g.Name as \"GENRE\", "+
				    "t.UnitPrice as \"PRICE\" "+
				"from track t, genre g, album, artist "+
				"where t.GenreId = g.GenreId "+
				"and t.AlbumId = album.AlbumId "+
				"and album.ArtistId = artist.ArtistId "+
				"ORDER BY t.Name";
		
		return get(sql);
	}
	
	private List<Track> get(String sql){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
		
		List<Track> tracks = new ArrayList<Track>();

		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			rs = statement.executeQuery();
			
			while(rs.next()){
				Track track = parseRowAsTrack(rs);
				tracks.add(track);
			}
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException e){}
			}
			dao.closeConnection(connection);
		}
		
		return tracks;	
	}
	
	private Track parseRowAsTrack(ResultSet rs){
		try{
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
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
}
