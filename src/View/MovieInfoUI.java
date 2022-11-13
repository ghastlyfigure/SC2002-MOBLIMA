package View;


import Controller.InputManager;
import Controller.MovieManager;
import Model.Movie;

public class MovieInfoUI {
    private MovieManager moviesMgr;
    private SearchMovieUI searchMovieUI = new SearchMovieUI();

    MovieInfoUI() {
        this.moviesMgr = new MovieManager();
    }

    MovieInfoUI(MovieManager moviesMgr) {
        this.moviesMgr = moviesMgr;
    }

    public void main(){
        if(!searchMovieUI.displayAll()){
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
