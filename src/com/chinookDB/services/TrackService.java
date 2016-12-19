package com.chinookDB.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chinookDB.ApplicationDAO;
import com.chinookDB.beans.Track;
import com.chinookDB.services.queries.TrackQuery;

public class TrackService {
	private static TrackService instance;
	private TrackService(){}
	
	public static TrackService getInstance(){
		if(instance == null)
			instance = new TrackService();
		
		return instance;
	}
	
	public void removeTrack(int trackId){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
	
		try{
			connection = dao.getConnection();
			
			String sql = "DELETE FROM track where TrackId = ?";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setInt(1, trackId);
			
			statement.execute();
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			dao.closeConnection(connection);
		}				
	}
	public void addTrack(Track track){
		if(track == null)
			return;
		
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
	
		try{
			connection = dao.getConnection();
			
			String sql = "SELECT MAX(TrackId) from track;";
			PreparedStatement statement = connection.prepareStatement(sql);
			
			rs = statement.executeQuery();
			
			int id = 0;
			
			if(rs.next())
				id = rs.getInt(1) + 1;
			
			
			sql = "INSERT INTO track (TrackId, Name, AlbumId, MediaTypeId, GenreId, Composer, Milliseconds, Bytes, UnitPrice) " +
					"VALUES (?,?,(Select AlbumId from album where album.Title = ?), 1,(SELECT GenreId from genre where genre.name = ? LIMIT 1), ?, 343719, 3000000, ?)";
			statement = connection.prepareStatement(sql);
			
			statement.setInt(1, id);
			statement.setString(2, track.getTitle());
			statement.setString(3, track.getAlbum());
			statement.setString(4, track.getGenre());
			statement.setString(5, track.getComposer());
			statement.setDouble(6, track.getPrice());
			
			statement.execute();
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
	}
	public List<Track> getTracks(){
		return getTracks(null);
	}
	
	public List<Track> getTracks(TrackQuery query){
		List<Track> tracks = new ArrayList<Track>();
		
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
		
		String artist = null;
		String title = null;
		String genre = null;
		
		if(query != null){
			artist = query.getArtist();
			title = query.getTitle();
			genre = query.getGenre();
		}
		
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
				"and album.ArtistId = artist.ArtistId ";

		if(artist != null)
			sql += "and LOWER(artist.Name) like ? ";
		
		if(title != null)
			sql += "and (LOWER(t.Name) LIKE ? OR LOWER(album.Title) LIKE ?) ";
		
		if(genre != null)
			sql += "and LOWER(g.Name) LIKE ? ";
			
		String sort = query.getSort();
		if(sort == null || sort.trim().isEmpty())
			sql += "ORDER BY LOWER(t.Name)";
		else
			sql += "ORDER BY " + sort;
		
		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			int index = 1;
			
			if(artist != null)
				statement.setString(index++, artist);
			
			if(title != null){
				statement.setString(index++, title);
				statement.setString(index++, title);
			}			
			
			if(genre != null)
				statement.setString(index, genre);
			
			rs = statement.executeQuery();
			
			while(rs.next()){
				tracks.add(Track.fromResultSet(rs));
			}
			
			return tracks;
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
		
		return Collections.emptyList();
	}
	
	public Track findTrackById(int id){
		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;
		
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
				"where t.TrackId = ? AND "+
				"t.GenreId = g.GenreId "+
				"and t.AlbumId = album.AlbumId "+
				"and album.ArtistId = artist.ArtistId LIMIT 1";

		
		try{
			connection = dao.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setInt(1, id);
			rs = statement.executeQuery();
			
			if(rs.next())
				return Track.fromResultSet(rs);

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
		
		return null;		
	}
}
