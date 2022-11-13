package View;

import Controller.InputManager;
import Controller.MovieManager;
import Model.Movie;

import java.util.ArrayList;


public class SearchMovieUI {
    private String name;
    private String type;
    private int choice;
    private MovieManager movieManager;
    
    SearchMovieUI() {
        this.movieManager = new MovieManager();
    }
    
    SearchMovieUI(MovieManager movieManager) {
        this.movieManager = movieManager;
    }

    public void main(){
        while (choice != 4) {
            movieSearchDisplay();
        }
    }

    public void movieSearchDisplay(){
    	System.out.printf("\nOption List:\n" +
    						"\t1. Search according to movie title" + "\n"
    						+ "\t2. Search according to movie type" + "\n"
    						+ "\t3. Display all movies" + "\n"
    						+ "\t4. Exit" + "\n"
    						+ "\tPlease select an option: ");
        switch (choice = InputManager.getInt()) {
            case 1 -> searchByTitle();
            case 2 -> searchByType();
            case 3 -> displayAll();
            case 4 -> {
                System.out.println("Returning back to main menu");
                return;
            }
            default -> System.out.println("Invalid input! Please try again.");
        }
    }
    
    public void searchByType(){
        int i, choice;
        System.out.println();
        System.out.println("List of movie types:");
        System.out.println("\t1. 2D");
        System.out.println("\t1. 3D");
        System.out.println("\t1. Blockbuster");
        System.out.println("Please select the Movie Type by entering the number: ");

        choice = InputManager.getInt();
        switch(choice){
            case 1:
                type = "TwoD";  break;
            case 2:
                type = "ThreeD"; break;
            case 3:
                type = "Blockbuster";
        }
        ArrayList<Movie> movieListing = movieManager.readMovieDetail(
        		movieManager.type, type);
        if(movieListing.isEmpty()){
            System.out.println("Unable to find any movies!");
        }
        else{
            System.out.println("Movie List: ");
        	for(i = 0; i < movieListing.size(); i++) {
                printMovie(movieListing.get(i));
                System.out.print("\n");
        	}
        }
    }

    public void searchByTitle(){
        System.out.println("Please enter the movie name: ");
        name = InputManager.getString();
        ArrayList<Movie> movieListing = movieManager.readMovieDetail(
        		movieManager.name, name);
        if(movieListing.isEmpty()){
            System.out.println("Unable to find any movies!");
        }
        else{
        	for(int i = 0; i < movieListing.size(); i++) {
        		printMovie(movieListing.get(i));
        	}
        }
    }
    
   
    public boolean displayAll(){
        int i;
        ArrayList<Movie> movieListing = movieManager.readMovie();
        // checks whether movieListing has movies in it
        if(movieListing.isEmpty()){
            System.out.println("No movies to be listed!");
            return false;
        }
        else{
        	for(i = 0; i < movieListing.size(); i++) {
        		printMovie(movieListing.get(i));
                System.out.print("\n");
        	}
        	return true;
        }
    }
    
    public void printMovie(Movie movie){
        int i;
        String startDate = movie.getStringStartDate();
        System.out.printf("\tMovie ID: " + movie.getID() + "\n" +
        				  "\tMovie Name: " + movie.getName() + "\n");

        System.out.printf("\tStart Date: " + movie.getStartDate() + "\n" +
                "\tEnd Date: " + movie.getEndDate() + "\n" +
                "\tDirector: " + movie.getDirector() + "\n");
        System.out.printf("\tCast List: ");
        for(i = 0; i < movie.getCastList().size() - 1; i++){
            System.out.printf(movie.getCastList().get(i) + ", ");
        }
        System.out.println(movie.getCastList().get(movie.getCastList().size() - 1));
    }
}
