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

    public final static String filename = "database/transaction.txt";

    public void createTransaction(String name, String email, String cinemaID, Movie movie, String mobileNumber){
        if(TransactionVerifier.isValidTransaction(name, email, cinemaID, movie, mobileNumber)) {
            ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
            File transactionFile = new File(filename);
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

    public ArrayList<Transaction> readAllTransaction() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            ArrayList<Transaction> transactionList = (ArrayList<Transaction>)in.readObject();
            in.close();
            return transactionList;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Transaction>();
    }

    public void delete(String username, String TransactionID){
        ArrayList<Transaction> transactionList = readAllTransaction();
        ArrayList<Transaction> newTransactionList = new ArrayList<Transaction>();
        int i;

        for(i = 0; i < transactionList.size(); i++) {
            if(transactionList.get(i).getTransactionID().equals(TransactionID) && transactionList.get(i).getEmail().equals(username))
                continue;
            newTransactionList.add(transactionList.get(i));
        }

        replaceFile(filename, newTransactionList);
    }

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

