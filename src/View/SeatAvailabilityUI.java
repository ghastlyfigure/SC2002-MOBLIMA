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

    /**
     * Temporary seat list storage & all necessary managers (movie time slot, cineplex, cinema)
     */
    private SeatList seatList;
    private MovieTimeSlotManager movieSlotManager;
    private CineplexManager cineplexManager;
    private CinemaManager cinemaManager;

    /**
     * Default Constructor - call the constructors for the necessary manager
     */
    public SeatAvailabilityUI(){
        this.cinemaManager = new CinemaManager();
        this.cineplexManager = new CineplexManager();
        this.movieSlotManager = new MovieTimeSlotManager();
    }

    /**
     * Main method to load - if there are available movie slots, user can choose one of the slots to print the layout
     */
    public void main(){
        if(slotsAvailable()){
            printSeatLayout();
        }
    }

    /**
     * Check if there are slots available.
     * If the list of cineplexes are empty, return false and return the user to the main menu
     * If there are, display cineplex list and ask the user to choose one.
     * Any invalid input will prompt the user to try again
     * If there are no available time slots within a cineplex, return false and return to main menu
     * Else, display all time slots and return true
     * @return	If there are available sessions or not
     */
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

    /**
     * Print the layout - ask the user to input cinema code and the date and time
     * If any input is invalid, the user will have to re-input
     * User can return to main menu with choice -1
     */
    public void printSeatLayout(){
        int choice = 0;
        System.out.println("================================================");
        System.out.println("Select Movie Timeslot to view Booking History");
        System.out.println("================================================");
        System.out.println("Please enter Cinema Code (eg. CAU, AMK): ");
        String cinemaCode = InputManager.getString();
        System.out.println("Please enter Date and Time (DD/MM/YYYY HH:MM) : ");
        LocalDateTime timeslotDate = InputManager.getDateTime();

        MovieTimeslot timeSlot = movieSlotManager.getSpecificTimeslot(cinemaCode, timeslotDate);

        if(timeSlot == null){
            System.out.println("Wrong input!");
            printSeatLayout();
        }
        else{
            System.out.println("Seat Layout: ");
            seatList = timeSlot.getSeatList();
            seatList.displaySeatLayout();
        }

        boolean inLoop = true;
        while (inLoop) {
            System.out.println("Please Enter -1 to Return to Previous Menu");
            int userInput = InputManager.getInt();
            if (userInput == -1) {
                inLoop = false;
            }
        }
    }
    
}
