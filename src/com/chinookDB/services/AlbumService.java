package com.chinookDB.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.chinookDB.ApplicationDAO;
import com.chinookDB.beans.Album;

public class AlbumService {

	private static AlbumService instance;
	private AlbumService(){}
	
	public static AlbumService getInstance(){
		if(instance == null)
			instance = new AlbumService();
		
		return instance;
	}
	
//	public void add(int albumId, String title, int artistId){
//		if(albumExists(albumId, artistId)){
//			String sql = "UPDATE album SET albumId = albumId + ? " +
//					"where title = ? and " + 
//					"artistId = ?;";
//			
//			updateAlbum(sql, albumId, title, artistId);
//			return;
//		}
//	
//		insertAlbum(albumId, title, artistId);
//	}
//	
//	private void updateAlbum(String sql, int albumId, String title, int artistId){
//		ApplicationDAO dao = ApplicationDAO.getInstance();
//		
//		Connection connection = null;
//
//		try{
//			connection = dao.getConnection();
//			
//			PreparedStatement statement = connection.prepareStatement(sql);
//
//			statement.setInt(1, albumId);
//			statement.setString(2, title);
//			statement.setInt(3, artistId);
//			
//			statement.execute();
//			
//		}catch(SQLException e){
//			e.printStackTrace();
//		}finally{
//			dao.closeConnection(connection);
//		}				
//	}
//	
//	private boolean albumExists(int albumId, int artistId){
//		String sql = "SELECT count(*) from invoiceline where TrackId = ? and invoiceId = (select invoiceId from invoice where customerId = ? ORDER BY invoicedate desc LIMIT 1);";
//		
//		ApplicationDAO dao = ApplicationDAO.getInstance();
//		
//		Connection connection = null;
//		ResultSet rs = null;
//
//		try{
//			connection = dao.getConnection();
//			
//			PreparedStatement statement = connection.prepareStatement(sql);
//
//			statement.setInt(1, trackId);
//			statement.setInt(2, customerId);
//			
//			rs = statement.executeQuery();
//			
//			if(rs.next())
//				return rs.getInt(1) > 0;
//			
//		}catch(SQLException e){
//			e.printStackTrace();
//		}finally{
//			if(rs != null){
//				try{
//					rs.close();
//				}catch(SQLException e){}
//			}
//			dao.closeConnection(connection);
//		}			
//		
//		return false;
//	}
//	private void insertAlbum(int albumId, String title, int artistId){
//		String sql = "INSERT INTO album (albumId, title, artistId)";
//
//		ApplicationDAO dao = ApplicationDAO.getInstance();
//		
//		Connection connection = null;
//		ResultSet rs = null;
//
//		try{
//			connection = dao.getConnection();
//			
//			//Get Album ID first
//			PreparedStatement statement = connection.prepareStatement("(SELECT MAX(albumId) from album)");
//			rs = statement.executeQuery();
//			
//			int id = 0;
//			if(rs.next()){
//				id = rs.getInt(1) + 1;
//			}
//			
//			statement = connection.prepareStatement(sql);
//
//			statement.setInt(1, albumId);
//			statement.setString(2, title);
//			statement.setInt(3, artistId);
//			
//			
//			statement.execute();
//			
//		}catch(SQLException e){
//			e.printStackTrace();
//		}finally{
//			if(rs != null){
//				try{
//					rs.close();
//				}catch(SQLException e){}
//			}			
//			dao.closeConnection(connection);
//		}		
//	}
	
	public List<Album> getAlbums(){
		String sql = "SELECT album.AlbumId \"AlbumId\", album.Title \"Title\", artist.Name \"Artist\" from album, artist where album.ArtistId = artist.ArtistId ORDER BY LOWER(album.Title)";

		ApplicationDAO dao = ApplicationDAO.getInstance();
		
		Connection connection = null;
		ResultSet rs = null;

		try{
			connection = dao.getConnection();
			
			//Get Album ID first
			PreparedStatement statement = connection.prepareStatement(sql);
			rs = statement.executeQuery();
			
			List<Album> albums = new ArrayList<Album>();
			
			while(rs.next()){
				albums.add(Album.fromResultSet(rs));
			}
			
			return albums;
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
}
