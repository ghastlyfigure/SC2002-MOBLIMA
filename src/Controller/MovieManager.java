package Controller;

import Model.Movie;
import Model.MovieType;
import Model.Rating;
import Verify.MovieVerifier;

import java.time.LocalDate;
import java.util.ArrayList;
import java.io.File;  
import java.io.IOException;  
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class MovieManager {
	public final static int ID = 0;
    public final static int name = 1;
    public final static int type = 2;
    public final static int synopsis = 3;
    public final static int rating = 4;
    public final static int duration = 5;
    public final static int start_date = 6;
    public final static int end_date = 7;
    public final static int director = 8;
    public final static int cast = 9;
    public final static int review = 10;
    
    public final static String filename = "database/movies.txt";
    public MovieTimeSlotManager timeslotManager;

    public MovieManager() {
        this.timeslotManager = new MovieTimeSlotManager();
    } 

    public MovieManager(MovieTimeSlotManager timeslotManager) {
        this.timeslotManager = timeslotManager;
    } 
	
	public void createMovie(String name, MovieType type, String synopsis,
                            String rating, double duration, LocalDate startDate,
                            LocalDate endDate, String director, ArrayList<String> castList) {
		if (MovieVerifier.isValidMovie(name, type, synopsis,
									 rating, duration, startDate, endDate,
									 director, castList)) {
			Movie movie = new Movie(getLastID() + 1, name, type, synopsis, rating, duration, startDate, endDate, director, castList);
            ArrayList<Movie> movieListing = new ArrayList<Movie>();
            File movieFile = new File(filename);
            if(movieFile.exists()) {
            	movieListing = readMovie();
            }
            try {
            	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
                movieListing.add(movie);
                out.writeObject(movieListing);
                out.flush();
                out.close();
            } catch (IOException e) {
                //
            }
		}
    } 
	
	public int getLastID(){
		int lastId = -1;
        int movieID;
        ArrayList<Movie> allData = readMovie();
        for (int i=0; i<allData.size(); i++){
            movieID = allData.get(i).getID();
            if (movieID > lastId)
                lastId = movieID;
        }
        return lastId;
    }
	
	public ArrayList<Movie> readMovie() {
        try {  	
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            ArrayList<Movie> movieListing = (ArrayList<Movie>) in.readObject();
            in.close();
            return movieListing;
        } catch (ClassNotFoundException | IOException e) {
        	//
        } 
        return new ArrayList<Movie>();
    }
	
	public ArrayList<Movie> readMovieDetail(int choice, Object attribute) {
        int i;
        ArrayList<Movie> movieListing = readMovie();
        ArrayList<Movie> newMovieListing = new ArrayList<Movie>();
        for (i = 0; i < movieListing.size(); i++){
            Movie movie = movieListing.get(i);

            switch(choice) {
                case name:
                    if (movie.getName().equals(attribute))
                        newMovieListing.add(movie);
                    break;
                case type:
                    if (movie.getType().toString().equals((String)attribute))
                        newMovieListing.add(movie);
                    break;
                case rating:
                    if (movie.getRating().equals(attribute))
                    	newMovieListing.add(movie);
                    break;
                case start_date:
                    if (movie.getStartDate().equals(attribute))
                    	newMovieListing.add(movie);
                    break;
                case end_date:
                    if (movie.getEndDate().equals(attribute))
                    	newMovieListing.add(movie);
                    break;
                default:   
                    break;
            }
        }
        return newMovieListing;
    }
	
	public Movie readMovieID(int ID) {
		int i;
        ArrayList<Movie> movieListing = readMovie();
        for (i = 0; i < movieListing.size(); i++){
            Movie movie = movieListing.get(i);
            if (movie.getID() == ID)
                return movie;
        }
        return null;
    }
	
	public void updateMovie(int choice, int movieID, Object newValue) {
        ArrayList<Movie> movieListing = readMovie();
        ArrayList<Movie> newMovieListing = new ArrayList<Movie>();
        int i;

        timeslotManager.updateMovie(choice, movieID, newValue);
                
        for (i = 0; i < movieListing.size(); i++){
            Movie movie = movieListing.get(i);
            if (movie.getID() == movieID){
                switch(choice) {
                    case ID:
                        movie.setID((int)newValue);
                        break;
                    case name:
                    	movie.setName((String)newValue);
                        break;
                    case type:
                        movie.setType((MovieType)newValue);
                        break;
                    case synopsis:
                    	movie.setSynopsis((String)newValue);
                        break;
                    case rating:
                    	movie.setRating((String)newValue);
                        break;
                    case duration:
                    	movie.setDuration((double)newValue);
                        break;
                    case start_date:
                    	movie.setStartDate((LocalDate)newValue);
                        break;
                    case end_date:
                    	movie.setendDate((LocalDate)newValue);
                        break;
                    case director:
                    	movie.setDirector((String)newValue);
                        break;
                    case cast:
                    	movie.setCastList((ArrayList<String>)newValue);
                        break;
                    case review:
                        movie.setRatingList((ArrayList<Rating>)newValue);
                        break;
                    default:   
                        break;
                }
            }
            newMovieListing.add(movie);
        }

        updateFile(filename, newMovieListing);
    }
	
	public void updateFile(String filename, ArrayList<Movie> movieList){
        File movieFile = new File(filename);
        if (movieFile.exists()) 
            movieFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(movieList);
            out.flush();
            out.close();
        } catch (IOException e) {
        	//
        }
    }
	
	public void deleteMovie(int ID) {
        ArrayList<Movie> movieListing = readMovie();
        ArrayList<Movie> updatedListing = new ArrayList<Movie>();
        timeslotManager.deleteTimeslotByMovie(ID);
        
        for (int i = 0; i < movieListing.size(); i++){
            Movie movie = movieListing.get(i);
            if (!(movie.getID() == ID)) {
            	updatedListing.add(movie);
            }
        }
        updateFile(filename, updatedListing);
    }
}
