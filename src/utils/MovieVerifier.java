package utils;

import controllers.MovieManager;
import interfaces.Movie;
import interfaces.MovieStatus;
import interfaces.MovieType;

import java.time.LocalDate;
import java.util.ArrayList;

public class MovieVerifier {
	static MovieManager movieManager = new MovieManager();

    public static boolean isValidMovie(
            String name, MovieType type, String synopsis, String rating,
            double duration, LocalDate movieReleaseDate, LocalDate movieEndDate,
            String director, ArrayList<String> castList
    ) {
    	boolean isValid = true;
        if(isExistingMovie(name)) {
        	System.out.println("interfaces.Movie already exists!");
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
        	System.out.println("interfaces.Movie duration cannot be negative!");
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

    public static boolean isExistingMovie(String name) {
    	int i;
        ArrayList<Movie> movieListing = movieManager.readMovie();
        for (i = 0; i < movieListing.size(); i++) {
            if (movieListing.get(i).getName().equals(name))
                return true;
        }
        return false;
    }

    public static boolean isEmptyName(String name) {
    	if(name.equals("")) {
    		return true;
    	}
    	return false;
    }

    public static boolean isEmptySynopsis(String synopsis) {
    	if(synopsis.equals("")) {
    		return true;
    	}
    	return false;
    }

    public static boolean isEmptyRating(String rating) {
    	if(rating.equals("")) {
    		return true;
    	}
    	return false;
    }
    
    public static boolean isNegativeDuration(double duration){
        if (duration < 0){
        	return true;
        }
        return false;
    }
  
    public static boolean isEmptyDirector(String director) {
    	if(director.equals("")) {
    		return true;
    	}
    	return false;
    }

    public static boolean isEmptyCastList(ArrayList<String> castList) {
    	int i;
    	for(i = 0; i < castList.size(); i++) {
    		if(castList.get(i).equals("")) {
    			return true;
    		}
    	}
    	return false;
    }
   
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
