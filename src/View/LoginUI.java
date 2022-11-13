package View;

import Controller.InputManager;
import Controller.LoginManager;
import Model.Admin;

import java.util.ArrayList;

public class LoginUI {
    private LoginManager loginManager;

    public LoginUI(){
        loginManager = new LoginManager();
    }

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
