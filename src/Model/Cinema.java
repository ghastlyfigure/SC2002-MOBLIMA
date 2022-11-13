package Model;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Cinema
 * It has a name, code, type, seat list and timeslot list in that cinema
 */
public class Cinema implements Serializable{
	private String cinemaName;
	private String cinemaCode;
	private CinemaType cinemaType;
	private SeatList seatList;
	private ArrayList<MovieTimeslot> movieTimeslot;

	/**
	 * Creates a new Cinema with the given cinema name, code, cinema type, and seat list, and movie timeslot
	 * @param cinemaName This Cinema's name.
	 * @param cinemaCode This Cinema's code.
	 * @param cinemaType This Cinema's type
	 * @param seatList This Cinema's seat list
	 */
	public Cinema(String cinemaName, String cinemaCode, CinemaType cinemaType, SeatList seatList){
		this.cinemaName = cinemaName;
		this.cinemaCode = cinemaCode;
		this.cinemaType = cinemaType;
		this.seatList = seatList;
		this.movieTimeslot = new ArrayList<MovieTimeslot>();
	}

	/**
	 * Changes the name of this Cinema
	 * @param cinemaName This Cinema's new name.
	 */
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}

	/**
	 * Gets the name of this Cinema.
	 * @return this Cinema's name.
	 */
	public String getCinemaName() {
		return cinemaName;
	}

	/**
	 * Changes the code of this Cinema
	 * @param cinemaCode This Cinema's new code.
	 *
	 */
	void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}

	/**
	 * Gets the cinema code of this Cinema.
	 * @return this Cinema's code.
	 */
	public String getCinemaCode(){
		return cinemaCode;
	}

	/**
	 * Changes the cinema type of this Cinema
	 * @param cinemaType This Cinema's new type.
	 */
	public void setCinemaType(CinemaType cinemaType) {
		this.cinemaType = cinemaType;
	}

	/**
	 * Gets the cinema type of this Cinema.
	 * @return this Cinema's cinema type.
	 */
	public CinemaType getCinemaType() {
		return cinemaType;
	}

	/**
	 * Changes the seat list of this Cinema
	 * @param seatList This Cinema's new seat list.
	 */
	void setSeatList(SeatList seatList) {
		this.seatList = seatList;
	}

	/**
	 * Gets the seat list of this Cinema.
	 * @return this Cinema's seat list.
	 */
	public SeatList getSeatList() {
		return seatList;
	}

	/**
	 * Changes the list of timeslots of this Cinema
	 * @param movieTimeslot This Cinema's new list of timeslots.
	 */
	public void setMovieTimeslot(ArrayList<MovieTimeslot> movieTimeslot) {
		this.movieTimeslot = movieTimeslot;
	}

	/**
	 * Gets the list of timeslots of this Cinema.
	 * @return this Cinema's list of timeslots.
	 */
	public ArrayList<MovieTimeslot> getMovieTimeslot() {
		return movieTimeslot;
	}

	/**
	 * String to return when this Cinema is being called
	 * @return String
	 */
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
