package View;




import Controller.CineplexManager;
import Controller.InputManager;
import Controller.LoginManager;
import Controller.MovieManager;
import Model.Cinema;
import Model.CinemaType;
import Model.SeatList;

import java.util.ArrayList;

public class MainMenuUI {
	public void main() {
		startSystem();

		boolean exit = false;
		while (exit == false) {
			System.out.println("|======================================|");
			System.out.println("|=========|Welcome to MOBLIMA|=========|");
			System.out.println("|======================================|\n" + 
					"\n\n" +
					"Option List:\n" +
					"\t1. Admin Login\n" +
					"\t2. Guest Login\n" +
					"\t3. Exit Application\n");
			System.out.print("\tPlease select an option: ");
		switch(InputManager.getInt()) {
			case 1:
				adminLogin();
				break;
			case 2:
				guestLogin();
				break;
			case 3:
				exit = true;
				System.out.println("Exiting MOBLIMA");
				System.out.println("Goodbye!");
				break;
			default:
				System.out.println("Wrong input!");
			}
			
		}
	}

	private static void startSystem() {
		CineplexManager cineplexManager = new CineplexManager();
		LoginManager loginManager = new LoginManager();

		loginManager.createAccount("admin", "123456");

		ArrayList<Cinema> CathayList = new ArrayList<>();
		ArrayList<Cinema> ShawList = new ArrayList<>();
		ArrayList<Cinema> GVList = new ArrayList<>();

		CathayList.add(new Cinema("Causeway Point Cinema",
						"CAU", CinemaType.BronzeClass,
								new SeatList(10,10)));
		CathayList.add(new Cinema("AMK Hub Cinema",
							"AMK", CinemaType.SilverClass,
								new SeatList(12,12)));
		CathayList.add(new Cinema("Downtown East Cinema",
							"DOW", CinemaType.GoldClass,
								new SeatList(15,15)));

		ShawList.add(new Cinema("Orchard Point Cinema",
						"ORC", CinemaType.BronzeClass,
						new SeatList(10,10)));
		ShawList.add(new Cinema("Jewel Cinema",
							"JEW", CinemaType.SilverClass,
								new SeatList(12,12)));
		ShawList.add(new Cinema("Paya Lebar Cinema","PAY",
							CinemaType.GoldClass,new SeatList(15,15)));

		GVList.add(new Cinema("Bishan Cinema","BIS",
							CinemaType.BronzeClass, new SeatList(10,10)));
		GVList.add(new Cinema("Tiong Bahru Cinema","TIO",
							CinemaType.SilverClass, new SeatList(12,12)));
		GVList.add(new Cinema("Katong Cinema","KAT",
							CinemaType.GoldClass, new SeatList(15,15)));

		if(cineplexManager.getAllCineplex().size() == 0){
			cineplexManager.createCineplex("Cathay Cineplex", CathayList);
			cineplexManager.createCineplex("Shaw Cineplex", ShawList);
			cineplexManager.createCineplex("Golden Village Cineplex", GVList);
		}


	}
	
	public static void adminLogin() {
		LoginUI loginUI = new LoginUI();
		boolean validLogin = loginUI.main();
		while(validLogin == true) {
			System.out.println("\n|=========================================|");
			System.out.println("|===========|Administrator Mode=|=========|");
			System.out.println("|=========================================|\n" +
					"\n\n" +
					"Option List:\n" +
					"\t1. Create/Update/Remove movie listing\n" +
					"\t2. Create/Update/Remove movie session\n" +
					"\t3. List Top 5 Ranking Movies\n" +
					"\t4. Configure system settings\n" +
					"\t5. Log out\n");
			System.out.print("\tPlease select an option: ");
			switch (InputManager.getInt()) {
				case 1:
					MovieListUI movieListUI = new MovieListUI();
					movieListUI.main();
					break;
				case 2:
					MovieSessionUI movieSessionUI = new MovieSessionUI();
					movieSessionUI.main();
					break;
				case 3:
					//List top 5 Ranking Movies
					break;
				case 4:
					//Configure System Settings
					break;
				case 5:
					validLogin = false;
					System.out.println("Logged out successfully!\n\n");
					break;
				default:
					System.out.println("Invalid option, please try again.");
					break;
			}
		}
	}
	
	public static void guestLogin() {
		boolean validLogin = true;
		while(validLogin == true) {
			System.out.println("\n|=========================================|");
			System.out.println("|===============|Guest Mode|==============|");
			System.out.println("|=========================================|");
			System.out.print("\n\n"+
					   "Option List: \n" +
					   "\t1. Search for movie\n"+
					   "\t2. View movie details\n"+
					   "\t3. Check seat availability\n" +
					   "\t4. Purchase ticket\n" +
					   "\t5. View booking history\n" +
					   "\t6. List Top 5 Ranking Movies\n" +
					   "\t7. Rate Movie\n" +
					   "\t8. Exit\n\n");
			System.out.printf("\tPlease select an option: ");
			int choice = InputManager.getInt();
			switch(choice) {
				case 1:
					SearchMovieUI searchMovieUI = new SearchMovieUI();
					searchMovieUI.main();
					break;
				case 2:
					MovieInfoUI viewMovieDetailUI = new MovieInfoUI();
					viewMovieDetailUI.main();
					break;
				case 3:
					SeatAvailabilityUI seatAvailabilityUI = new SeatAvailabilityUI();
					seatAvailabilityUI.main();
					break;
				case 4:
					//MakeBookingUI makeBookingUI = new MakeBookingUI();
					//makeBookingUI.main();
					break;
				case 5:
					BookingHistoryUI bookingHistoryUI = new BookingHistoryUI();
					bookingHistoryUI.main();
					break;
				case 6:
					TopMovieUI topMovieUI = new TopMovieUI();
					topMovieUI.main();
					break;
				case 8:
					validLogin = false;
					System.out.println("Logged out successfully!");
					break;
				default:
					System.out.println("Invalid option, please try again.");
					break;
			}
					
		}
	}
}
