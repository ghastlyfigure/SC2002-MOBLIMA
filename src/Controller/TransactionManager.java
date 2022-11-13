package Controller;

import Model.Transaction;
import Model.Movie;
import Verify.TransactionVerifier;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TransactionManager {

    /**
     * The file name of the database to be accessed by this manager
     */
    public final static String FILENAME = "database/transaction.txt";

    /**
     * Create a new Transaction and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new Transaction object, and save the file
     * If database file does not exist, write Transaction object to a new file and save
     * @param name          Name of user of the transaction
     * @param email         Email of user of the transaction
     * @param cinemaID      Cinema of this Transaction
     * @param movie         Movie of this Transaction
     * @param mobileNumber  Mobile number of user in this Transaction
     */
    public void createTransaction(String name, String email, String cinemaID, Movie movie, String mobileNumber){
        if(TransactionVerifier.isValidTransaction(name, email, cinemaID, movie, mobileNumber)) {
            ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
            File transactionFile = new File(FILENAME);
            if(transactionFile.exists()){
                transactionList = readAllTransaction();
            }

            try{
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(transactionFile));
                transactionList.add(new Transaction(name, email, cinemaID, movie, mobileNumber));
                out.writeObject(transactionList);
                out.flush();
                out.close();
            } catch(IOException e) { //

            }
        }
    }

    /**
     * Read and return every Transaction of given Transaction ID in the Database file
     * @param TransactionID                     Transaction ID of Transaction to find
     * @return Model.@{@link Transaction}       Return list of Transaction if found, else empty list
     */
    public ArrayList<Transaction> readTransactionByID(String TransactionID){
        ArrayList<Transaction> transactionList = readAllTransaction();
        ArrayList<Transaction> newTransactionList = new ArrayList<Transaction>();
        int i;
        for(i = 0; i < transactionList.size(); i++){
            if(transactionList.get(i).getTransactionID().equals(TransactionID))
                newTransactionList.add(transactionList.get(i));
        }

        return newTransactionList;
    }

    /**
     * Read and return every Transaction of given email in the Database file
     * @param email                         Email of user to find
     * @return Model.{@link Transaction}    Return list of Transaction if found, else empty list
     */
    public ArrayList<Transaction> readTransactionByEmail(String email) {
        int i;
        ArrayList<Transaction> transactionList = readAllTransaction();
        ArrayList<Transaction> newTransactionList = new ArrayList<Transaction>();

        for (i = 0; i < transactionList.size(); i++){
            if (email.equals(transactionList.get(i).getEmail())) //
                newTransactionList.add(transactionList.get(i));
        }
        return newTransactionList;
    }

    /**
     * Read and return every Cineplex in the Database file
     * If Database file not found, return empty list
     * @return Model.{@link Transaction}    Return list of Transaction if found, else empty list
     */
    public ArrayList<Transaction> readAllTransaction() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILENAME));
            ArrayList<Transaction> transactionList = (ArrayList<Transaction>)in.readObject();
            in.close();
            return transactionList;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Transaction>();
    }

    /**
     * Delete a Transaction in the Database file, using the Transaction ID and Username
     * @param username
     * @param TransactionID
     */
    public void delete(String username, String TransactionID){
        ArrayList<Transaction> transactionList = readAllTransaction();
        ArrayList<Transaction> newTransactionList = new ArrayList<Transaction>();
        int i;

        for(i = 0; i < transactionList.size(); i++) {
            if(transactionList.get(i).getTransactionID().equals(TransactionID) && transactionList.get(i).getEmail().equals(username))
                continue;
            newTransactionList.add(transactionList.get(i));
        }

        replaceFile(FILENAME, newTransactionList);
    }

    /**
     * Overwrite Database file with new data of list of Admin
     * @param filename              Filename to check
     * @param newTransactionList    New ArrayList of Transaction to write to the file
     */
    public void replaceFile(String filename, ArrayList<Transaction> newTransactionList){
        File transactionFile = new File(filename);

        if(transactionFile.exists())
            transactionFile.delete();

        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(newTransactionList);
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}

