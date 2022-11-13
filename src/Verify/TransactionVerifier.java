package Verify;

import Controller.TransactionManager;
import Model.Movie;

public class TransactionVerifier {

    static TransactionManager transactionManager = new TransactionManager();

    /**
     * Verify if transaction can be created based on parameters passed
     * @param name              Name of user booking the ticket(s)
     * @param email             Email of user booking the ticket(s)
     * @param cinemaID          Code of cinema selected in this transaction
     * @param movie             Movie selected in this transaction
     * @param mobileNumber      Mobile number of user booking the ticket(s)
     * @return
     */
    public static boolean isValidTransaction(String name, String email, String cinemaID, Movie movie, String mobileNumber){

        boolean isValid = true;

        if(CinemaVerifier.isExistingCinema(cinemaID) == false){
            isValid = false;
        }

        if(MovieVerifier.canBeBooked(movie) == false){
            isValid = false;
        }

        if(!isValid){
            System.out.println("Invalid Transaction");
        }

        return isValid;
    }
    
}
