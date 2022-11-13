package Model;

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
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getID() {
		return ID;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setType(MovieType type) {
		this.type = type;
	}
	
	public MovieType getType() {
		return type;
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	String getSynopsis() {
		return synopsis;
	}
	
	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String getRating() {
		return rating;
	}
	
	public void setDirector(String director) {
		this.director = director;
	}
	
	public String getDirector() {
		return director;
	}
	
	public void setCastList(ArrayList<String> castList) {
		this.castList = castList;
	}
	
	public ArrayList<String> getCastList() {
		return castList;
	}
	
	public void setDuration(double duration) {
		this.duration = duration;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	
	public LocalDate getStartDate() {
		return startDate;
	}
	
	public String getStringStartDate() {
		return startDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}
	
	public void setendDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	
	public LocalDate getEndDate() {
		return endDate;
	}
	
	String getStringEndDate() {
		return endDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
	}
	
	public void setRatingList(ArrayList<Rating> rating) {
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
			// TODO: what is this bro can explain? the logic seems to be off
            time = Duration.between(currentTime.atStartOfDay(),
            						startDate.atStartOfDay()).toDays();
            if (time > 7) {
                return MovieStatus.Coming_Soon;
            } else if (time > 0) {
                return MovieStatus.Preview;
            } else {
				 // it doesnt even reach here
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
