package Controller;

import Model.Admin;

import java.io.*;
import java.util.ArrayList;

public class LoginManager {
        public final static String filename = "database/login.txt";

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

