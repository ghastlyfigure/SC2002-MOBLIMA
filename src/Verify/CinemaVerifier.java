package Verify;



import Controller.CinemaManager;
import Model.Cinema;
import Model.CinemaType;
import Model.SeatList;

import java.util.ArrayList;

public class CinemaVerifier {
	static CinemaManager cinemaManager = new CinemaManager();

    /**
     * Verify if creation of cinema is valid using parameters passed
     * @param name          Name of Cineplex that this cinema will be added to
     * @param code          Code of cinema
     * @param cinemaType    Type of cinema
     * @param seatList      Seating plan of cinema
     * @return boolean      Return true if cinema can be created, else false
     */
    public static boolean isValidCinema(String name, String code, CinemaType cinemaType, SeatList seatList){
        boolean isValid = true;

        if (isExistingCinema(code)) {
        	System.out.println("Model.Cinema already exists!");
            isValid = false; 
        }   
        if(isEmptyName(name)) {
        	System.out.println("Model.Cinema Name cannot be empty!");
        }
        if (!isValidCode(code)) {
        	System.out.println("Model.Cinema Code must be three characters long!");
        	isValid = false;
        }

        if (isEmptyCode(code)) {
        	System.out.println("Model.Cinema Code cannot be empty!");
        	isValid = false;
        }

        return isValid;
    }

    /**
     * Verify if cinema exist using cinema code passed
     * @param code      Code of cinema to check
     * @return boolean  Return true if cinema already exists, else false
     */
    public static boolean isExistingCinema(String code) {
    	int i;
        ArrayList<Cinema> cinemaList = cinemaManager.getAllCinema();

        for (i = 0; i < cinemaList.size(); i++) {
            if (cinemaList.get(i).getCinemaCode().equals(code))
                return true;
        }

        return false;
    }

    /**
     * Check if a string is empty
     * @param name            String to be checked
     * @return boolean        Return true if string is empty, else false
     */
    public static boolean isEmptyName(String name) {
        return name.equals("");
    }

    /**
     * Verify if code of cinema is of characters length 3
     * @param code      Code of cinema to check
     * @return boolean  Return true if length of cinema code is 3 characters, else false
     */
    public static boolean isValidCode(String code) {
        return code.length() == 3;
    }

    /**
     * Verify if code of cinea is empty
     * @param code      Code for cinema to check
     * @return boolean  Return true if cinema code is empty, else false
     */
    public static boolean isEmptyCode(String code) {
        return code.equals("");
    }
}
