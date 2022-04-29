package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {

	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	
	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
  @Override
  public Film findFilmById(int filmId) throws SQLException{
	  
	  Film film = null; 
	  
	  //access to database
	  String user = "student";
	  String pass = "student";
	  Connection conn = DriverManager.getConnection(URL, user, pass);

	  //SQL statement what the get from the database and where to get it from
	  String sql = "SELECT id, title FROM film WHERE id = ?";
	  
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1, filmId);
	  ResultSet filmResult = stmt.executeQuery();
	  
	 if (filmResult.next()) {
		  film = new Film();
		  
		  film.setId(filmResult.getInt(1));
		  film.setTitle(filmResult.getString(2));
		  film.setDescription(filmResult.getString(3));
		  film.setReleaseYear(filmResult.getInt(4));
		  film.setLanguageId(filmResult.getInt(5));
		  film.setRentalDuration(filmResult.getInt(6));
		  film.setRentalRate(filmResult.getDouble(7));
		  film.setLenth(filmResult.getInt(8));
		  film.setReplacementCost(filmResult.getDouble(9));
		  film.setRating(filmResult.getString(10));
		  film.setSpecialFeatures(filmResult.getString(11));
		  //film.setActor(null);
	  }  
   return film; 
  }

  
  public Actor findActorById(int actorId) throws SQLException {
	  
	  Actor actor = null; 
	  
	  String user = "student";
	  String pass = "student";
	  Connection conn = DriverManager.getConnection(URL, user, pass);

	  String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
	  
	  PreparedStatement stmt = conn.prepareStatement(sql);
	  stmt.setInt(1, actorId);
	  ResultSet actorResult = stmt.executeQuery();
	  if (actorResult.next()) {
	    actor = new Actor(); // Create the object
	    // Here is our mapping of query columns to our object fields:
	    actor.setId(actorResult.getInt(1));
	    actor.setFirstName(actorResult.getString(2));
	    actor.setLastName(actorResult.getString(3));
	    //actor.setFilms(findFilmsByActorId(actorId)); // An Actor has Films
	  }
	  //...
	  return actor;
	}
  
  
  
  
  
}
