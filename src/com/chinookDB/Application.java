package com.chinookDB;

import java.util.List;

import com.chinookDB.beans.Customer;
import com.chinookDB.beans.Track;
import com.chinookDB.services.TrackFactory;

public class Application {

	public static void main(String[] args) {
		CustomerFactory cf = CustomerFactory.getInstance();
		
		List<Customer> customers = cf.getCustomersInCity("salt");//cf.getAllCustomers();
		
		for(Customer c : customers){
			System.out.println(c.getFirstName() + " " + c.getLastName());
		}
		
		TrackFactory tf = TrackFactory.getInstance();
		List<Track> tracks = tf.getAllTracks();
		
		for(Track track : tracks){
			System.out.printf("%s - %s", track.getTitle(), track.getArtist());
		}
	}

}
