package Controller;

import Model.Transaction;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class TransactionManager {

    public final static String filename = "database/transaction.txt";
    public ArrayList<Transaction> readTransactionByUsername(String username) {
        int i;
        ArrayList<Transaction> transactionList = readAllTransaction();
        ArrayList<Transaction> newTransactionList = new ArrayList<Transaction>();

        for (i = 0; i < transactionList.size(); i++){
            if (username == transactionList.get(i).getEmail()) //
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
}
