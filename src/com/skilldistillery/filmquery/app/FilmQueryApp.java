package com.skilldistillery.filmquery.app;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) throws SQLException {
		FilmQueryApp app = new FilmQueryApp();
		// app.test();
		app.launch();
	}

	private void test() throws SQLException {
		Film film = db.findFilmById(1);
		System.out.println(film);
	}

	private void launch() throws SQLException {
		Scanner input = new Scanner(System.in);
		startUserInterface(input);
		input.close();
	}

	private void startUserInterface(Scanner input) throws SQLException {

		boolean keepGoing = true;
		while (keepGoing) {

			printMenu();
			System.out.println("What would you like to do?");
			int userInput = input.nextInt();

			switch (userInput) {
			case 1:
				System.out.println("Please enter a film ID: ");
				userInput = input.nextInt();
				
				Film filmById = db.findFilmById(userInput);
				
				if (filmById == null) {
					System.out.println("That ID does not exist, please try again.");
				} else {

					System.out.println(filmById);

				}

				break;

			case 2:
				input.nextLine();
				System.out.println("Please enter a keyword: ");
				String userKeyword = input.nextLine();

				List<Film> films = db.findFilmsByKeyword(userKeyword);

				if (films.size() == 0) {
					System.out.println("No films found");
				} else {

					for (Film film : films) {
						System.out.println(film);
					}
					System.out.println(films.size());
				}

				break;

			case 3:
				System.out.println("Exiting the app.");
				keepGoing = false;
				break;
			}

		}
	}

	private void printMenu() {
		System.out.println("");
		System.out.println("===================== Film Query ====================");
		System.out.println("=                                                   =");
		System.out.println("=            1. View film by ID                     =");
		System.out.println("=            2. View film by key word               =");
		System.out.println("=            3. Exit                                =");
		System.out.println("=                                                   =");
		System.out.println("=====================================================");
		System.out.println("");

	}

}
