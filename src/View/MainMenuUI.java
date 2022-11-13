package View;

import Controller.CineplexManager;
import Controller.InputManager;
import Controller.LoginManager;
import Model.Admin;
import Model.Cinema;
import Model.CinemaType;
import Model.SeatList;

import java.util.ArrayList;

public class MainMenuUI {
	public void main() {
		startSystem();

		boolean exit = false;
		while (!exit) {
			System.out.println("|======================================|");
			System.out.println("|=========|Welcome to MOBLIMA|=========|");
			System.out.println("|======================================|");
			System.out.println();
			System.out.println("Option List:");
			System.out.println("\t1. Admin Login/Registration");
			System.out.println("\t2. Movie-Goer Login");
			System.out.println("\t3. Exit MOBLIMA");
			System.out.println();
			System.out.println("\tPlease select an option: ");

			switch (InputManager.getInt()) {
				case 1 -> adminRegistrationMenu();
				case 2 -> guestLoginMenu();
				case 3 -> {
					exit = true;
					System.out.println("Exiting MOBLIMA");
					System.out.println("Goodbye!");
				}
				default -> {
					System.out.println("Wrong input!");
					System.out.println("Please try again");
				}
			}
			
		}
	}

	private static void startSystem() {
		CineplexManager cineplexManager = new CineplexManager();
		LoginManager loginManager = new LoginManager();

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
	
	public static void adminLoginMenu() {
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
			System.out.println("\t1. Create/Update/View/Remove Movie Listing");
			System.out.println("\t2. Create/Update/View/Remove Movie Session");
			System.out.println("\t3. List Top 5 Ranking Movies");
			System.out.println("\t4. Configure System Settings");
			System.out.println("\t5. Log out");
			System.out.println();
			System.out.println("\tPlease select an Option: ");

			switch (InputManager.getInt()) {
				case 1 -> {
					MovieListingUI movieListingUI = new MovieListingUI();
					movieListingUI.main();
				}
				case 2 -> {
					MovieSessionUI movieSessionUI = new MovieSessionUI();
					movieSessionUI.main();
				}
				case 3 -> {
					TopMovieUI topMovieUI = new TopMovieUI();
					topMovieUI.main();
				}
				case 4 -> {
					// Function: display/add/remove holiday, set holiday/weekend charges
					ConfigureSettingsUI configureSettingsUI = new ConfigureSettingsUI();
					configureSettingsUI.main();
				}
				case 5 -> {
					validLogin = false;
					System.out.println("Logged out successfully!\n\n");
				}
				default -> {
					System.out.println("Invalid option!");
					System.out.println("Please try again");
				}
			}
		}
	}
	
	public static void guestLoginMenu() {
		boolean validLogin = true;
		while (validLogin) {
			System.out.println();
			System.out.println("|=========================================|");
			System.out.println("|===============|Guest Mode|==============|");
			System.out.println("|=========================================|");
			System.out.println();
			System.out.println("Option List:");
			System.out.println("\t1. Search for Movie");
			System.out.println("\t2. View Movie Details");
			System.out.println("\t3. Check Seat Availability");
			System.out.println("\t4. Purchase Ticket");
			System.out.println("\t5. View Booking History");
			System.out.println("\t6. List Top 5 Ranking Movies");
			System.out.println("\t7. Rate Movie");
			System.out.println("\t8. Exit");
			System.out.println();
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
					// TODO: debug Purchase Ticket UI
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
				case 7 -> {
					MovieRateUI movieRateUI = new MovieRateUI();
					movieRateUI.main();
				}
				case 8 -> {
					validLogin = false;
					System.out.println("Logged out successfully!");
				}
				default -> System.out.println("Invalid option, please try again.");
			}

		}
	}

	public static void adminRegistrationMenu() {
		System.out.println("Option List:");
		System.out.println("\t1. Register new Admin account");
		System.out.println("\t2. Login as Admin");
		switch (InputManager.getInt()) {
			case 1 -> {
				boolean isUsernameTaken = true;
				boolean isPasswordSame = false;
				LoginManager loginManager = new LoginManager();
				ArrayList<Admin> adminList = loginManager.read();
				String username = "";

				while (isUsernameTaken) {
					boolean usernameMatch = false;
					System.out.println("Please enter your username");
					String usernameInput = InputManager.getString();

					for (Admin a : adminList) {
						if (a.getUsername().equals(usernameInput)) {
							usernameMatch = true;
							System.out.println("Username is already taken!");
							break;
						}
					}

					if (!usernameMatch) {
						isUsernameTaken = false;
						username = usernameInput;
					}
				}

				while (!isPasswordSame) {
					System.out.println("Please enter your password");
					String confirmation1 = InputManager.getString();
					System.out.println("Please enter your password again");
					String confirmation2 = InputManager.getString();
					if(confirmation1.equals(confirmation2)){
						isPasswordSame = true;
						loginManager.createAccount(username, confirmation2);
					}
				}
			}
			case 2 -> adminLoginMenu();
			default -> {
				System.out.println("Wrong input!");
				System.out.println("Please try again");
			}
		}
	}
}