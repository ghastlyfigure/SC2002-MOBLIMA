package View;

import Controller.*;
import Model.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class PurchaseTicketUI {

    /**
     * Variables required to make a booking and purchase a ticket(movieName, cinemaID, selected date and time)
     */
    private String movieName;
    private String cinemaID;
    private LocalDateTime selectedDateTime ;

    /**
     * Variables to store the selected cinema, slot and seating arrangement
     */
    private Cinema selectedCinema;
    private MovieTimeslot selectedSlot;
    private SeatList seatList;

    /**
     * Variable to store the number of tickets
     */
    private int tickets;

    /**
     * All managers (cineplex, cinema, movie, movie time slot, ticket cost, transaction)
     */
    private CineplexManager cineplexManager ;
    private CinemaManager cinemaManager ;
    private MovieManager movieManager ;
    private MovieTimeSlotManager movieSlotManager ;
    private TicketCostManager costManager ;
    private TransactionManager transactionManager;

    /**
     * Default constructors to call all managers
     */
    public PurchaseTicketUI(){
        this.cineplexManager = new CineplexManager();
        this.cinemaManager = new CinemaManager();
        this.movieManager = new MovieManager();
        this.transactionManager = new TransactionManager();
        this.costManager = new TicketCostManager();
        this.movieSlotManager = new MovieTimeSlotManager();
    }

    /**
     * Set the cinema controller to a specific state
     * @param cinemaManager The state for the cinema controller
     */
    public void setCinemaManager(CinemaManager cinemaManager){
        this.cinemaManager = cinemaManager;
    }

    /**
     * Set the movie manager to a specific state
     * @param movieManager The state for the movie controller
     */
    public void setMovieManager(MovieManager movieManager){
        this.movieManager = movieManager;
    }

    /**
     * Set the transaction manager to a specific state
     * @param transactionManager The state for the transaction controller
     */
    public void setTransactionManager(TransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    /**
     * Set the movie slot manager to a specific state
     * @param movieTimeSlotManager The state for the session controller
     */
    public void setMovieSlotManager(MovieTimeSlotManager movieTimeSlotManager){
        this.movieSlotManager = movieSlotManager;
    }

    /**
     * Main method for the UI - display all movies available for streaming and ask them to pick the cineplex to view
     * If no cinemas are available to show the movie for a cineplex, user will be asked to choose the cineplex again
     * Then, pass to the other methods to select date, time and show ticket costs
     */
    public void main(){
        ArrayList<Cinema> availableCinemas = new ArrayList<Cinema>();
        int choice;

        System.out.println("======================");
        System.out.println("Movies Streaming Now: ");
        System.out.println("======================");
        ArrayList<Movie> movies = movieManager.readMovie();
        for (Movie movie : movies) {
            if (movie.getMovieStatus() == MovieStatus.Now_Showing || movie.getMovieStatus() == MovieStatus.Preview) {
                System.out.println(movie.getName());
            }
        }
        System.out.println("=======================");
        System.out.println();

        System.out.println("Select Cineplex: ");
        System.out.println();
        ArrayList<Cineplex> cineplexes = cineplexManager.getAllCineplex();
        for(int i = 0; i < cineplexes.size(); i++){
            System.out.println((i+1) + ". " + cineplexes.get(i).getName());
        }

        while(availableCinemas.size() == 0){
            choice = InputManager.getInt();
            if(choice == -1){
                System.out.println("Returning...");
                return;
            }
            if (choice < 0 || choice > cineplexes.size())
                System.out.println("Invalid Input! Please select again.");

            else{
                availableCinemas = listAvailableSlots(cineplexes.get(choice - 1).getName());
                if(availableCinemas.size() == 0){
                    System.out.println("No Currently Available Slots for this Cineplex. Choose another Cineplex.");
                    System.out.println("========================================================================");
                    for(int i = 0; i < cineplexes.size(); i++){
                        System.out.println((i+1) + ". " + cineplexes.get(i).getName());
                    }
                    System.out.println("Enter -1 to return to Guest Menu");
                }
            }
        }
        
        selectDateTime(availableCinemas);

        showTicketCosts();
    }

    /**
     * Show all available sessions for a cineplex knowing the movie
     * @param cineplexName		Name of chosen cineplex
     * @return a list of cinema that is showing the movie for that cineplex
     */
    public ArrayList<Cinema> listAvailableSlots(String cineplexName){
        MovieTimeslot slot;
        Cinema cinema;
        //boolean printedcinemaID = false; ?
        //boolean printedSeperator = false; ?
        ArrayList<Cinema> availableCinemas = new ArrayList<Cinema>();
        System.out.println();
        System.out.println("Enter movie name to view currently available slots: ");
        movieName = InputManager.getString();

        ArrayList<Movie> movies = movieManager.readMovieDetail(MovieManager.name, movieName);
        for (Movie movie : movies) {
            if (movie.getMovieStatus().equals(MovieStatus.Coming_Soon) || movie.getMovieStatus().equals(MovieStatus.End_of_Showing)) {
                System.out.println("Movie no longer available for booking as it is No Longer Showing!");
                System.out.println();
                return availableCinemas;
            }
        }

        ArrayList<Cinema> cineplexCinemas = cinemaManager.getCinemaOfCineplex(cineplexName);
        System.out.println();
        for(int i = 0; i < cineplexCinemas.size(); i++){

            cinema = cineplexCinemas.get(i);
            for(int j = 0; j < cinema.getMovieTimeslot().size(); j++){
                slot = cinema.getMovieTimeslot().get(j);
                if(slot.getMovie().getName().equals(movieName)){
                    //if(!printedCinemaID) {
                    System.out.println("=====================================================");
                    System.out.println("Cinema ID: " + cinema.getCinemaCode() + "   Cinema type: " + cinema.getCinemaType());
                    System.out.println();
                    System.out.println("\tAvailable Time Slots for -" + movieName + "- at this Cinema:");
                    System.out.println();
                    //}
                    //printedCinemaID = true;
                    System.out.println("\tDate: " + slot.getTimeslot().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
                    System.out.println("======================================================");
                    System.out.println();
                    availableCinemas.add(cinema);

                }
            }
        }
        return availableCinemas;
    }

    /**
     * Allow user to select date and time for their selected time slot
     * @param cinemaList		List of available cinemas
     */
    public void selectDateTime(ArrayList<Cinema> cinemaList){
        MovieTimeslot slot;
        Cinema cinema;
        
        System.out.println("Select your Cinema ID/code (eg. AMK, CAU): ");
        cinemaID = InputManager.getString();
        System.out.println("Select viewing date and time (Format: DD/MM/YYYY HH:MM): ");
        selectedDateTime = InputManager.getDateTime();
        for(int i = 0; i < cinemaList.size(); i++){
            cinema = cinemaList.get(i);
            if(cinema.getCinemaCode().equals(cinemaID)){
                for(int j = 0; j < cinema.getMovieTimeslot().size(); j++){
                    slot = cinema.getMovieTimeslot().get(j);
                    if(slot.getTimeslot().equals(selectedDateTime)){
                        seatList = slot.getSeatList();
                        selectedCinema = cinema;
                        selectedSlot = slot;
                        break;
                    }
                }
            }
        }
    }

    /**
     * Displaying the cost of the tickets
     * Pass to method bookSeats if the user wants to reserve seats
     */
    public void showTicketCosts(){
        double cost = 0;
        System.out.print("Enter the number of Tickets you want to purchase: ");
        tickets = InputManager.getInt();
        for(int i = 0; i < tickets; i++){
            boolean validInput = false;
            while(!validInput){
                System.out.println("Enter age type for the " + (i+1) + " ticket (Student, Senior, Standard): ");
                String ageType = InputManager.getString();
                switch (ageType) {
                    case "Student" -> {
                        cost += costManager.calculateCost(selectedCinema, selectedSlot, TicketCostType.Student);
                        validInput = true;
                    }
                    case "Senior" -> {
                        cost += costManager.calculateCost(selectedCinema, selectedSlot, TicketCostType.SeniorCitizen);
                        validInput = true;
                    }
                    case "Standard" -> {
                        cost += costManager.calculateCost(selectedCinema, selectedSlot, TicketCostType.Normal);
                        validInput = true;
                    }
                    default -> System.out.println("Wrong input!");
                }
            }
        }

        System.out.println("Total ticket cost: " + cost + "SGD");
        // TODO: change to do while loop
        int choice = 0;
        while(choice == 0 || choice == 1){
            System.out.println("Do you want to proceed? Yes(1) / No(0): ");
            choice = InputManager.getInt();
            if(choice == 1){
                bookSeats();
                break;
            }
            else if(choice == 0){
                System.out.println("Returning...");
                return;
            }
            else{
                System.out.println("Invalid choice input, Input 1 for Yes or 0 for No");
            }
        }
    }

    /**
     * Make the transaction with user's name, email, mobile number
     */
    public void bookSeats(){
        int seatID;
        int ticketsToBeBooked = tickets;
        int c = 1;
        boolean seatBooked = false;
        seatList.displaySeatLayout();

        do{
            System.out.println("Choose Seat ID for Ticket " + c);
            seatID = InputManager.getInt();
            seatBooked = movieSlotManager.assignSeat(seatList, seatID, selectedSlot.getTimeslotID());
            if(seatBooked){
                ticketsToBeBooked--;
                c++;
            }
        }while(ticketsToBeBooked > 0);
        makeTransaction();
    }

    public void makeTransaction(){
        System.out.print("Enter your Name: ");
        String name = InputManager.getString();
        System.out.print("Enter your Email: ");
        String email = InputManager.getEmail();
        System.out.print("Enter your Mobile Number: ");
        String mobileNumber = InputManager.getMobileNumber();
        Movie movie = selectedSlot.getMovie();
    
        transactionManager.createTransaction(name, email, cinemaID, movie, mobileNumber);
    
        System.out.println("Completed transaction successfully!");
        // TODO: Debug logical error, after transaction completed, message "Do you want to proceed" displays
    }
}



