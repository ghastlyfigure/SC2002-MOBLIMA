package View;

import Controller.InputManager;
import Controller.LoginManager;
import Model.Admin;

import java.util.ArrayList;

public class LoginUI {
    /**
     * Login manager
     */
    private LoginManager loginManager;

    /**
     * Default constructor to call login manager
     */
    public LoginUI(){
        loginManager = new LoginManager();
    }

    /**
     * Main method to display when the user is logging in
     * If the user's input is not valid, ask them to enter again or exit
     * @return boolean  return true if user has logged in successfully, else return false
     */
    public boolean main() {
        boolean validLogin = false;
        boolean exit = false;
        int choice;

        while(!exit){
            validLogin = verifyLogin();
            if(!validLogin){
                System.out.println();
                System.out.println("Invalid Username or Password!");
                System.out.println();
                System.out.println("Option List:");
                System.out.println("\t1. Enter Again");
                System.out.println("\t2. Exit");
                System.out.println("\tPlease select an Option: ");
                choice = InputManager.getInt();
                if(choice == 2){
                    exit = true;
                    System.out.println("Exiting...");
                }
            }
            else{
                System.out.println("You have logged in successfully.\n\n");
                return true;
            }
        }
        return false;
    }
    /**
     * Verifying a user's logging in (admin/movie goer) by checking with the database
     * @return boolean  return true if user has been verified based on the email and password entered
     */
    public boolean verifyLogin() {
        String username, password;
        System.out.println();
        System.out.println("Please enter username: ");
        username = InputManager.getString();
        System.out.println("Please enter password: ");
        password = InputManager.getString();

        ArrayList<Admin> adminList = loginManager.read();
        for(int i = 0; i < adminList.size(); i++){
            if(username.equals(adminList.get(i).getUsername()) && password.equals(adminList.get(i).getPassword())){
                return true;
            }
        }
        return false;
    }
}
