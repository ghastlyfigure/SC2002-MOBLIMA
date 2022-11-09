package controllers;

import controllers.CinemaManager;
import controllers.CineplexManager;
import controllers.MovieManager;
import interfaces.Cinema;
import interfaces.Cineplex;
import interfaces.Movie;

import java.util.ArrayList;
import java.time.LocalDateTime;

public class MovieSessionManager {
	/** 
     * All controllers (movie, cinema, cineplex, session)
     */
	private MovieManager movieManager = new MovieManager();
	private CinemaManager cinemaManager = new CinemaManager();
	private CineplexManager cineplexManager = new CineplexManager();
	private MovieTimeSlotManager timeslotManager = new MovieTimeSlotManager();
	
	public void main(){
		boolean valid = true;
		while(valid == true) {
			System.out.print("\nOption List: \n" +
						     "1. Create interfaces.Movie Session\n" +
						     "2. Update interfaces.Movie Session\n" +
						     "3. Remove interfaces.Movie Session\n" +
						     "4. View interfaces.Movie Sessions\n" +
						     "5. Return to Main Menu\n" +
							 "Please select option: ");
			int choice = InputManager.getInt();
			switch(choice) {
				case 1:
					createMovieSession();
					break;
				case 2:
					updateMovieSession();
					break;
				case 3:
					removeMovieSession();
					break;
				case 4:
					viewMovieSession();
					break;
				case 5:
					valid = false;
					break;
				default:
					System.out.println("Invalid choice! Please choose again.");
					break;
			}
		}
	}
	
	public void createMovieSession() {		
		int i;
		
		System.out.println("interfaces.Cineplex List:\n");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("interfaces.Cineplex List is empty!\n" +
								"Returning back to main menu");
			return;
		}
		
		for(i = 0; i < cineplexList.size(); i++) {
			printCineplex(cineplexList.get(i));
		}
		
		System.out.println("\nPlease select a cineplex:");
		String cineplexName = InputManager.getString();
		Cineplex cineplex = cineplexManager.getCineplex(cineplexName);
		
		if (cineplex == null) {
			System.out.println("interfaces.Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		System.out.println("\ninterfaces.Cinema List: \n");
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
		
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}
		
		System.out.println("Please select a cinema: ");
		String cinema = InputManager.getString();
		
		if(cinemaManager.getCinema(CinemaManager.code, cinema).isEmpty()) {
			System.out.println("interfaces.Cinema does not exist!\n"+
						   		"Returning back to main menu");
			return;
		}

		System.out.println("Please select a movie: ");
	    int movieID = InputManager.getInt();
	    if (movieManager.readMovieID(movieID) == null) {
			System.out.println("interfaces.Movie does not exist!\n"+
							   "Returning back to main menu");
			return;
		};

	    System.out.println("Enter session date and time: ");
	    LocalDateTime timeslot = InputManager.getDateTime();
	    Movie movie = movieManager.readMovieID(movieID);
	    timeslotManager.createTimeslot(cinema, movie, timeslot);
	    

	    System.out.println("interfaces.Movie Session created successfully!");

	}
	
	public void updateMovieSession() {
		int i;
		System.out.println("interfaces.Cineplex List:\n");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("interfaces.Cineplex List is empty!\n"
								+ "Returning back to main menu");
			return;
		}
		
		for(i = 0; i < cineplexList.size(); i++) {
			printCineplex(cineplexList.get(i));
		}
		
		System.out.println("\nPlease select a cineplex:");
		String cineplexName = InputManager.getString();
		Cineplex cineplex = cineplexManager.getCineplex(cineplexName);
		
		if (cineplex == null) {
			System.out.println("interfaces.Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		System.out.println("interfaces.Cinema List:\n ");
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
		
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}
		
		System.out.print("Please select session ID to update: ");
		int timeslotID = InputManager.getInt();
		if (timeslotManager.getTimeslotByID(timeslotID) == null) {
			System.out.println("Session iD does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		System.out.println("Option List: \n" +
						   "1. interfaces.Movie\n" +
						   "2. Date & Time\n" +
						   "Please select an option: ");
		
		int choice = InputManager.getInt();

		switch(choice) {
		case 1:
			System.out.println("Please enter new interfaces.Movie ID: ");
			int movieID = InputManager.getInt();
			if (movieManager.readMovieID(movieID) == null) {
				System.out.println("interfaces.Movie does not exist!\n"+
								   "Returning back to main menu");
				return;
			}
			timeslotManager.updateTimeslotByID(MovieTimeSlotManager.movie, timeslotID, movieManager.readMovieID(movieID));
			break;
			
		case 2:
			System.out.println("Please enter new date & time: ");
			LocalDateTime timeslotDate = InputManager.getDateTime();
			timeslotManager.updateTimeslotByID(MovieTimeSlotManager.ID, timeslotID, timeslotDate);
			break;
			
		}
		System.out.println("Session " + timeslotID + " successfully updated!");
	}
	
	public void removeMovieSession() {
		int i;
		System.out.println("\ninterfaces.Cineplex List:");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("interfaces.Cineplex List is empty!\n"  +
							   "Returning back to main menu");
			return;
		}
		
		for(i = 0; i < cineplexList.size(); i++) {
			printCineplex(cineplexList.get(i));
		}

		System.out.println("Please select a cineplex: ");
		String cineplexName = InputManager.getString();
		Cineplex cineplex = cineplexManager.getCineplex(cineplexName);
		
		if (cineplex == null) {
			System.out.println("interfaces.Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}

		System.out.println("interfaces.Cinema List:\n ");
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
		
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}

		System.out.print("Please select session ID to delete: ");
		int timeslotID = InputManager.getInt();
		if (timeslotManager.getTimeslotByID(timeslotID) == null) {
			System.out.println("Session ID does not exist!\n" +
							   "Returning back to main menu");
			return;
		}	
		
		timeslotManager.deleteTimeslotByID(timeslotID);
		System.out.println("Session " + timeslotID + " successfully deleted!");
	}
	
	public void viewMovieSession() {
		int i;
		System.out.println("\ninterfaces.Cineplex List:");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("interfaces.Cineplex List is empty!\n" +
								"Returning back to main menu");
			return;
		}
		
		for(i = 0; i < cineplexList.size(); i++) {
			printCineplex(cineplexList.get(i));
		}
		
		System.out.println("\nPlease select a cineplex: ");
		String cineplexName = InputManager.getString();
		Cineplex cineplex = cineplexManager.getCineplex(cineplexName);
		
		if (cineplex == null) {
			System.out.println("interfaces.Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
		
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}
	}
	
	public void printCinema(Cinema cinema) {
		int i;
		System.out.println("\ninterfaces.Cinema code: " + cinema.getCinemaCode());
		ArrayList<MovieTimeslot> timeslotList = cinema.getMovieTimeslot();
		for(i = 0; i < timeslotList.size(); i++) {
			printTimeslot(timeslotList.get(i));
		}
	}
	
	public void printCinemaCode(Cinema cinema) {
		System.out.println("interfaces.Cinema code: " + cinema.getCinemaCode());
	}
	
	public void printTimeslot(MovieTimeslot timeslot) {
		System.out.print("\n\tTimeslot ID: " + timeslot.getTimeslotID() + "\n" +
						   "\tinterfaces.Movie Title: " + timeslot.getMovie().getName() + "\n" +
						   "\tDate: " + timeslot.getStringTimeSlot() + "\n");
	}
	
	public void printCineplex(Cineplex cineplex) {
		System.out.println("interfaces.Cineplex: " + cineplex.getName());
	}
}
