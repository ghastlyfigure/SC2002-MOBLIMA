package Verify;

import Controller.MovieManager;
import Model.Movie;
import Model.Rating;

import java.util.ArrayList;

public class RatingVerifier {
    public static MovieManager movieManager = new MovieManager();

    public static boolean isValidRating(Movie movie, String username) {

        if (isExistingReview(movie, username)){
            System.out.println("You have already rated this movie!");
            return false;
        }
        else{
            return true;
        }
    }
    public static boolean isExistingReview(Movie movie, String username) {
        if (MovieVerifier.isExistingMovie(movie.getName())){
            Movie m = movieManager.readMovieID(movie.getID());
            ArrayList<Rating> ratingList = m.getRatingList();
            for(int i = 0; i < ratingList.size(); i++){
                if (ratingList.get(i).getName().equals(username))
                    return true;
            }
        }
        return false;
    }
}
