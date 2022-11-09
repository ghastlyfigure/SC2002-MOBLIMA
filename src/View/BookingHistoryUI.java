package View;


import Controller.InputManager;
import Controller.TransactionManager;
import Model.Transaction;

import java.util.ArrayList;

public class BookingHistoryUI {

    private String email;
    private TransactionManager transactionManager;

    public BookingHistoryUI() {
        this.transactionManager = new TransactionManager();
    }

    public BookingHistoryUI(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    public void main(){
        System.out.print("Please enter your email: ");
        email = InputManager.getEmail();
        System.out.println("\nBooking History (" + email + "):\n");
        display();
    }

    public void display(){
        int i;
        String choice;
        ArrayList<Transaction> transactionList = transactionManager.readTransactionByUsername(email);
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
