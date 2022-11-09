package views;

import controllers.MovieManager;
import interfaces.Movie;

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
    	System.out.printf("Option List:\n" +
    						"1. Search according to movie title" + "\n"
    						+ "2. Search according to movie type" + "\n"
    						+ "3. Display all movies" + "\n"
    						+ "4. Exit" + "\n"
    						+ "Please select an option: ");
        switch (choice = InputManager.getInt()) {
        case 1:
            searchByTitle();
            break;
        case 2:
            searchByType();
            break;
        case 3:
            displayAll();
            break;
        case 4:
            System.out.println("Exiting...");
            return;
        default:
            System.out.println("Invalid input! Please try again.");
        }
    }
    
    public void searchByType(){
        System.out.println("Please enter the movie type: ");
        type = InputManager.getString();
        ArrayList<Movie> movieListing = movieManager.readMovieDetail(
        		movieManager.type, type);
        if(movieListing.isEmpty()){
            System.out.println("Unable to find any movies!");
        }
        else{
        	for(int i = 0; i < movieListing.size(); i++) {
        		printMovie(movieListing.get(i));
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
        ArrayList<Movie> movieListing = movieManager.readMovie();
        if(movieListing.isEmpty()){
            System.out.println("No movies to be listed!");
            return false;
        }
        else{
        	for(int i = 0; i < movieListing.size(); i++) {
        		printMovie(movieListing.get(i));
        	}
        	return true;
        }
    }
    
    public void printMovie(Movie movie){
        int ID = movie.getID();
        String name = movie.getName();
        String startDate = movie.getStringStartDate();
        System.out.printf("interfaces.Movie ID: " + ID + "\n" +
        				  "interfaces.Movie Name: " + name + "\n");
    }
}
