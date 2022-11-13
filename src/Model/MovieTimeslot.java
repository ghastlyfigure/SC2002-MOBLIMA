package Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

/**
 * Represents a Movie Timeslot
 */
public class MovieTimeslot implements Serializable{
    /**
     * This timeslot's movie
     */
    private Movie movie;

    /**
     * This timeslot's date and time
     */
    private LocalDateTime timeslot;

    /**
     * This timeslot's seat list
     */
    private SeatList seatList;

    /**
     * This timeslot's ID
     */
    private int timeslotID;

    /**
     * Creates a new Movie Timeslot with the given movie, timeslot, seat list, and ID
     * @param movie This Timeslot's movie.
     * @param timeslot This Timeslot's date and time.
     * @param seatList This Timeslot's seat list
     * @param timeslotID This Timeslot's ID
     */
    public MovieTimeslot(Movie movie, LocalDateTime timeslot, SeatList seatList, int timeslotID) {
        this.movie = movie;
        this.timeslot = timeslot;
        this.seatList = seatList;
        this.timeslotID = timeslotID;
    }

    /**
     * Changes the movie of this timeslot
     * @param movie This Timeslot's new movie.
     */
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    /**
     * Gets the movie of this timeslot.
     * @return this Timeslot's movie.
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Changes the date and time of this timeslot
     * @param timeslot This Timeslot's new date and time.
     */
    public void setTimeslot(LocalDateTime timeslot) {
        this.timeslot = timeslot;
    }

    /**
     * Gets the date and time of this timeslot.
     * @return this Timeslot's date and time.
     */
    public LocalDateTime getTimeslot() {
        return timeslot;
    }

    /**
     * Gets the date and time of this timeslot in a string format for better readability
     * @return String this Timeslot's date and time in string format
     */
    public String getStringTimeSlot() {
        return timeslot.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy HH:mm"));
    }

    /**
     * Changes the seat list of this timeslot
     * @param seatList This Timeslot's new seat list.
     */
    public void setSeatList(SeatList seatList) {
        this.seatList = seatList;
    }

    /**
     * Gets the seat list of this timeslot.
     * @return this Timeslot's seat list.
     */
    public SeatList getSeatList() {
        return this.seatList;
    }

    /**
     * Changes the ID of this timeslot
     * @param timeslotID This Timeslot's new ID.
     */
    public void setTimeslotID(int timeslotID){
        this.timeslotID = timeslotID;
    }

    /**
     * Gets the ID of this timeslot.
     * @return this Timeslot's ID.
     */
    public int getTimeslotID(){
        return this.timeslotID;
    }

    /**
     * Gets the start time of this timeslot.
     * @return this Timeslot's start time.
     */
    public LocalTime getStartTime() {
        return getTimeslot().toLocalTime();
    }

    /**
     * Gets the end time of this timeslot.
     * @return this Timeslot's end time.
     */
    public LocalTime getEndTime() {
        return getStartTime().plusMinutes((long) getMovie().getDuration());
    }

    /**
     * Check if the timeslot is on a weekend
     * Ticket price is more expensive on weekends
     * @return boolean  Return true if timeslot is a weekend, else False
     */
    public boolean isWeekend(){
        if(timeslot.getDayOfWeek() == DayOfWeek.SATURDAY || timeslot.getDayOfWeek() == DayOfWeek.SUNDAY){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * String to return when this Timeslot is being called
     * @return String
     */
    public String displayTimeslot() {
        return "\nTimeslot ID: " + this.getTimeslotID() 
        	 + "\nTitle: " + movie.getName() 
        	 + "\nTime: " + this.getStringTimeSlot() 
        	 + "\nWeekend: \n" + isWeekend();
    }
}
