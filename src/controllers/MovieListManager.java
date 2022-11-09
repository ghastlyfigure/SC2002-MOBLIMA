package controllers;

import controllers.MovieManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class MovieListManager {
	private static MovieManager movieManager = new MovieManager();
	
	public void main() {
		boolean active = true;
		while(active == true) {
			System.out.printf("\nOption List: \n" +
					"1. Create interfaces.Movie Listing\n" +
					"2. Update interfaces.Movie Listing\n" +
					"3. Remove interfaces.Movie Listing\n" +
					"4. Return to Main Menu\n\n");
			System.out.printf("Please select option: ");
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
		System.out.println("Please enter the name of the movie: ");
		name = InputManager.getString();
		System.out.println("\nList of movie types: \n" +
						   "1. 2D\n" +
						   "2. 3D\n" +
						   "3. Blockbuster\n\n" +
						   "Please select the movie type: ");
		type = InputManager.getInt();
		MovieType movietype;
		switch(type) {
		case 1:
			movietype = MovieType.TwoD;
			break;
		case 2:
			movietype = MovieType.ThreeD;
			break;
		case 3:
			movietype = MovieType.Blockbuster;
			break;
		default:
			System.out.println("Invalid input!\n" 
						     + "Returning back to main menu\n\n");
			return;
		}
		
		System.out.println("Please enter movie synopsis: ");
		synopsis = InputManager.getString();

		System.out.println("List of movie ratings: \n" +
						   "G - General\n" +
						   "PG13 - Parental Guidance 13\n" +
						   "NC16 - No Children Under 16\n" +
						   "M18 - Mature 18\n" + 
						   "R21 - Restricted 21\n" +
						   "Please select the movie rating: ");
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
		
		System.out.println("\ninterfaces.Movie created....");
	}
	
	public void updateMovie() {
		SearchMovieUI searchMovie = new SearchMovieUI();
		System.out.println("interfaces.Movie List: \n");

		if(searchMovie.displayAll()){
			System.out.printf("Please select the movie to be updated: ");
			int ID = InputManager.getInt();
			if (movieManager.readMovieID(ID) == null) {
				System.out.println("Invalid choice!\n" +
								   "Returning back to main menu");
				return;
			}

			System.out.println("What would you like to update?\n" +
								"1. interfaces.Movie Title\n" +
								"2. interfaces.Movie Type\n" +
								"3. interfaces.Movie Synopsis\n" +
								"4. interfaces.Movie interfaces.Rating\n" +
								"5. interfaces.Movie Duration \n" +
								"6. interfaces.Movie Start Date\n" +
								"7. interfaces.Movie End Date\n" +
								"8. interfaces.Movie Director\n" +
								"9. interfaces.Movie Cast\n" +
								"Enter option: ");
			int choice = InputManager.getInt();

			switch(choice) {
				case 1:
					System.out.println("Please enter the name of the movie:");
					String name = InputManager.getString();
					movieManager.updateMovie(1, ID, name);
					break;
				case 2:
					System.out.print(  "\nPossible movie types: \n" +
									   "1. 2D\n" +
									   "2. 3D\n" +
									   "3. Blockbuster\n\n" +
									   "Select movie type (number): ");
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
										"G - General\n" +
										"PG13 - Parental Guidance 13\n" +
										"NC16 - No Children Under 16\n" +
										"M18 - Mature 18\n" +
										"R21 - Restricted 21\n" +
										"Please select the movie rating: ");
					String rating = InputManager.getString();
					movieManager.updateMovie(4, ID, rating);
					break;

				case 5:
					System.out.println("Please enter the name of the movie director: ");
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
					System.out.println("Please enter the name of the movie director: ");
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
		System.out.println("interfaces.Movie List:");
		
		if(searchMovie.displayAll()){
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
