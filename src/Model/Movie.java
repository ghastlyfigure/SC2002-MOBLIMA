package Model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.io.Serializable;

/**
 * Represents a Movie
 * It has an ID, name, type, synopsis, rating, director, list of cast, duration,
 * start date, end date, and list of ratings in that movie
 */
public class Movie implements Serializable{
	/**
	 * This movie's ID
	 */
	private int ID;

	/**
	 * This movie's name
	 */
	private String name;

	/**
	 * This movie's type
	 */
	private MovieType type;

	/**
	 * This movie's synopsis
	 */
	private String synopsis;

	/**
	 * This movie's rating
	 */
	private String rating;

	/**
	 * This movie's director
	 */
	private String director;

	/**
	 * This movie's cast list
	 */
	private ArrayList<String> castList;

	/**
	 * This movie's duration
	 */
	private double duration;

	/**
	 * This movie's start date
	 */
	private LocalDate startDate;

	/**
	 * This movie's end date
	 */
	private LocalDate endDate;

	/**
	 * This movie's list of ratings
	 */
	private ArrayList<Rating> ratingList;

	/**
	 * Creates a new Movie with the given movie ID, name, type, synopsis, rating, duration, start date, end date,
	 * director, cast list, and rating list
	 * @param ID This Movie's ID.
	 * @param name This Movie's name
	 * @param type This Movie's type.
	 * @param synopsis This Movie's synopsis
	 * @param rating This Movie's rating.
	 * @param duration This Movie's duration
	 * @param startDate This Movie's start date.
	 * @param endDate This Movie's end date
	 * @param director This Movie's director.
	 * @param castList This Movie's cast list
	 *
	 */
	public Movie(int ID, String name, MovieType type, String synopsis,
				 String rating, double duration, LocalDate startDate, LocalDate endDate,
				 String director, ArrayList<String> castList){
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.synopsis = synopsis;
		this.rating = rating;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.director = director;
		this.castList = castList;
		this.ratingList = new ArrayList<Rating>();
	}

	/**
	 * Changes the ID of this Movie
	 * @param ID This Cinema's new ID.
	 */
	public void setID(int ID) {
		this.ID = ID;
	}

	/**
	 * Gets the ID of this Movie.
	 * @return this Movie's ID.
	 */
	public int getID() {
		return ID;
	}

	/**
	 * Changes the name of this Movie
	 * @param name This Movie's new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this Movie.
	 * @return this Movie's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the type of this Movie
	 * @param type This Movie's new type.
	 */
	public void setType(MovieType type) {
		this.type = type;
	}

	/**
	 * Gets the type of this Movie.
	 * @return this Movie's type.
	 */
	public MovieType getType() {
		return type;
	}

	/**
	 * Changes the synopsis of this Movie
	 * @param synopsis This Movie's new synopsis.
	 */
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	/**
	 * Gets the synopsis of this Movie.
	 * @return this Movie's synopsis.
	 */
	String getSynopsis() {
		return synopsis;
	}

	/**
	 * Changes the rating of this Movie
	 * @param rating This Movie's new rating.
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * Gets the rating of this Movie.
	 * @return this Movie's rating.
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * Changes the director of this Movie
	 * @param director This Movie's new director.
	 */
	public void setDirector(String director) {
		this.director = director;
	}

	/**
	 * Gets the director of this Movie.
	 * @return this Movie's director.
	 */
	public String getDirector() {
		return director;
	}

	/**
	 * Changes the cast list of this Movie
	 * @param castList This Movie's new cast list.
	 */
	public void setCastList(ArrayList<String> castList) {
		this.castList = castList;
	}

	/**
	 * Gets the cast list of this Movie.
	 * @return this Movie's cast list.
	 */
	public ArrayList<String> getCastList() {
		return castList;
	}

	/**
	 * Changes the duration of this Movie
	 * @param duration This Movie's new duration.
	 */
	public void setDuration(double duration) {
		this.duration = duration;
	}

	/**
	 * Gets the duration of this Movie.
	 * @return this Movie's duration.
	 */
	public double getDuration() {
		return duration;
	}

	/**
	 * Changes the start date of this Movie
	 * @param startDate This Movie's new start date.
	 */
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the start date of this Movie.
	 * @return this Movie's start date.
	 */
	public LocalDate getStartDate() {
		return startDate;
	}

	/**
	 * Gets the start date of this Movie in a string format for better readability
	 * @return String this Movie's start date in string format
	 */
	public String getStringStartDate() {
		return startDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}

	/**
	 * Changes the end date of this Movie
	 * @param endDate This Cinema's new end date.
	 */
	public void setendDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the end date of this Movie.
	 * @return this Movie's end date.
	 */
	public LocalDate getEndDate() {
		return endDate;
	}

	/**
	 * Gets the end date of this Movie in a string format for better readability
	 * @return String this Movie's end date in string format
	 */
	String getStringEndDate() {
		return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}

	/**
	 * Changes the rating list of this Movie
	 * @param rating This Cinema's new rating list.
	 */
	public void setRatingList(ArrayList<Rating> rating) {
		this.ratingList = rating;
	}

	/**
	 * Gets the rating list of this Movie.
	 * @return this Movie's rating list.
	 */
	public ArrayList<Rating> getRatingList(){
		return ratingList;
	}

	/**
	 * String to return when this Movie is being called
	 * @return String
	 */
    public String displayMovie(){
        String castList = "";
        int i;
        for (i = 0; i < getCastList().size() - 1; i++)
            castList = castList + getCastList().get(i) + ", ";
        
        castList = castList + getCastList().get(getCastList().size() - 1);

        String ratingList = "";
        for(i = 0; i < getRatingList().size();i++){
            ratingList += getRatingList().get(i).toString() + "\n\n";
        }
        
        if(ratingList.equals(""))
            ratingList = "N/A";

		String movieDetail = "ID: " + getID() + "\n"
				+ "Title: " + getName() + "\n";

		switch(getType()){
			case TwoD:
				movieDetail = movieDetail + "Type: 2D" + "\n";
				break;
			case ThreeD:
				movieDetail = movieDetail + "Type: 3D" + "\n";
				break;
			case Blockbuster:
				movieDetail = movieDetail + "Type: Blockbuster" + "\n";
				break;
		}

		switch(getMovieStatus()){
			case Coming_Soon:
				movieDetail = movieDetail + "Status: Coming Soon" + "\n";
				break;
			case Preview:
				movieDetail = movieDetail + "Status: Preview" + "\n";
				break;
			case Now_Showing:
				movieDetail = movieDetail + "Status: Now Showing" + "\n";
				break;
			case End_of_Showing:
				movieDetail = movieDetail + "Status: End of Showing" + "\n";
				break;
		}
		movieDetail = movieDetail + "Synopsis: " + getSynopsis() + "\n"
				+ "Movie Rating: " + getRating() + "\n"
				+ "Duration: " + String.valueOf(getDuration()) + " hour(s)\n"
				+ "Release Date: " +  getStringStartDate() + "\n"
				+ "End Date: " +  getStringEndDate() + "\n"
				+ "Director: " + getDirector() + "\n"
				+ "Cast: " + castList + "\n"
				+ "Overall Review Rating: " + getAverageScore() + "\n"
				+ "Reviews: \n\n" + ratingList + "\n";

        
        return movieDetail;
    }

	/**
	 * Get the average score for this Movie
	 * If there are 1 or fewer ratings, return "N/A"
	 * @return String   Average score of this Movie
	 */
    public String getAverageScore(){
        double sum = 0;
        int i;
        if(ratingList.size() > 1){
            for(i = 0; i < ratingList.size(); i++){
                sum += ratingList.get(i).getScore();
            }

            return String.format("%.1f", sum/ratingList.size());
        }
        else {
            return "N/A";
        }
    }

	/**
	 * Get the movie status of the Movie
	 * The status is derived from the difference between the start/end date and today's date
	 * If today's date is after the end date of the movie, it will return "End_Of_Showing"
	 * ELse If the Movie's start date is more than 7 days after today's date, it will return "Coming_Soon"
	 * Else If the Movie's start date is less than 7 days after today's date, it will return "Preview"
	 * Else, it will return "Now_Showing"
	 * @return MovieStatus  Movie status of this Movie
	 */
    public MovieStatus getMovieStatus() {
    	float time;
        LocalDate currentTime = LocalDate.now();
        if (currentTime.isAfter(endDate))
            return MovieStatus.End_of_Showing;
        else {
			// TODO: Movie Status calculation
            time = Duration.between(currentTime.atStartOfDay(),
            						startDate.atStartOfDay()).toDays();
            if (time > 7) {
                return MovieStatus.Coming_Soon;
            } else if (time > 0) {
                return MovieStatus.Preview;
            } else {
                return MovieStatus.Now_Showing;
            }
        }
    }

	/**
	 * Checks whether unknown object is identical to this Movie
	 * @param unknownObject     object to be compared to
	 * @return boolean  Return true if object is identical to this Movie, else false
	 */
   boolean isIdenticalMovie(Object unknownObject) {
        if (!(unknownObject instanceof Movie)) {
            return false;
        }
        Movie movie = (Movie) unknownObject;
        if(this.ID == movie.getID() &&
           this.name.equals(movie.getName()) &&
           this.type.equals(movie.getType()) &&
           this.synopsis.equals(movie.getSynopsis()) && 
           this.rating.equals(movie.getRating()) &&
           this.startDate.equals(movie.getStartDate()) && 
           this.endDate.equals(movie.getEndDate()) &&
           this.director.equals(movie.getDirector()) &&
           this.castList.equals(movie.getCastList()) &&
           this.ratingList.equals(movie.getRatingList())) {
        	return true;
        }
        else {
        	return false;
        }
        
    }
}
