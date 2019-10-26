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
		String user = "student";
		String password = "student";
		Connection conn = DriverManager.getConnection(URL, user, password);

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
		String user = "student";
		String password = "student";
		Connection conn = DriverManager.getConnection(URL, user, password);

		List<Film> filmsWanted = new ArrayList<>();
		String sqlComm = "select film.*, language.name from film join language on film.language_id = language.id where title like \"%"
				+ key + "%\" or description like \"%" + key + "%\"";

		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
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
			filmWanted = null;
		}
		rs.close();
		ps.close();
		conn.close();
		return filmsWanted;

	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
		String user = "student";
		String password = "student";
		Connection conn = DriverManager.getConnection(URL, user, password);

		List<Actor> actors = new ArrayList<>();

		String sqlComm = "SELECT actor.id, actor.first_name, actor.last_name FROM actor "
				+ "JOIN film_actor ON actor.id = film_actor.actor_id "
				+ "JOIN film ON film.id = film_actor.film_id WHERE film.id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ps.setInt(1, filmId);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Actor actor = new Actor();
			actor.setActorID(rs.getInt("id"));
			actor.setFirstName(rs.getString("first_name"));
			actor.setLastName(rs.getString("last_name"));
			actors.add(actor);
			actor = null;
		}
		rs.close();
		ps.close();
		conn.close();
		return actors;
	}

	@Override
	public Actor findActorById(int actorId) throws SQLException {
		String user = "student";
		String password = "student";
		Connection conn = DriverManager.getConnection(URL, user, password);

		Actor actorWanted = null;
		String sqlComm = "SELECT * FROM actor WHERE id = ?";

		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ps.setInt(1, actorId);
		ResultSet rs = ps.executeQuery();
		if (rs.next()) {
			actorWanted = new Actor();
			actorWanted.setActorID(rs.getInt("id"));
			actorWanted.setFirstName(rs.getString("first_name"));
			actorWanted.setLastName(rs.getString("last_name"));
			actorWanted.setFilms(findFilmByActorId(actorId));
		}
		rs.close();
		ps.close();
		conn.close();

		return actorWanted;
	}
	
	@Override
	public List<Film> findFilmByActorId(int actorId) throws SQLException {
		String user = "student";
		String password = "student";
		Connection conn = DriverManager.getConnection(URL, user, password);
		
		List<Film> films = new ArrayList<>();
		String sqlComm = "Select film.*, language.name from film join film_actor ON film_actor.film_id = film.id "
				+ "join language on film.language_id = language.id "
				+ "JOIN actor ON film_actor.actor_id = actor.id  WHERE actor.id = ?";
		PreparedStatement ps = conn.prepareStatement(sqlComm);
		ps.setInt(1, actorId);
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
			films.add(filmWanted);
			filmWanted = null;
		}
		
		rs.close();
		ps.close();
		conn.close();
		
		return films;
	}

}
