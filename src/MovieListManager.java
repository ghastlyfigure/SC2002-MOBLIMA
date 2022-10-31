import java.time.LocalDate;
import java.util.ArrayList;

public class MovieListManager {
	public void main() {
		boolean active = true;
		while(active == true) {
			System.out.printf("\n\nPlease select option: \n\n" +
					"1. Create Movie Listing\n" +
					"2. Update Movie Listing\n" +
					"3. Remove Movie Listing\n" +
					"4. Return to Main Menu\n\n");
			System.out.printf("Select option: ");
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
		int type, duration, castNumber, i;
		System.out.println("Please key in movie details");
		System.out.println("Please enter the name of the movie: ");
		name = InputManager.getString();
		System.out.println("\nList of movie types: \n" +
						   "1. 2D\n" +
						   "2. 3D\n" +
						   "3. Blockbuster\n\n" +
						   "Please select the movie type: ");
		type = InputManager.getInt();
		switch(type) {
		case 1:
			break;
		case 2:
			break;
		case 3:
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
						   "R21 - Restricted 21\n\n" +
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
			System.out.println("Cast members must be more than 2!\n" +
							   "Returning to menu");
			return;
		}
		ArrayList<String> cast = new ArrayList<String>();
		for (i = 0; i < castNumber; i++) {
			System.out.println("Enter name of cast " + (i+1) + ": ");
			cast.add(InputManager.getString());
			}

		//movieCtrl.create(title, movieType, synopsis, rating, duration, movieReleaseDate, movieEndDate, director, cast);

		//if (movieCtrl.read().isEmpty())
		//	return;
		System.out.println("\n\nMovie created....");
	}
	
	public void updateMovie() {
		
	}
	
	public void removeMovie() {
		
	}
}
