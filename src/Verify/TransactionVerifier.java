package Verify;

import Controller.TransactionManager;
import Model.Movie;

public class TransactionVerifier {

    static TransactionManager transactionManager = new TransactionManager();
    
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
