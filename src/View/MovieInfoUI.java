package View;


import Controller.InputManager;
import Controller.MovieManager;
import Model.Movie;

public class MovieInfoUI {
    /**
     * Movie manager and SearchMovieUI() method
     */
    private final MovieManager moviesMgr;
    private final SearchMovieUI searchMovieUI = new SearchMovieUI();

    /**
     * Default constructor
     */
    MovieInfoUI() {
        this.moviesMgr = new MovieManager();
    }

    /**
     * Constructor with the state of the movie manager
     * @param moviesMgr	State of movie manager
     */
    MovieInfoUI(MovieManager moviesMgr) {
        this.moviesMgr = moviesMgr;
    }

    /**
     * Main method for the UI - get the movie ID from the user, and print the details of that movie
     */
    public void main(){
        if(searchMovieUI.isEmpty()){
            System.out.println("Movie List is Empty!\n" +
                               "Returning back to Main Menu...");
            return;
        }
        System.out.println("Please enter the ID of the movie to view more details\n" +
                           "(Enter -1 to return to Guest Menu)");

        int ID = InputManager.getInt();
        if(ID == -1){
            return;
        }
        Movie movie = moviesMgr.readMovieID(ID);
        if(movie == null){
            System.out.println("Movie does not exist!\n" +
                               "Returning back to main menu");
        }
        else{
            System.out.println(movie.displayMovie());
        }
    }
}
