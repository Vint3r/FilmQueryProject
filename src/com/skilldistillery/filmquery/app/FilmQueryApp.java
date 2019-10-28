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
			System.out.println("3.) Search for a actor by their ID number.");
			System.out.println("4.) Exit.");
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
			case "actor":
			case "actor id":
			case "three":
			case "3":
				searchActorId(input);
				System.out.println();
				break;
			case "exit":
			case "quit":
			case "4":
			case "four":
				keepGoing = false;
				break;
			default:
				System.out.println("That is not a valid input please pick a option that is displayed on the screen.");
				break;
			}
		}
	}

	private void searchActorId(Scanner input) {
		boolean keepGoing = true;
		Actor actor = null;
		while (keepGoing) {
			System.out.println("What is the ID of the actor you wish to view?");
			String userIn = input.nextLine();

			try {
				int actorId = Integer.parseInt(userIn);
				actor = db.findActorById(actorId);
				displayActor(actor, input, actorId);
				keepGoing = false;
			} catch (NumberFormatException e) {
				System.out.println("Please enter a valid whole number for the ID, no words please.");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void displayActor(Actor actor, Scanner input, int actorId) {
		if (actor == null) {
			System.out.println(
					"I am sorry, we don't have a actor with " + actorId + " as their ID number in our data base.");
		} else {
			boolean keepGoing = true;
			while (keepGoing) {
				System.out.println();
				System.out.println("The information on actor ID: " + actorId + " is as follows: ");
				System.out.println("Actor ID: " + actor.getActorID());
				System.out.println("Actor First Name: " + actor.getFirstName());
				System.out.println("Actor Last Name: " + actor.getLastName());
				System.out.println(actor.getFirstName() + " has appeared in " + actor.getFilms().size() + " movies.");
				System.out.println();
				System.out.println("Would you like to: ");
				System.out.println("1.) View all movies this actor has appeared in.");
				System.out.println("2.) Return to main menu.");
				String userIn = input.nextLine();

				switch (userIn.toLowerCase()) {
				case "1":
				case "one":
				case "list movies":
				case "movies":
					displayMovieWord(actor.getFilms(), null, input);
					break;
				case "2":
				case "two":
				case "main menu":
				case "quit":
				case "exit":
				case "menu":
					keepGoing = false;
					break;
				default:
					System.out.println("That is not a valid input, please try again.");
					break;
				}
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
			outer: while (keepGoing) {

				System.out.println("The movie information for the search term: " + key + " is as follows: ");
				System.out.println("Title: " + userFilm.getTitle());
				System.out.println("Rating: " + userFilm.getRating());
				System.out.println("Release Year: " + userFilm.getReleaseYear());
				System.out.println("Description: " + userFilm.getDescription());
				System.out.println("Languages available: " + userFilm.getLanguage());
				System.out.println();
				System.out.println("1.) Display all actors.");
				System.out.println("2.) Display all movie information. (includes actors)");
				System.out.println("3.) Display all movie information. (excluding actors)");
				System.out.println("4.) Exit to main menu");
				String userIn = input.nextLine();

				switch (userIn.toLowerCase()) {
				case "actors":
				case "1":
				case "all actors":
				case "one":
					displayActorsForFilms(userFilm, input);
					break;
				case "2":
				case "two":
				case "actor and movie":
					boolean wantsActors = true;
					displayEverything(wantsActors, userFilm, input);
					break;
				case "3":
				case "three":
				case "just movie":
				case "movie":
					wantsActors = false;
					displayEverything(wantsActors, userFilm, input);
					break;
				case "quit":
				case "menu":
				case "main menu":
				case "4":
				case "four":
					userFilm = null;
					keepGoing = false;
					break outer;
				default:
					System.out.println("That is not a valid input please try again.");
				}
			}
		}
	}

	private void displayActorsForFilms(Film userFilm, Scanner input) {
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
				key = "";
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
			outer: while (keepGoing) {
				for (Film userFilm : userFilms) {
					boolean stay = true;
					inner: while (stay) {
						if (key == null) {
							System.out.println("The movie information is as follows: ");
						} else {
							System.out.println("The movie information for the search term: " + key + " is as follows: ");
						}
						System.out.println("Title: " + userFilm.getTitle());
						System.out.println("Rating: " + userFilm.getRating());
						System.out.println("Release Year: " + userFilm.getReleaseYear());
						System.out.println("Description: " + userFilm.getDescription());
						System.out.println("Languages available: " + userFilm.getLanguage());
						System.out.println();
						System.out.println("1.) Display all actors.");
						System.out.println("2.) Display all movie information. (includes actors)");
						System.out.println("3.) Display all movie information. (excluding actors)");
						System.out.println("4.) Display next movie");
						System.out.println("5.) Exit to main menu");
						String userIn = input.nextLine();

						switch (userIn.toLowerCase()) {
						case "actors":
						case "1":
						case "all actors":
						case "one":
							displayActorsForFilms(userFilm, input);
							break;
						case "2":
						case "two":
						case "actor and movie":
							boolean wantsActors = true;
							displayEverything(wantsActors, userFilm, input);
							break;
						case "3":
						case "three":
						case "just movie":
						case "movie":
							wantsActors = false;
							displayEverything(wantsActors, userFilm, input);
							break;
						case "4":
						case "four":
						case "next":
							break inner;
						case "quit":
						case "menu":
						case "main menu":
						case "5":
						case "five":
							userFilms = null;
							keepGoing = false;
							break outer;
						default:
							System.out.println("That is not a valid input please try again.");
						}
					}
				}
			}
		}
	}

	private void displayEverything(boolean wantsActors, Film userFilm, Scanner input) {
		System.out.println();
		System.out.println("==========================================================");
		System.out.println();
		System.out.println("Title: " + userFilm.getTitle());
		System.out.println("Rating: " + userFilm.getRating());
		System.out.println("Description: " + userFilm.getDescription());
		System.out.println("Languages available: " + userFilm.getLanguage());
		System.out.println("Release Year: " + userFilm.getReleaseYear());
		System.out.println("Length of movie: " + userFilm.getLength());
		System.out.println("Special features available: " + userFilm.getSpecialFeat());
		System.out.println("Rental Duration is: " + userFilm.getRentDuration());
		System.out.println("Rental Rate is: " + userFilm.getRentRate());
		System.out.println("Cost to replace movie: " + userFilm.getReplaceCost());
		System.out.println("Movie id: " + userFilm.getId());
		if (wantsActors) {
			displayActorsForFilms(userFilm, input);
		} else {
			System.out.println("[ENTER TO CONTINUE]");
			input.nextLine();
		}
	}
}
