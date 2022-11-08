import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	private String cinemaName;
	private String cinemaCode;
	private CinemaType cinemaType;
	private SeatList seatList;
	private ArrayList<MovieTimeslot> movieTimeslot; 
	
	Cinema(String cinemaName, String cinemaCode, CinemaType cinemaType, SeatList seatList){
		this.cinemaName = cinemaName;
		this.cinemaCode = cinemaCode;
		this.cinemaType = cinemaType;
		this.movieTimeslot = new ArrayList<MovieTimeslot>();
	}
	
	void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	String getCinemaName() {
		return cinemaName;
	}
	
	void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	String getCinemaCode(){
		return cinemaCode;
	}
	
	void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}
	
	CinemaType getCinemaType() {
		return cinemaType;
	}
	
	void setSeatList(SeatList seatList) {
		this.seatList = seatList;
	}
	
	SeatList getSeatList() {
		return seatList;
	}
	
	void setMovieTimeslot(ArrayList<MovieTimeslot> movieTimeslot) {
		this.movieTimeslot = movieTimeslot;
	}
	
	ArrayList<MovieTimeslot> getMovieTimeslot() {
		return movieTimeslot;
	}

	String displayCinema() {
		int i;
		String timeslot = "";
		for (i = 0; i < movieTimeslot.size() - 1; i++)
			timeslot = timeslot + movieTimeslot.get(i) + "\n";
		
		timeslot = timeslot + movieTimeslot.get(movieTimeslot.size() - 1);
		
		return "\nCinema Name: " + getCinemaName() + "\n"
			+  "Cinema Code: " + getCinemaCode() + "\n"
			+  "Cinema Type: " + getCinemaType() + "\n"
			+  "Movie Timeslots: " + movieTimeslot.size() + "\n" + timeslot;
	}
}
