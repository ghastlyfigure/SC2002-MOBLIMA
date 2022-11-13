package Verify;

import Controller.MovieManager;
import Model.Movie;
import Model.MovieStatus;
import Model.MovieType;

import java.time.LocalDate;
import java.util.ArrayList;

public class MovieVerifier {
	static MovieManager movieManager = new MovieManager();

    /**
     * Verify if movie can be created based on parameters passed
     * @param name                  Title of movie
     * @param type                  Type of movie
     * @param synopsis              Synopsis of movie
     * @param rating                Rating of movie
     * @param duration              Duration of movie
     * @param movieReleaseDate      Release date of movie
     * @param movieEndDate          End date of movie
     * @param director              Director of movie
     * @param castList              Cast list of movie
     * @return
     */
    public static boolean isValidMovie(
            String name, MovieType type, String synopsis, String rating,
            double duration, LocalDate movieReleaseDate, LocalDate movieEndDate,
            String director, ArrayList<String> castList
    ) {
    	boolean isValid = true;
        if(isExistingMovie(name)) {
        	System.out.println("Model.Movie already exists!");
        	isValid = false;
        }
        if(isEmptyName(name)) {
        	System.out.println("Empty movie title!");
        	isValid = false;
        }
        if (isEmptySynopsis(synopsis)) {
        	System.out.println("Empty movie synopsis!");
        	isValid = false;
        }
        if (isEmptyRating(rating)) {
        	System.out.println("Empty movie rating!");
        	isValid = false;
        }
        if (isNegativeDuration(duration)) {
        	System.out.println("Model.Movie duration cannot be negative!");
        	isValid = false;
        }
        if (isEmptyDirector(director)) {
        	System.out.println("Director cannot be empty!");
        	isValid = false;
        }
        if (isEmptyCastList(castList)) {
        	System.out.println("Cast List cannot have empty names!");
        	isValid = false;
        }
        
        return isValid;
    }

    /**
     * Verify if movie exists using title of movie passed
     * @param name      Title of movie to check
     * @return boolean  Return true if movie already exists, else false
     */
    public static boolean isExistingMovie(String name) {
    	int i;
        ArrayList<Movie> movieListing = movieManager.readMovie();
        for (i = 0; i < movieListing.size(); i++) {
            if (movieListing.get(i).getName().equals(name))
                return true;
        }
        return false;
    }

    /**
     * Verify if movie's title (name) is empty
     * @param name      Title of movie to check
     * @return boolean  Return true if title of movie is empty, else false
     */
    public static boolean isEmptyName(String name) {
    	if(name.equals("")) {
    		return true;
    	}
    	return false;
    }

    /**
     * Verify if movie's synopsis is empty
     * @param synopsis      Movie synopsis to check
     * @return boolean      Return true if movie's synopsis is empty, else false
     */
    public static boolean isEmptySynopsis(String synopsis) {
    	if(synopsis.equals("")) {
    		return true;
    	}
    	return false;
    }

    /**
     * Verify if movie's rating is empty
     * @param rating        Movie rating to check
     * @return boolean      Return true if movie's rating is empty, else false
     */
    public static boolean isEmptyRating(String rating) {
    	if(rating.equals("")) {
    		return true;
    	}
    	return false;
    }

    /**
     * Verify if movie's duration is empty
     * @param duration      Movie's duration to check for
     * @return boolean      Return true if movie's duration is negative, else false
     */
    public static boolean isNegativeDuration(double duration){
        if (duration < 0){
        	return true;
        }
        return false;
    }

    /**
     * Verify if movie's director is empty
     * @param director      Movie's director to check for
     * @return boolean      Return true if movie's director is negative, else false
     */
    public static boolean isEmptyDirector(String director) {
    	if(director.equals("")) {
    		return true;
    	}
    	return false;
    }

    /**
     * Verify if movie's cast list is empty
     * @param castList      Movie's cast list to check for
     * @return boolean      Return true if movie's cast list is negative, else false
     */
    public static boolean isEmptyCastList(ArrayList<String> castList) {
    	int i;
    	for(i = 0; i < castList.size(); i++) {
    		if(castList.get(i).equals("")) {
    			return true;
    		}
    	}
    	return false;
    }

    /**
     * Verify if movie is available for booking based on the status of movie
     * @param movie         Movie to check
     * @return boolean      Return true if movie is available for booking, else false
     */
    public static boolean canBeBooked(Movie movie){
        MovieStatus movieStatus = movie.getMovieStatus();
        if (movieStatus == MovieStatus.Preview || movieStatus == MovieStatus.Now_Showing) {
        	return true;
        }
        else {
        	return false;
        }
    }
}
