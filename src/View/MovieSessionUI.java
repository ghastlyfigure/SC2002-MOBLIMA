package View;

import Controller.*;
import Model.Cinema;
import Model.Cineplex;
import Model.Movie;
import Model.MovieTimeslot;

import java.util.ArrayList;
import java.time.LocalDateTime;


public class MovieSessionUI {
	private MovieManager movieManager = new MovieManager();
	private CinemaManager cinemaManager = new CinemaManager();
	private CineplexManager cineplexManager = new CineplexManager();
	private MovieTimeSlotManager timeslotManager = new MovieTimeSlotManager();
	private SearchMovieUI searchMovie = new SearchMovieUI();
	
	public void main(){
		boolean valid = true;
		while(valid) {
			System.out.print("\nOption List: \n" +
						     "\t1. Create Movie Session\n" +
						     "\t2. Update Movie Session\n" +
						     "\t3. Remove Movie Session\n" +
						     "\t4. View Movie Sessions\n" +
						     "\t5. Return to Main Menu\n\n" +
							 "\tPlease select option: ");
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
		
		System.out.println("\nCineplex List:");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("Cineplex List is empty!\n" +
								"Returning back to main menu");
			return;
		}

		for(i = 0; i < cineplexList.size(); i++) {
			System.out.println("\t" + (i+1) + ". " + cineplexList.get(i).getName());
		}
		
		System.out.println("\nPlease select a cineplex by entering the corresponding number:");
		int cineplexChoice = InputManager.getInt();
		if (cineplexChoice < 1 || cineplexChoice > cineplexList.size()){
			System.out.println("Wrong input!");
		}
		Cineplex cineplex = cineplexList.get(cineplexChoice - 1);
		
		if (cineplex == null) {
			System.out.println("Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		System.out.println("\nModel.Cinema List: ");
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
		
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}
		
		System.out.println("\nPlease select a cinema by entering the corresponding cinema code: ");
		String cinema = InputManager.getString();
		
		if(cinemaManager.getCinema(CinemaManager.code, cinema).isEmpty()) {
			System.out.println("Cinema does not exist!\n"+
						   		"Returning back to main menu");
			return;
		}
		
		System.out.println("\nMovie List: \n");
		if(!searchMovie.displayAll()) {
			System.out.println("Returning back to main menu");
			return;
		}
		
		System.out.println("Please select a movie: ");
	    int movieID = InputManager.getInt();
	    
	    if (movieManager.readMovieID(movieID) == null) {
			System.out.println("Movie does not exist!\n"+
							   "Returning back to main menu");
			return;
		};


	    System.out.println("Enter session date and time: ");
	    LocalDateTime timeslot = InputManager.getDateTime();
	    Movie movie = movieManager.readMovieID(movieID);
	    timeslotManager.createTimeslot(cinema, movie, timeslot);
	}
	
	public void updateMovieSession() {
		int i;
		System.out.println("Cineplex List:\n");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("Cineplex List is empty!\n"
								+ "Returning back to main menu");
			return;
		}

		for(i = 0; i < cineplexList.size(); i++) {
			System.out.println("\t" + (i+1) + ". " + cineplexList.get(i).getName());
		}

		System.out.println("\nPlease select a cineplex by selecting the corresponding number:");
		int cineplexChoice = InputManager.getInt();
		if(cineplexChoice < 1 || cineplexChoice > cineplexList.size()){
			System.out.println("Wrong input!");
		}
		Cineplex cineplex = cineplexList.get(cineplexChoice - 1);

		
		if (cineplex == null) {
			System.out.println("Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		System.out.println("Cinema List:\n ");
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();

		if (cinemaList.size() == 0) {
			System.out.println("There are no Movie Sessions in this cinema");
			System.out.println("Returning back to Main Menu");
			return;
		}

		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}
		
		System.out.print("Please select Timeslot ID to update: ");
		int timeslotID = InputManager.getInt();
		if (timeslotManager.getTimeslotByID(timeslotID) == null) {
			System.out.println("Session ID does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		System.out.println("Option List: \n" +
						   "1. Movie\n" +
						   "2. Date & Time\n" +
						   "Please select an option: ");
		
		int choice = InputManager.getInt();

		switch(choice) {
		case 1:
			System.out.println("\nMovie List: \n");
			if(!searchMovie.displayAll()) {
				System.out.println("Movie does not exist!\n"+
						   "Returning back to main menu");
				return;
			}
			System.out.println("Please enter new Movie ID: ");
			int movieID = InputManager.getInt();
			if (movieManager.readMovieID(movieID) == null) {
				System.out.println("Movie does not exist!\n"+
								   "Returning back to main menu");
				return;
			}
			timeslotManager.updateTimeslotByID(MovieTimeSlotManager.movie, timeslotID, movieManager.readMovieID(movieID));
			break;
			
		case 2:
			System.out.println("Please enter new date & time: ");
			LocalDateTime timeslotDate = InputManager.getDateTime();
			timeslotManager.updateTimeslotByID(MovieTimeSlotManager.timeslot, timeslotID, timeslotDate);
			break;
			
		}
		System.out.println("Session " + timeslotID + " successfully updated!");
	}
	
	public void removeMovieSession() {
		int i;
		System.out.println("\nCineplex List:");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("Cineplex List is empty!\n"  +
							   "Returning back to main menu");
			return;
		}

		for(i = 0; i < cineplexList.size(); i++) {
			System.out.println("\t" + (i+1) + ". " + cineplexList.get(i).getName());
		}

		System.out.println("\nPlease select a cineplex by selecting the corresponding number:");
		int cineplexChoice = InputManager.getInt();
		if(cineplexChoice < 1 || cineplexChoice > cineplexList.size()){
			System.out.println("Wrong input!");
		}
		Cineplex cineplex = cineplexList.get(cineplexChoice - 1);
		
		if (cineplex == null) {
			System.out.println("Cineplex does not exist!");
			System.out.println("Returning back to Main Menu");
			return;
		}

		System.out.println("Cinema List:\n ");
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();

		if (cinemaList.size() == 0) {
			System.out.println("There are no Movie Sessions in this cinema");
			System.out.println("Returning back to Main Menu");
			return;
		}
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}

		System.out.print("\nPlease select timeslot ID to delete: ");
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
		System.out.println("\nCineplex List:");
		ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
		
		if (cineplexList.isEmpty()) {
			System.out.println("Cineplex List is empty!\n" +
								"Returning back to main menu");
			return;
		}

		for(i = 0; i < cineplexList.size(); i++) {
			System.out.println("\t" + (i+1) + ". " + cineplexList.get(i).getName());
		}

		System.out.println("\nPlease select a cineplex by entering the corresponding number:");
		int cineplexChoice = InputManager.getInt();
		if(cineplexChoice < 1 || cineplexChoice > cineplexList.size()){
			System.out.println("Wrong input!");
		}
		Cineplex cineplex = cineplexList.get(cineplexChoice - 1);
		
		if (cineplex == null) {
			System.out.println("Cineplex does not exist!\n" +
							   "Returning back to main menu");
			return;
		}
		
		ArrayList<Cinema> cinemaList = cineplex.getCinemaList();

		if (cinemaList.size() == 0){
			System.out.println("There are no Movie Sessions in this cinema");
			System.out.println("Returning back to Main Menu");
			return;
		}
		for(i = 0; i < cinemaList.size(); i++) {
			printCinema(cinemaList.get(i));
		}
	}
	
	public void printCinema(Cinema cinema) {
		int i;
		System.out.println("\tCinema: " + cinema.getCinemaName() + " (" +
							cinema.getCinemaCode() + ")");
		ArrayList<MovieTimeslot> timeslotList = cinema.getMovieTimeslot();
		if (timeslotList.size() == 0) {
			System.out.println("\t\tThere are no Movie Sessions in this cinema");
			System.out.println();
		}
		for(i = 0; i < timeslotList.size(); i++) {
			printTimeslot(timeslotList.get(i));
		}
	}
	
	public void printCinemaCode(Cinema cinema) {
		System.out.println("Cinema code: " + cinema.getCinemaCode());
	}
	
	public void printTimeslot(MovieTimeslot timeslot) {
		System.out.print("\t\tTimeslot ID: " + timeslot.getTimeslotID() + "\n" +
						   "\t\tMovie Title: " + timeslot.getMovie().getName() + "\n" +
						   "\t\tDate: " + timeslot.getStringTimeSlot() + "\n\n");
	}

	public void printCineplex(Cineplex cineplex) {
		System.out.println("Cineplex: " + cineplex.getName());
	}
}
