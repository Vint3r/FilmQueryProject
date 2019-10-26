package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false";

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) throws SQLException {
		Connection conn = setUpConn();
		Film filmWanted = null;
		String sqlComm = "SELECT film.*, language.name FROM film "
				+ "JOIN language ON language.id = film.language_id WHERE film.id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			filmWanted = new Film();
			filmWanted.setId(rs.getInt("id"));
			filmWanted.setTitle(rs.getString("title"));
			filmWanted.setDescription(rs.getString("description"));
			filmWanted.setReleaseYear(rs.getInt("release_year"));
			filmWanted.setLanguage(rs.getString("name"));
			filmWanted.setRentDuration(rs.getInt("rental_duration"));
			filmWanted.setRentRate(rs.getDouble("rental_rate"));
			filmWanted.setLength(rs.getInt("length"));
			filmWanted.setReplaceCost(rs.getDouble("replacement_cost"));
			filmWanted.setRating(rs.getString("rating"));
			filmWanted.setSpecialFeat(rs.getString("special_features"));
			filmWanted.setActors(findActorsByFilmId(filmId));
			return filmWanted;
		}
		return filmWanted;
	}
	
	public List<Film> findFilmByWord(String key) throws SQLException {
		Connection conn = setUpConn();
		List<Film> filmsWanted = new ArrayList<>();
		String sqlComm = "select film.*, language.name from film join language on film.language_id = language.id where title like \"%" + key + "%\" or description like \"%" + key + "%\"";
		
		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ResultSet rs = ps.executeQuery();
		while(rs.next()) {
			Film filmWanted = new Film();
			filmWanted.setId(rs.getInt("id"));
			filmWanted.setTitle(rs.getString("title"));
			filmWanted.setDescription(rs.getString("description"));
			filmWanted.setReleaseYear(rs.getInt("release_year"));
			filmWanted.setLanguage(rs.getString("name"));
			filmWanted.setRentDuration(rs.getInt("rental_duration"));
			filmWanted.setRentRate(rs.getDouble("rental_rate"));
			filmWanted.setLength(rs.getInt("length"));
			filmWanted.setReplaceCost(rs.getDouble("replacement_cost"));
			filmWanted.setRating(rs.getString("rating"));
			filmWanted.setSpecialFeat(rs.getString("special_features"));
			filmWanted.setActors(findActorsByFilmId(filmWanted.getId()));
			filmsWanted.add(filmWanted);
		}
		return filmsWanted;
		
		
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		Connection conn = setUpConn();
		Actor actorWanted = null;
		String sqlComm = "SELECT * FROM actor WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ps.setInt(1, actorId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			actorWanted = new Actor(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
			return actorWanted;
		}
		System.out.println("Here");
		rs.close();
		ps.close();
		conn.close();

		return actorWanted;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		List<Actor> actors = new ArrayList<>();
		Connection conn = setUpConn();
		String sqlComm = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";
		
		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next()) {
			Actor actor = new Actor();
			actor.setActorID(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actors.add(actor);
			actor = null;
		}
		return actors;
	}

	public Connection setUpConn() {
		String user = "student";
		String password = "student";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

}
