package Controller;

import Model.Admin;

import java.io.*;
import java.util.ArrayList;

public class LoginManager {

    /**
     * File name of database file to access
     */
    public final static String filename = "database/login.txt";

    /**
     * Create a new admin and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new admin object, and save the file
     * If database file does not exist, write admin object to a new file and save
     * @param username   Email of the Admin to be created
     * @param password   Text password of Admin to be created
     */
    public void createAccount(String username, String password) {
        try {
            Admin admin = new Admin(username, password);
            ArrayList<Admin> adminList = new ArrayList<Admin>();
            File loginFile = new File(filename);
            if (loginFile.exists())
                adminList = read();
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            adminList.add(admin);
            out.writeObject(adminList);
            out.flush();
            out.close();
            } catch (IOException e) {
                //
            }
    }

    /**
     * Read every Admin in database file and return them
     * If database file not found, ignore error and return empty list
     * @return Model.{@link Admin}     Return list of Admins if found, else empty list
     */
    public ArrayList<Admin> read() {
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));
            ArrayList<Admin> adminList = (ArrayList<Admin>) in.readObject();
            in.close();
            return adminList;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        }
        return new ArrayList<Admin>();
    }

}

