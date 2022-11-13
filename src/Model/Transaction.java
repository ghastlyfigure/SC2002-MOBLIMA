package Model;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Represents a Transaction
 */
public class Transaction implements Serializable {

    /**
     * This Transaction's name
     */
    private String name;

    /**
     * This Transaction's email
     */
    private String email;

    /**
     * This Transaction's movie
     */
    private Movie movie;

    /**
     * This Transaction's mobile number
     */
    private String mobileNumber;

    /**
     * Transaction ID of the Transaction
     * In the format of XXXYYYYMMDDhhmm
     * XXX is the cinema code
     * YYYYDDMMhhmm is datetime
     */
    private String TransactionID;

    /**
     * Creates a new Transaction with the given name, email, cinema ID, movie, and mobile number
     * @param name This Transaction's name.
     * @param email This Transaction's email
     * @param cinemaID This Transaction's cinema ID.
     * @param movie This Transaction's movie
     * @param mobileNumber This Transaction's mobile number.
     */
    public Transaction(String name, String email, String cinemaID, Movie movie, String mobileNumber) {
        String formattedTimestamp = new SimpleDateFormat("YYYYMMddHHmm").format(new Date());
        this.name = name;
        this.email = email;
        this.TransactionID = cinemaID.concat(formattedTimestamp);
        this.movie = movie;
        this.mobileNumber = mobileNumber;
    }

    /**
     * Gets the name of the person that made this Transaction.
     * @return this Transaction's name
     */
    public String getPersonName(){
        return name;
    }

    /**
     * Gets the email of the person that made this Transaction.
     * @return this Transaction's email
     */
    public String getEmail(){
        return email;
    }

    /**
     * Gets the Transaction's ID
     * @return this Transaction's ID
     */
    public String getTransactionID() {
        return TransactionID;
    }

    /**
     * Gets the Transaction's movie
     * @return this Transaction's movie
     */
    public Movie getMovie(){
        return movie;
    }

    /**
     * Gets the mobile number of the person that made this Transaction.
     * @return this Transaction's mobile number
     */
    public String getMobileNumber(){
        return mobileNumber;
    }

    /**
     * String to return when this Transaction is being called
     * @return String
     */
    public String toString() {
        return "Transaction ID: " + getTransactionID() + "\t"
                + "Name: " + getPersonName() + "\n"
                + "Movie Title: " + getMovie().getName() + "\n"
                + "Mobile Number: " + getMobileNumber() + "\n";
    }
}
