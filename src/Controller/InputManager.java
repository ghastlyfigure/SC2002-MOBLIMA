package Controller;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Class is used for user input.
 * Implemented to avoid multiple declaration of Scanner, which may lead to problems.
 * Set of static methods which return desired and validated user's input.
 */
public class InputManager {
    /** Attribute scanner defined once */
    private static Scanner sc = new Scanner(System.in);

    /**
     * Receives string from user and ensures that it is not empty
     * Ask for input until it is correct.
     * @return Non-empty String inserted by user
     */
    public static String getString(){
        String input = "";
        boolean valid = false;
        while(valid == false) {
            input = sc.nextLine();
            if(input.equals("")) {
                System.out.println("\nCannot be empty, try again!");
            }
            else {
                valid = true;
            }
        }
        return input;
    }

    /**
     * Receives and verifies integer from user
     * Ask for input until it is correct
     * @return Valid integer
     */
    public static int getInt(){
        int input = -1;
        boolean valid = false;
        while(valid == false) {
            if(sc.hasNextInt() == true){
                input = sc.nextInt();
                valid = true;
            }
            else{
                System.out.println("Wrong input! (Not an int)");
            }
            sc.nextLine();
        }
        return input;
    }

    /**
     * Receives a double between 0
     * Ask for input until it is correct.
     * @return Valid Double
     */
    public static double getDouble(){
        double input = -1;
        boolean valid = false;
        while(!valid) {
            if(sc.hasNextDouble()){
                input = sc.nextDouble();
                valid = true;
            }
            else{
                System.out.println("Wrong input! (Not a double)");
            }
            sc.nextLine();
        }
        return input;
    }

    /**
     * Receives string from user which matches default email pattern
     * Ensures that string received is not empty
     * Ask for input until correct.
     * @return Non-empty String that matches email pattern
     */
    public static String getEmail(){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        String input = "";
        boolean valid = false;
        while(valid == false){
            input = sc.nextLine();
            if(pattern.matcher(input).matches()){
                valid = true;
            }
            else{
                System.out.println("Invalid email address!");
            }
        }
        return input;
    }

    /**
     * Receive date from user which matches date pattern
     * Ask for String input until it is correct
     * @return LocalDate based on user's input
     */
    public static LocalDate getDate() {
        LocalDate date = null;
        String input;
        boolean valid = false;
        while(valid == false){
            try{
                input = sc.nextLine();
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                valid = true;
            }
            catch(DateTimeParseException e){
                System.out.println("Must be of pattern DD/MM/YYYY!");
            }
        }
        return date;
    }

    /**
     * Receive date and time from user which matches date time pattern
     * Ask for String input until it is correct
     * @return LocalDateTime based on user's input
     */
    public static LocalDateTime getDateTime(){
        LocalDateTime dateTime = null;
        String input;
        boolean valid = false;
        while(valid == false){
            try{
                input = sc.nextLine();
                dateTime = LocalDateTime.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
                valid = true;
            }
            catch(DateTimeParseException e){
                System.out.println("Must be of pattern DD/MM/YYYY HH:MM!");
            }
        }
        return dateTime;
    }

    /**
     * Receives mobile number with valid Singapore pattern (8 digits, starts with 8 or 9)
     * Ask for input until it is correct.
     * @return Non-empty String that matches the pattern of mobile number in Singapore
     */
    public static String getMobileNumber(){

        String mobileNumberFormat = "\\d{8}";
        String mobileNumber = "";
        boolean validInput = false;
        while(!validInput){
            mobileNumber = sc.nextLine();
            if(mobileNumber.matches(mobileNumberFormat) && (mobileNumber.startsWith("9")) || (mobileNumber.startsWith("8"))){
                validInput = true;
            }
            else{
                System.out.println("Must be valid mobile number (8 digits long, starts with either 8 or 9)");
            }
        }

        return mobileNumber;
    }

}
