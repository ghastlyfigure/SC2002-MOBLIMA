package interfaces;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.io.Serializable;

public class Movie implements Serializable{
	private int ID;
	private String name;
	private MovieType type;
	private String synopsis;
	private String rating;
	private String director;
	private ArrayList<String> castList;
	private double duration;
	private LocalDate startDate;
	private LocalDate endDate;
	private ArrayList<Rating> ratingList;
	
	Movie(int ID, String name, MovieType type, String synopsis,
		  String rating, double duration, LocalDate startDate, LocalDate endDate,
		  String director, ArrayList<String> castList){
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.synopsis = synopsis;
		this.rating = rating;
		this.director = director;
		this.castList = castList;
		this.duration = duration;
		this.startDate = startDate;
		this.endDate = endDate;
		this.director = director;
		this.ratingList = new ArrayList<Rating>();
	}
	
	void setID(int ID) {
		this.ID = ID;
	}
	
	int getID() {
		return ID;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getName() {
		return name;
	}
	
	void setType(MovieType type) {
		this.type = type;
	}
	
	MovieType getType() {
		return type;
	}
	
	void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	String getSynopsis() {
		return synopsis;
	}
	
	void setRating(String rating) {
		this.rating = rating;
	}
	
	String getRating() {
		return rating;
	}
	
	void setDirector(String director) {
		this.director = director;
	}
	
	String getDirector() {
		return director;
	}
	
	void setCastList(ArrayList<String> castList) {
		this.castList = castList;
	}
	
	ArrayList<String> getCastList() {
		return castList;
	}
	
	void setDuration(double duration) {
		this.duration = duration;
	}
	
	double getDuration() {
		return duration;
	}
	
	void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	LocalDate getStartDate() {
		return startDate;
	}
	
	String getStringStartDate() {
		return startDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}
	
	void setendDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	LocalDate getEndDate() {
		return endDate;
	}
	
	String getStringEndDate() {
		return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}
	
	void setRatingList(ArrayList<Rating> rating) {
		this.ratingList = rating;
	}
	
	public ArrayList<Rating> getRatingList(){
		return ratingList;
	}
	
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

        
        return "ID: " + getID() + "\n"
        		+ "Title: " + getName() + "\n"
                + "Type: " + getType() + "\n"
                + "Status: " + getMovieStatus().toString() + "\n"
                + "Synopsis: " + getSynopsis() + "\n"
                + "interfaces.Rating: " + getRating() + "\n"
                + "Duration: " + String.valueOf(getDuration()) + " hour(s)\n"
                + "Release date: " +  getStringStartDate() + "\n"
                + "End date: " +  getStringEndDate() + "\n"
                + "Director: " + getDirector() + "\n"
                + "Cast: " + castList + "\n"
                + "Overall review rating: " + getAverageScore() + "\n"
                + "Reviews: \n\n" + ratingList + "\n";
    }

    public String getAverageScore(){
        double sum = 0;
        int i;
        if(ratingList.size() > 1){
            for(i = 0; i < ratingList.size(); i++){
                sum += ratingList.get(i).getScore();
            }
            DecimalFormat df = new DecimalFormat();
            df.setMaximumFractionDigits(2);
            return df.format(sum/ratingList.size());
        }
        else {
            return "N/A";
        }
    }

    public MovieStatus getMovieStatus() {
    	float time;
        LocalDate currentTime = LocalDate.now();
        if (currentTime.isAfter(endDate))
            return MovieStatus.End_of_Showing;
        else {
            time = Duration.between(currentTime.atStartOfDay(), 
            						endDate.atStartOfDay()).toDays();
            if (time > 7) {
                return MovieStatus.Coming_Soon;
            } else if (time <= 7 && time > 0) {
                return MovieStatus.Preview;
            } else {
                return MovieStatus.Now_Showing;
            }
        }
    }

    
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
