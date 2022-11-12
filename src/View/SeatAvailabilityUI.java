package View;

import Controller.CinemaManager;
import Controller.CineplexManager;
import Controller.InputManager;
import Controller.MovieTimeSlotManager;
import Model.Cinema;
import Model.Cineplex;
import Model.MovieTimeslot;
import Model.SeatList;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SeatAvailabilityUI {
    
    private SeatList seatList;
    private MovieTimeSlotManager movieSlotManager;
    private CineplexManager cineplexManager;
    private CinemaManager cinemaManager;

    public SeatAvailabilityUI(){
        this.cinemaManager = new CinemaManager();
        this.cineplexManager = new CineplexManager();
        this.movieSlotManager = new MovieTimeSlotManager();
    }

    public void main(){
        if(slotsAvailable()){
            printSeatLayout();
        }
    }

    private boolean slotsAvailable(){
        ArrayList<Cineplex> cineplexes = cineplexManager.getAllCineplex();
        boolean validInput = false;
        int choice = 0;
        int i, j;

        if(cineplexes.isEmpty()){
            System.out.println("Cineplex List is empty!\n" +
                               "Returning back to main menu");
            return false;
        }
        else{
            System.out.println("Cineplex List: ");
            for (i = 0; i < cineplexes.size(); i++) {
                System.out.println("\t" + (i+1) + ". " + cineplexes.get(i).getName());
            }
        }

        while(!validInput){
            System.out.print("Please select a cineplex by selecting the corresponding number: ");
            choice = InputManager.getInt();
            if(choice < 1 || choice > cineplexes.size()){
                System.out.println("Wrong input!");
            }
            else{
                validInput = true;
            }
        }

        ArrayList<Cinema> cinemas = cineplexes.get(choice - 1).getCinemaList();
        int counter = 0;

        System.out.println("Available Timeslot list in cineplex " + cineplexes.get(choice-1).getName() + ": ");
        for(i = 0; i < cinemas.size(); i++){
            Cinema cinema = cinemas.get(i);
            ArrayList<MovieTimeslot> movieSlot = cinema.getMovieTimeslot();
            if(movieSlot.size() == 0){
                System.out.println("\tCinema " + (i+1) + " has no available sessions.");
            }
            for(j = 0; j < movieSlot.size(); j++){
                // TODO: reformat for readability
                System.out.println("\t" + "Cinema " + (i+1) + " " + cinema.getCinemaCode() + "\n\t   Movie: " + movieSlot.get(j).getMovie().getName()
                + "\n\t   Date: " + movieSlot.get(j).getStringTimeSlot());
                counter++;
            }
        }
        if(counter == 0){
            System.out.println("There are no available sessions in this cineplex!");
            System.out.println("Returning to menu....");
            return false;
        }
        return true;
    }

    public void printSeatLayout(){
        int choice = 0;
        System.out.println("Select movie timeslot");
        System.out.println("Please enter cinema code: ");
        String cinemaCode = InputManager.getString();
        System.out.println("Please enter date: ");
        LocalDateTime timeslotDate = InputManager.getDateTime();

        MovieTimeslot timeSlot = movieSlotManager.getSpecificTimeslot(cinemaCode, timeslotDate);

        if(timeSlot == null){
            System.out.println("Wrong input!");
            printSeatLayout();
        }
        else{
            System.out.println("Seat Layout: ");
            seatList = timeSlot.getSeatList();
            seatList.printLayout();
        }
    }
    
}
