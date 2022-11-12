package View;

import Controller.InputManager;
import Controller.MovieManager;
import Controller.RatingManager;
import Model.Movie;

public class MovieRateUI {
    private MovieManager movieManager = new MovieManager();
    private RatingManager ratingManager = new RatingManager();
    private SearchMovieUI searchMovieUI = new SearchMovieUI();

    public void main(){
        System.out.println("Please enter your username: ");
        String username = InputManager.getString();
        double score = -1;
        boolean validScore = false;
        if(searchMovieUI.displayAll()){
            System.out.print("Please select the movie to be rated: ");
            int ID = InputManager.getInt();
            Movie movie = movieManager.readMovieID(ID);
            if(movie == null){
                System.out.println("\nNo movies found!");
                System.out.println("Returning back to main menu\n");
                return;
            }
            while(validScore == false){
                System.out.println("Please enter your rating (from 1-5): ");
                score = InputManager.getDouble();
                if(score >= 0 && score <= 5){
                    validScore = true;
                }
                else{
                    System.out.println("Rating must be within the range of 1 to 5! Please try again.");
                }
            }
            System.out.println("Please enter your review (optional): ");
            String comment = InputManager.getString();
            ratingManager.createRating(movie, username, score, comment);
        }
    }
}