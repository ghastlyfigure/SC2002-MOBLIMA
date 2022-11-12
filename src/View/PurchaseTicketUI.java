package View;

import Controller.*;
import Model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;


public class PurchaseTicketUI {
    
    private String movieName;
    private String cinemaID;
    private LocalDateTime selectedDateTime ;
    private Cinema selectedCinema;
    private MovieTimeslot selectedSlot;
    private SeatList seatList;

    private int tickets;

    private CineplexManager cineplexManager ;
    private CinemaManager cinemaManager ;
    private MovieManager movieManager ;
    private MovieTimeSlotManager movieSlotManager ;
    private TicketCostManager costManager ;
    private TransactionManager transactionManager;

    public PurchaseTicketUI(){
        this.cineplexManager = new CineplexManager();
        this.cinemaManager = new CinemaManager();
        this.movieManager = new MovieManager();
        this.transactionManager = new TransactionManager();
        this.costManager = new TicketCostManager();
        this.movieSlotManager = new MovieTimeSlotManager();
    }

    public void setCinemaManager(CinemaManager cinemaManager){
        this.cinemaManager = cinemaManager;
    }

    public void setMovieManager(MovieManager movieManager){
        this.movieManager = movieManager;
    }

    public void setTransactionManager(TransactionManager transactionManager){
        this.transactionManager = transactionManager;
    }

    public void setMovieSlotManager(MovieTimeSlotManager movieTimeSlotManager){
        this.movieSlotManager = movieSlotManager;
    }

    public void main(){
        ArrayList<Cinema> availableCinemas = new ArrayList<Cinema>();
        int choice;

        System.out.println("Movies streaming now: ");
        System.out.println();
        ArrayList<Movie> movies = movieManager.readMovie();
        for (Movie movie : movies) {
            if (movie.getMovieStatus() == MovieStatus.Now_Showing || movie.getMovieStatus() == MovieStatus.Preview) {
                System.out.println(movie.getName());
            }
        }
        System.out.println();

        System.out.println("Select cineplex: ");
        System.out.println();
        ArrayList<Cineplex> cineplexes = cineplexManager.getAllCineplex();
        for(int i = 0; i < cineplexes.size(); i++){
            System.out.println((i+1) + ". " + cineplexes.get(i).getName());
        }

        while(availableCinemas.size() == 0){
            choice = InputManager.getInt();
            /*else if(choice == 0){
                System.out.println("Returning...");
                return;
            }*/
            if (choice < 0 || choice > cineplexes.size())
                System.out.println("Invalid Input! Please select again.");

            else{
                availableCinemas = listAvailableSlots(cineplexes.get(choice - 1).getName());
                if(availableCinemas.size() == 0){
                    System.out.println("No Currently Available Slots for this Cineplex. Choose another Cineplex.");
                    for(int i = 0; i < cineplexes.size(); i++){
                        System.out.println((i+1) + ". " + cineplexes.get(i).getName());
                    }
                }
            }
        }
        
        selectDateTime(availableCinemas);

        showTicketCosts();
    }

    public ArrayList<Cinema> listAvailableSlots(String cineplexName){
        MovieTimeslot slot;
        Cinema cinema;
        //boolean printedcinemaID = false; ?
        //boolean printedSeperator = false; ?
        ArrayList<Cinema> availableCinemas = new ArrayList<Cinema>();
        System.out.println();
        System.out.println("Enter movie name to view currently available slots: ");
        movieName = InputManager.getString();
        ArrayList<Cinema> cineplexCinemas = cinemaManager.getCinemaOfCineplex(cineplexName);
        System.out.println();
        for(int i = 0; i < cineplexCinemas.size(); i++){

            cinema = cineplexCinemas.get(i);
            for(int j = 0; j < cinema.getMovieTimeslot().size(); j++){
                slot = cinema.getMovieTimeslot().get(j);
                if(slot.getMovie().getName().equals(movieName)){
                    //if(!printedCinemaID) {
                        System.out.println("Cinema ID: " + cinema.getCinemaCode() + "   Cinema type: " + cinema.getCinemaType());
                        System.out.println();
                        System.out.println("Available time slots for " + movieName + " at this cinema:");
                        System.out.println();
                    //}
                    //printedCinemaID = true;
                    System.out.println("\tDate: " + slot.getTimeslot());
                    System.out.println();
                    availableCinemas.add(cinema);

                }
            }
        }
        return availableCinemas;
    }

    public void selectDateTime(ArrayList<Cinema> cinemaList){
        MovieTimeslot slot;
        Cinema cinema;
        
        System.out.println("Select your cinema ID/code: ");
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

    public void showTicketCosts(){
        double cost = 0;
        System.out.print("Enter the number of tickets: ");
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
            System.out.println("Do you want to proceed? Yes(1)/No(0): ");
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
                System.out.println("Invalid choice input, enter 1 for Yes and 0 for No");
            }
        }
    }
    public void bookSeats(){
        int seatID;
        int ticketsToBeBooked = tickets;
        int c = 1;
        boolean seatBooked = false;
        seatList.printLayout();

        do{
            System.out.println("Choose seat id for ticket" + c);
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
        System.out.print("Enter your Email(username): ");
        String email = InputManager.getEmail();
        System.out.print("Enter your Mobile number: ");
        String mobileNumber = InputManager.getMobileNumber();
        Movie movie = selectedSlot.getMovie();
    
        transactionManager.createTransaction(name, email, cinemaID, movie, mobileNumber);
    
        System.out.println("Completed transaction successfully!");
        // TODO: Debug logical error, after transaction completed, message "Do you want to proceed" displays
    }
}



