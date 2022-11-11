package View;

import Controller.CineplexManager;
import Controller.InputManager;
import Controller.LoginManager;
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

		loginManager.createAccount("admin", "password");

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
		while(validLogin) {
			System.out.println();
			System.out.println("|=========================================|");
			System.out.println("|===========|Administrator Mode=|=========|");
			System.out.println("|=========================================|");
			System.out.println();
			System.out.println("Option List:");
			System.out.println();
			System.out.println("\t1. Create/Update/Remove movie listing");
			System.out.println("\t2. Create/Update/Remove movie session");
			System.out.println("\t3. List Top 5 Ranking Movies");
			System.out.println("\t4. Configure System Settings");
			System.out.println("\t5. Log out");
			System.out.println();
			System.out.println("\tPlease select an option: ");
			switch (InputManager.getInt()) {
				case 1:
					MovieListingUI movieListingUI = new MovieListingUI();
					movieListingUI.main();
					break;
				case 2:
					MovieSessionUI movieSessionUI = new MovieSessionUI();
					movieSessionUI.main();
					break;
				case 3:
					// TODO: List Top 5 Ranking Movies
					// List top 5 Ranking Movies
					break;
				case 4:
					// display/add/remove holiday, set holiday/weekend charges
					ConfigureSettingsUI configureSettingsUI = new ConfigureSettingsUI();
					configureSettingsUI.main();

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
		while(validLogin) {
			System.out.println();
			System.out.println("|=========================================|");
			System.out.println("|===============|Guest Mode|==============|");
			System.out.println("|=========================================|");
			System.out.println();
			System.out.println("Option List:");
			System.out.println("\t1. Search for Movie");
			System.out.println("\t2. View Movie Details");
			System.out.println("\t3. Check seat availability");
			System.out.println("\t4. Purchase Ticket");
			System.out.println("\t5. View Booking History");
			System.out.println("\t6. List Top 5 Ranking Movies");
			System.out.println("\t7. Rate Movie");
			System.out.println("\t8. Exit");
			System.out.println("\tPlease Select an Option: ");

			int choice = InputManager.getInt();

			switch (choice) {
				case 1 -> {
					SearchMovieUI searchMovieUI = new SearchMovieUI();
					searchMovieUI.main();
				}
				case 2 -> {
					MovieInfoUI viewMovieDetailUI = new MovieInfoUI();
					viewMovieDetailUI.main();
				}
				case 3 -> {
					SeatAvailabilityUI seatAvailabilityUI = new SeatAvailabilityUI();
					seatAvailabilityUI.main();
				}
				case 4 -> {
					// TODO: Purchase Ticket UI
					PurchaseTicketUI purchaseTicketUI = new PurchaseTicketUI();
					purchaseTicketUI.main();
				}
				case 5 -> {
					BookingHistoryUI bookingHistoryUI = new BookingHistoryUI();
					bookingHistoryUI.main();
				}
				case 6 -> {
					TopMovieUI topMovieUI = new TopMovieUI();
					topMovieUI.main();
				}
				case 8 -> {
					validLogin = false;
					System.out.println("Logged out successfully!");
				}
				default -> System.out.println("Invalid option, please try again.");
			}
					
		}
	}
}
