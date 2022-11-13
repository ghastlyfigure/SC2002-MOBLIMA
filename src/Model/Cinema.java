package Model;


import java.io.Serializable;
import java.util.ArrayList;

public class Cinema implements Serializable{
	private String cinemaName;
	private String cinemaCode;
	private CinemaType cinemaType;
	private SeatList seatList;
	private ArrayList<MovieTimeslot> movieTimeslot; 
	
	public Cinema(String cinemaName, String cinemaCode, CinemaType cinemaType, SeatList seatList){
		this.cinemaName = cinemaName;
		this.cinemaCode = cinemaCode;
		this.cinemaType = cinemaType;
		this.seatList = seatList;
		this.movieTimeslot = new ArrayList<MovieTimeslot>();
	}
	
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
	
	void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	public String getCinemaCode(){
		return cinemaCode;
	}
	
	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}
	
	public CinemaType getCinemaType() {
		return cinemaType;
	}
	
	void setSeatList(SeatList seatList) {
		this.seatList = seatList;
	}
	
	public SeatList getSeatList() {
		return seatList;
	}
	
	public void setMovieTimeslot(ArrayList<MovieTimeslot> movieTimeslot) {
		this.movieTimeslot = movieTimeslot;
	}
	
	public ArrayList<MovieTimeslot> getMovieTimeslot() {
		return movieTimeslot;
	}

	String displayCinema() {
		int i;
		String timeslot = "";
		for (i = 0; i < movieTimeslot.size() - 1; i++)
			timeslot = timeslot + movieTimeslot.get(i) + "\n";
		
		timeslot = timeslot + movieTimeslot.get(movieTimeslot.size() - 1);
		
		return "\nModel.Cinema Name: " + getCinemaName() + "\n"
			+  "Model.Cinema Code: " + getCinemaCode() + "\n"
			+  "Model.Cinema Type: " + getCinemaType() + "\n"
			+  "Model.Movie Timeslots: " + movieTimeslot.size() + "\n" + timeslot;
	}
}
