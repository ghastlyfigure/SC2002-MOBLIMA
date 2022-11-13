package View;


import Controller.InputManager;
import Controller.TransactionManager;
import Model.Transaction;

import java.util.ArrayList;

public class BookingHistoryUI {
    /**
     * Variable to store user's email and transaction manager
     */
    private String email;
    private TransactionManager transactionManager;

    /**
     * Default constructor
     */
    public BookingHistoryUI() {
        this.transactionManager = new TransactionManager();
    }

    /**
     * Constructor with controller's state
     * @param transactionManager		State of transaction controller
     */
    public BookingHistoryUI(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /**
     * Main method for the UI
     */
    public void main(){
        System.out.print("Please enter your email: ");
        email = InputManager.getEmail();
        System.out.println("\nBooking History (" + email + "):\n");
        display();
    }

    /**
     * Display all transactions made with an user's email
     */
    public void display(){
        int i;
        String choice;
        ArrayList<Transaction> transactionList = transactionManager.readTransactionByEmail(email);
        if(transactionList.isEmpty()){
            System.out.println("Booking history is empty!\n" +
                                "Would you like to book a ticket now? Enter Y to start a new booking!");
            choice = InputManager.getString();
            if(choice.equals("Y") || choice.equals("y")){
                MovieInfoUI viewMovieDetailUI = new MovieInfoUI();
                viewMovieDetailUI.main();
            }
        }
        else {
            for (i = 0; i < transactionList.size(); i++) {
                System.out.println(transactionList.get(i).toString());
            }
        }
    }
}
