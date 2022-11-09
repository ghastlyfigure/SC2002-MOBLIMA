package Model;

import java.io.Serializable;
import java.util.Date;
import java.text.SimpleDateFormat;

@SuppressWarnings("serial")
public class Transaction implements Serializable {

    private String name;
    private String email;
    private Movie movie;
    private String mobileNumber;
    private String TransactionID;


    public Transaction(String name, String email, String cinemaID, Movie movie, String mobileNumber) {
        String formattedTimestamp = new SimpleDateFormat("YYYYMMddHHmm").format(new Date());
        this.name = name;
        this.email = email;
        this.TransactionID = cinemaID.concat(formattedTimestamp);
        this.movie = movie;
        this.mobileNumber = mobileNumber;
    }
    public String getPersonName(){
        return name;
    }
    public String getEmail(){
        return email;
    }
    public String getTransactionID() {
        return TransactionID;
    }
    public Movie getMovie(){
        return movie;
    }
    public String getMobileNumber(){
        return mobileNumber;
    }

    public String toString(){
        return "Model.Transaction ID: " + getTransactionID() + "\t"
                + "Name: " + getPersonName() + "\n"
                + "Model.Movie: " + getMovie().getName() + "\n"
                + "Mobile number: " + getMobileNumber() + "\n";
    }
}
