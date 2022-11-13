package Controller;

import Model.Movie;
import Model.Rating;
import Verify.RatingVerifier;

import java.util.ArrayList;

public class RatingManager {

    /**
     * The Movie Manager to be referenced by this manager
     */
    private MovieManager movieManager;

    /**
     * The file name of the database to be accessed by this manager
     */
    public String filename;

    /**
     * Default constructor
     */
    public RatingManager(){
        this.movieManager = new MovieManager();
        this.filename = movieManager.filename;
    }

    /**
     * Parameterized constructor with Movie Manager
     * @param movieManager    User-defined Movie Manager is referenced instead
     */
    public RatingManager(MovieManager movieManager){
        this.movieManager = movieManager;
        this.filename = movieManager.filename;
    }

    /**
     * Change Movie Manager referenced by this manager
     * @param movieManager      New Movie Manager for this manager
     */
    public void setMovieManager(MovieManager movieManager){
        this.movieManager = movieManager;
    }

    /**
     * Gets Movie Manager referenced by this manager
     * @return movieManager     Movie Manager for this manager
     */
    public MovieManager getMovieManager(){
        return this.movieManager;
    }

    /**
     * Create a new Rating and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new Rating object, and save the file
     * If database file does not exist, write Rating object to a new file and save
     * @param movie       Movie that this Rating will be added to
     * @param username    List of cinemas of this cineplex
     * @param score       Score given by user (0 to 5)
     * @param comment     Optional comment accompanying rating
     */
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