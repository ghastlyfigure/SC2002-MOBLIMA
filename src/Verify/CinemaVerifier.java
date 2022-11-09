package Verify;



import Controller.CinemaManager;
import Model.Cinema;
import Model.CinemaType;
import Model.SeatList;

import java.util.ArrayList;

public class CinemaVerifier {
	static CinemaManager cinemaManager = new CinemaManager();

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
    
    public static boolean isExistingCinema(String code) {
    	int i;
        ArrayList<Cinema> cinemaList = cinemaManager.getAllCinema();

        for (i = 0; i < cinemaList.size(); i++) {
            if (cinemaList.get(i).getCinemaCode().equals(code))
                return true;
        }

        return false;
    }
    
    public static boolean isEmptyName(String name) {
    	if(name.equals("")) {
    		return true;
    	}
    	return false;
    }

    public static boolean isValidCode(String code) {
        if (code.length() == 3) {
            return true;
        } 
        else {
            return false;
        }
    }

    public static boolean isEmptyCode(String code) {
    	if(code.equals("")) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
}
