package View;

import Controller.InputManager;
import Controller.MovieManager;
import Model.MovieType;

import java.time.LocalDate;
import java.util.ArrayList;


public class MovieListingUI {
	private static MovieManager movieManager = new MovieManager();
	
	public void main() {
		boolean active = true;
		while(active) {
			System.out.printf("\nOption List: \n" +
					"\t1. Create Movie Listing\n" +
					"\t2. Update Movie Listing\n" +
					"\t3. Remove Movie Listing\n" +
					"\t4. Return to Main Menu\n\n");
			System.out.printf("\tPlease select an option: ");
			int option = InputManager.getInt();
			switch(option) {
			case 1:
				createMovie();
				break;
			case 2:
				updateMovie();
				break;
			case 3:
				removeMovie();
				break;
			case 4:
				active = false;
				break;
			}
		}
	}
	
	public void createMovie() {
		String name, synopsis, rating, director;
		int type, castNumber, i;
		double duration;
		System.out.println("Please key in movie details");
		System.out.println("|======================================|");
		System.out.println("Please enter the name of the movie: ");
		name = InputManager.getString();

		System.out.println("List of Available Movie Types: ");
		System.out.println("\t1. 2D");
		System.out.println("\t2. 3D");
		System.out.println("\t3. Blockbuster");
		System.out.println();
		System.out.println("\tPlease select the Movie Type of the Movie (1-3): ");
		type = InputManager.getInt();
		MovieType movietype;

		switch (type) {
			case 1 -> movietype = MovieType.TwoD;
			case 2 -> movietype = MovieType.ThreeD;
			case 3 -> movietype = MovieType.Blockbuster;
			default -> {
				System.out.println("Invalid input!");
				System.out.println("Returning back to Main Menu");
				System.out.println("---------------------------");
				return;
			}
		}
		
		System.out.println("Please enter movie synopsis: ");
		synopsis = InputManager.getString();

		System.out.println("List of movie ratings: ");
		System.out.println("\tG    - General");
		System.out.println("\tPG13 - Parental Guidance 13");
		System.out.println("\tNC16 - No Children Under 16");
		System.out.println("\tM18  - Mature 18");
		System.out.println("\tR21  - Restricted 21");
		System.out.println();
		System.out.println("\tPlease select the Movie Rating: ");
		rating = InputManager.getString();

		System.out.println("Please enter the duration of the movie: ");
		duration = InputManager.getInt();

		System.out.println("Please enter the release date of the movie (DD/MM/YYYY): ");
		LocalDate startDate = InputManager.getDate();

		System.out.println("Please enter the end date of the movie (DD/MM/YYYY): ");
		LocalDate endDate = InputManager.getDate();

		if (endDate.isBefore(startDate)) {
			System.out.println("End Date cannot be earlier than Start Date!\n" +
							   "Returning back to main menu");
			return;
		}

		System.out.println("Please enter the name of the movie director: ");
		director = InputManager.getString();

		System.out.println("Please enter the number of casts (at least 2): ");
		castNumber = InputManager.getInt();
		if (castNumber < 2) {
			System.out.println("There must be at least 2 cast members!\n" +
							   "Returning to menu");
			return;
		}
		ArrayList<String> castList = new ArrayList<String>();
		for (i = 0; i < castNumber; i++) {
			System.out.println("Enter name of cast " + (i+1) + ": ");
			castList.add(InputManager.getString());
		}

		movieManager.createMovie(name, movietype, synopsis, rating, duration, startDate, endDate, director, castList);

		if (movieManager.readMovie().isEmpty())
			return;
		
		System.out.println("\nMovie created!");
	}
	
	public void updateMovie() {
		SearchMovieUI searchMovie = new SearchMovieUI();
		System.out.println("\nMovie List: ");

		if(searchMovie.displayAll()){
			System.out.printf("Please select the movie to be updated by entering the correct Model.Movie ID: ");
			int ID = InputManager.getInt();
			if (movieManager.readMovieID(ID) == null) {
				System.out.println("\nInvalid choice!\n" +
								   "Returning back to main menu");
				return;
			}

			System.out.println("What would you like to update?\n" +
								"\t1. Movie Title\n" +
								"\t2. Movie Type\n" +
								"\t3. Movie Synopsis\n" +
								"\t4. Movie Model.Rating\n" +
								"\t5. Movie Duration \n" +
								"\t6. Movie Start Date\n" +
								"\t7. Movie End Date\n" +
								"\t8. Movie Director\n" +
								"\t9. Movie Cast\n" +
								"\tPlease enter option: ");
			int choice = InputManager.getInt();

			switch(choice) {
				case 1:
					System.out.println("Please enter the name of the movie:");
					String name = InputManager.getString();
					movieManager.updateMovie(1, ID, name);
					break;
				case 2:
					System.out.print(  "\nList of movie types: \n" +
									   "\t1. 2D\n" +
									   "\t2. 3D\n" +
									   "\t3. Blockbuster\n\n" +
									   "\tSelect movie type (number): ");
					int type = InputManager.getInt();
					MovieType movieType;
					switch(type) {
					case 1:
						movieType = MovieType.TwoD;
						break;
					case 2:
						movieType = MovieType.ThreeD;
						break;
					case 3:
						movieType = MovieType.Blockbuster;
						break;
					default:
						System.out.println("Invalid input!\n" 
							     + "Returning back to main menu\n\n");
						return;
					}
					movieManager.updateMovie(2, ID, movieType);
					break;
				case 3:
					System.out.println("Please enter movie synopsis: ");
					String synopsis = InputManager.getString();
					movieManager.updateMovie(3, ID, synopsis);
					break;
				case 4:
					System.out.println("List of movie ratings: \n" +
										"\tG - General\n" +
										"\tPG13 - Parental Guidance 13\n" +
										"\tNC16 - No Children Under 16\n" +
										"\tM18 - Mature 18\n" +
										"\tR21 - Restricted 21\n" +
										"\tPlease select the movie rating: ");
					String rating = InputManager.getString();
					movieManager.updateMovie(4, ID, rating);
					break;

				case 5:
					System.out.println("Please enter the duration of the movie: ");
					double duration = InputManager.getInt();
					movieManager.updateMovie(5, ID, duration);
					break;

				case 6:
					System.out.println("Please enter the release date of the movie (DD/MM/YYYY): ");
					LocalDate startDate = InputManager.getDate();
					if (startDate.isAfter(movieManager.readMovieID(ID).getEndDate())) {
						System.out.println("Start Date cannot be later than End Date!\n" +
										   "Returning back to main menu");
						return;
					}
					movieManager.updateMovie(6, ID, startDate);
					break;
				case 7:
					System.out.println("Please enter the end date of the movie (DD/MM/YYYY): ");
					LocalDate endDate = InputManager.getDate();
					if (endDate.isBefore(movieManager.readMovieID(ID).getEndDate())){
						System.out.println("End Date cannot be earlier than Start Date!\n" +
										   "Returning back to main menu");
						return;
					}
					movieManager.updateMovie(7, ID, endDate);
					break;
				case 8:
					System.out.println("\nPlease enter the name of the movie director: ");
					String director = InputManager.getString();
					movieManager.updateMovie(8, ID, director);
					break;

				case 9:														
					int castNumber, i;
					ArrayList<String> castList = new ArrayList<String>();
					System.out.println("Please enter the number of casts (at least 2): ): ");
					castNumber = InputManager.getInt();
					if (castNumber < 2) {
						System.out.println("There must be at least 2 cast members!\n" +
										   "Returning to menu");
						return;
					}
					for (i = 0; i < castNumber; i++) {
						System.out.println("Enter name of cast " + (i+1) + " :");
						castList.add(InputManager.getString());
					}
					movieManager.updateMovie(9, ID, castList);
					break;
				default:
					System.out.println("Invalid choice!\n" +
									   "Returning back to main menu");
					return;
			}
		}
		else{
			System.out.println("Returning back to main menu");
		}
	}
	
	public void removeMovie(){
		SearchMovieUI searchMovie = new SearchMovieUI();
		System.out.println("Movie List:");
		
		if(true){
			System.out.print("Please select the movie to be removed: ");
			int ID = InputManager.getInt();
			if (movieManager.readMovieID(ID) == null) {
				System.out.println("Invalid choice!\n"
								+ "Returning back to main menu");
				return;
			}
			movieManager.deleteMovie(ID);
		}
		else{
			System.out.println("Returning back to main menu");
		}
	}
}
