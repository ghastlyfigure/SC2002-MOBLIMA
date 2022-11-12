package Controller;

import Model.Movie;
import Model.Rating;
import Verify.RatingVerifier;

import java.util.ArrayList;

public class RatingManager {
    private MovieManager movieManager;
    public String filename;

    public RatingManager(){
        this.movieManager = new MovieManager();
        this.filename = movieManager.filename;
    }

    public RatingManager(MovieManager movieManager){
        this.movieManager = movieManager;
        this.filename = movieManager.filename;
    }

    public void setMovieManager(MovieManager movieManager){
        this.movieManager = movieManager;
    }

    public MovieManager getMovieManager(){
        return this.movieManager;
    }

    public void createRating(Movie movie, String username, double score, String comment) {
        int i;
        if (RatingVerifier.isValidRating(movie, username)) {
            Rating rating = new Rating(username, score, comment);
            ArrayList<Movie> movieList = this.movieManager.readMovie();
            ArrayList<Movie> newMovieList = new ArrayList<Movie>();
            for (i = 0; i < movieList.size(); i++){
                Movie m = movieList.get(i);
                if (m.getName().equals(movie.getName())){
                    ArrayList<Rating> ratingList = m.getRatingList();
                    ratingList.add(rating);
                    m.setRatingList(ratingList);
                }
                newMovieList.add(m);
            }
            System.out.println("You have successfully rated the movie!");
            this.movieManager.updateFile(filename, newMovieList);
        } else {
            //
        }
    }
}