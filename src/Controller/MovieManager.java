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

    /**
     * Declaring constants to be used
     * Ensures better readibility
     */
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

    /**
     * File name of database file to access
     */
    public final static String filename = "database/movies.txt";
    public MovieTimeSlotManager timeslotManager;

    public MovieManager() {
        this.timeslotManager = new MovieTimeSlotManager();
    } 

    public MovieManager(MovieTimeSlotManager timeslotManager) {
        this.timeslotManager = timeslotManager;
    }

    /**
     * Create a new movie and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new movie object, and save the file
     * If database file does not exist, write movie object to a new file and save
     * @param name              Title of movie
     * @param type              Type of movie
     * @param synopsis          Synopsis of movie
     * @param rating            Rating of movie
     * @param duration          Duration of movie
     * @param startDate         Release date of movie
     * @param endDate           End date of movie
     * @param director          Director of movie
     * @param castList          List of cast in movie
     */
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

    /**
     * Return the ID of the last movie in the database
     * @return int      ID of last Movie in the database
     */
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

    /**
     * Read every Movie in the database file
     * @return Movie    Return list of Movies if found, else empty list
     */
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

    /**
     * Read every Movie in database file using value of a given attribute
     * @param choice          Name of cineplex to find
     * @param attribute       Value of given attribute to search for
     * @return Movie          Return list of Movies if found, else null object
     */
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

    /**
     * Read Movie in database file using ID passed in
     * @param ID            ID of cineplex to find
     * @return movie        Return Cineplex if found, else null object
     */
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

    /**
     * Update a Movie's attribute in database file using given movie ID
     * @param choice        Attribute of movie to be updated
     * @param movieID       ID of Movie to find
     * @param newValue      New value of Movie's attribute
     */
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

    /**
     * Overwrite database file with new data of list of Movie
     * @param filename      Filename to check
     * @param movieList          New ArrayList of Movies to be written to the file
     */
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

    /**
     * Delete a Movie in the database file corresponding to the ID attribute passed in
     * @param ID  ID of Movie to delete
     */
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
