package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		if (film == null) {
			System.out.println("is null");
		}
		System.out.println(film);
		for (Actor actor : film.getActors()) {
			System.out.println(actor);
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean keepGoing = true;
		while (keepGoing) {
			System.out.println("Welcome to the Video DataBase!");
			System.out.println("What would you like to search for?");
			System.out.println();
			System.out.println("1.) Search for a movie by its ID number.");
			System.out.println("2.) Search for a movie by a key word. (Such as thrilling, academy etc.)");
			System.out.println("3.) Exit.");
			String userIn = input.nextLine();

			switch (userIn.toLowerCase()) {
			case "1":
			case "one":
			case "by id":
			case "id":
			case "id number":
			case "by id number":
				searchByID(input);
				System.out.println();
				break;
			case "by key word":
			case "word":
			case "2":
			case "two":
				searchByWord(input);
				System.out.println();
				break;
			case "exit":
			case "quit":
			case "3":
			case "three":
				keepGoing = false;
				break;
			default:
				System.out.println("That is not a valid input please pick a option that is displayed on the screen.");
				break;
			}
		}
	}

	private void searchByID(Scanner input) {
		boolean keepGoing = true;
		Film userFilm = null;
		while (keepGoing) {
			System.out.println("What is the ID of the movie you wish to view?");
			String userIn = input.nextLine();

			try {
				int movieId = Integer.parseInt(userIn);
				userFilm = db.findFilmById(movieId);
				displayMovieId(userFilm, movieId, input);
				keepGoing = false;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid whole number for the ID, no words please.");
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
	}

	private void displayMovieId(Film userFilm, int key, Scanner input) {
		boolean keepGoing = true;
		if (userFilm == null) {
			System.out.println("I am sorry there is no movie with the id of " + key + " in our data base.");
		} else {
			while (keepGoing) {
				System.out.println("The movie information for movie ID: " + key + " is as follows: ");
				System.out.println("Title: " + userFilm.getTitle());
				System.out.println("Rating: " + userFilm.getRating());
				System.out.println("Release Year: " + userFilm.getReleaseYear());
				System.out.println("Description: " + userFilm.getDescription());
				System.out.println("Languages available: " + userFilm.getLanguage());
				System.out.println();
				System.out.println("Would you like to see a list of the actors?");
				String userIn = input.nextLine();

				switch (userIn.toLowerCase()) {
				case "yes":
				case "y":
					displayActors(userFilm, input);
					keepGoing = false;
					break;
				case "n":
				case "no":
					keepGoing = false;
					break;
				default:
					System.out.println("That is not a valid input please try again.");
				}
			}
		}
	}

	private void displayActors(Film userFilm, Scanner input) {
		int count = 1;
		for (Actor actor : userFilm.getActors()) {
			System.out.println(count + ": " + actor.getFirstName() + ", " + actor.getLastName());
			count++;
		}
		System.out.println("[ENTER TO CONTINUE]");
		input.nextLine();
	}

	private void searchByWord(Scanner input) {
		List<Film> films = new ArrayList<>();
		boolean keepGoing = true;
		while (keepGoing) {
			System.out.println("What key word would you like to look for?");
			String key = input.nextLine();
			try {
				films = db.findFilmByWord(key);
				keepGoing = false;
				displayMovieWord(films, key, input);
			} catch (SQLException e) {
				System.out.println("That is not a valid input, please try again.");
			}
		}
	}

	private void displayMovieWord(List<Film> userFilms, String key, Scanner input) {
		if (userFilms.size() <= 0) {
			System.out.println("I am sorry, there is no movie with the search term " + key + " in our data base.");
		} else {
			boolean keepGoing = true;
			System.out.println("There are a total of " + userFilms.size() + " movies in our data base matching that key word.");
			System.out.println("How many would you like to see?");
			System.out.println();
			System.out.println(userFilms.size() + " or all of them.");
			System.out.println(userFilms.size() / 2);
			System.out.println(userFilms.size() / 4);
			while (keepGoing) {
				for (Film userFilm : userFilms) {
					System.out.println("The movie information for the search term: " + key + " is as follows: ");
					System.out.println("Title: " + userFilm.getTitle());
					System.out.println("Rating: " + userFilm.getRating());
					System.out.println("Release Year: " + userFilm.getReleaseYear());
					System.out.println("Description: " + userFilm.getDescription());
					System.out.println("Languages available: " + userFilm.getLanguage());
					System.out.println();
					System.out.println("Would you like to see a list of the actors?");
					String userIn = input.nextLine();

					switch (userIn.toLowerCase()) {
					case "yes":
					case "y":
						displayActors(userFilm, input);
						keepGoing = false;
						break;
					case "n":
					case "no":
						keepGoing = false;
						break;
					default:
						System.out.println("That is not a valid input please try again.");
					}
				}
			}
		}
	}
}
