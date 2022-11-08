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
					"Please select option:\n" +
					"1. Admin Login\n" +
					"2. Guest Login\n" +
					"3. Exit Application\n");
			System.out.print("Select action: ");
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
		MovieManager movieManager = new MovieManager();

		ArrayList<Cinema> cinemasOne = new ArrayList<>();
		ArrayList<Cinema> cinemasTwo = new ArrayList<>();
		ArrayList<Cinema> cinemasThree = new ArrayList<>();

		cinemasOne.add(new Cinema("RTU","RTU",CinemaType.BronzeClass,new SeatList(10,10)));
		cinemasOne.add(new Cinema("DFK","DFK",CinemaType.BronzeClass,new SeatList(10,10)));
		cinemasOne.add(new Cinema("TYU","TYU",CinemaType.BronzeClass,new SeatList(10,10)));
		cinemasTwo.add(new Cinema("XDP","XDP",CinemaType.SilverClass,new SeatList(10,10)));
		cinemasTwo.add(new Cinema("PUP","PUP",CinemaType.SilverClass,new SeatList(10,10)));
		cinemasTwo.add(new Cinema("VAL","VAL",CinemaType.SilverClass,new SeatList(10,10)));
		cinemasThree.add(new Cinema("TIR","TIR", CinemaType.GoldClass, new SeatList(10,10)));
		cinemasThree.add(new Cinema("EFG","EFG",CinemaType.GoldClass, new SeatList(10,10)));
		cinemasThree.add(new Cinema("TUT","IUT",CinemaType.GoldClass, new SeatList(10,10)));

		if(cineplexManager.getAllCineplex().size() == 0){
			System.out.println(cinemasOne.size());
			cineplexManager.createCineplex("Orchard Movies Cineplex", cinemasOne);
			cineplexManager.createCineplex("Sentosa Beach Cineplex", cinemasTwo);
			cineplexManager.createCineplex("Movie Base Cineplex", cinemasThree);
		}
	}
	
	public static void adminLogin() {
		boolean validAccount = false;
		boolean validLogin = false;
		String username, password;
		while(validAccount == false) {
			System.out.println("Please enter username: ");
			username = InputManager.getString();
			System.out.println("Please enter password: ");
			password = InputManager.getString();
			if(username.equals("admin") && password.equals("123456")) {
				validAccount = true;
				validLogin = true;
				while(validLogin == true) {
					System.out.println("\n|=========================================|");
					System.out.println("|===========|Administrator Mode=|=========|");
					System.out.println("|=========================================|\n" +
								"\n\n"+
							   "Please select option:\n" + 
							   "1. Create/Update/Remove movie listing\n" +
							   "2. Create/Update/Remove movie session\n" +
							   "3. List Top 5 Ranking Movies\n" +
							   "4. Configure system settings\n" +
							   "5. Log out\n");
					System.out.println("Select action: ");
					switch(InputManager.getInt()) {
					case 1:
						MovieListManager movieListManager = new MovieListManager();
						movieListManager.main();
						break;
					case 2:
						MovieSessionManager movieSessionManager = new MovieSessionManager();
						movieSessionManager.main();
						break;
					case 3:
						
						break;
					case 4:
						
						break;
					case 5:
						validLogin = false;
						System.out.println("Logged out successfully!");
						System.out.println();
						System.out.println();
						break;
					default:
						System.out.println("Invalid option, please try again.");
						break;
					}
				}
			}
		}
	}
	
	public static void guestLogin() {
		boolean validLogin = true;
		while(validLogin == true) {
			System.out.println("\n|=========================================|");
			System.out.println("|===============|Guest Mode|==============|");
			System.out.println("|=========================================|");
			System.out.print("\n"+
					   "1. Search movie\n"+
					   "2. View movie details\n"+
					   "3. Check seat availibility\n" +
					   "4. Purchase ticket\n" +
					   "5. View booking history\n" +
					   "6. List Top 5 Ranking Movies\n" +
					   "7. Exit\n");
			System.out.println("Select action: ");
			switch(InputManager.getInt()) {
				case 1:
					
					break;
				case 2:
					
					break;
				case 3:
					
					break;
				case 4:
					
					break;
				case 5:
					
					break;
				case 6:
					
					break;
				case 7:
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
