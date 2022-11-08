import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class MovieTimeslot implements Serializable{
    private Movie movie;
    private LocalDateTime timeslot;
    private SeatList seatList;
    private int timeslotID;

    public MovieTimeslot(Movie movie, LocalDateTime timeslot, SeatList seatList, int timeslotID) {
        this.movie = movie;
        this.timeslot = timeslot;
        this.seatList = seatList;
        this.timeslotID = timeslotID;
    }
    
    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Movie getMovie() {
        return movie;
    }
    
    public void setTimeslot(LocalDateTime timeslot) {
        this.timeslot = timeslot;
    }

    public LocalDateTime getTimeslot() {
        return timeslot;
    }
    
    public String getStringTimeSlot() {
        return timeslot.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy HH:mm"));
    }
    
    public void setSeatList(SeatList seatList) {
        this.seatList = seatList;
    }

    public SeatList getSeatList() {
        return this.seatList;
    }

    public void setTimeslotID(int timeslotID){
        this.timeslotID = timeslotID;
    }
   
    public int getTimeslotID(){
        return this.timeslotID;
    }
    
    public boolean isWeekend(){
        if(timeslot.getDayOfWeek() == DayOfWeek.SATURDAY || timeslot.getDayOfWeek() == DayOfWeek.SUNDAY){
            return true;
        }
        else{
            return false;
        }
    }

    public LocalTime getStartTime() {
        return getTimeslot().toLocalTime();
    }

    public LocalTime getEndTime() {
        return getStartTime().plusMinutes((long) getMovie().getDuration());
    }


    public String displayTimeslot() {
        return "\nTimeslot ID: " + this.getTimeslotID() 
        	 + "\nTitle: " + movie.getName() 
        	 + "\nTime: " + this.getStringTimeSlot() 
        	 + "\nWeekend: \n" + isWeekend();
    }
}
