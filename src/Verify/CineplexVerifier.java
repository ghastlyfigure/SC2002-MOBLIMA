package Verify;

import Controller.CineplexManager;
import Model.Cinema;
import Model.Cineplex;

import java.util.ArrayList;

public class CineplexVerifier {
	static CineplexManager cineplexManager = new CineplexManager();

    /**
     * Verify if cineplex can be created using parameters passed
     * @param name          Cineplex's name
     * @param cinemaList    List of Cinemas in the Cineplex
     * @return boolean      Return true if cineplex can be created, else false
     */
    public static boolean isValidCineplex(String name, ArrayList<Cinema> cinemaList) {
        boolean isValid = true;
        if (isExistingCineplex(name)){
        	System.out.println("Model.Cineplex already exists!");
            isValid = false;
        }
        if (isEmptyName(name)) {
        	System.out.println("Model.Cineplex Name cannot be empty!");
        	isValid = false;
        }
        if (!hasThreeCinema(cinemaList)) {
        	System.out.println("Model.Cineplex must have at least three cinemas!");
            isValid = false;
        }
        return isValid;
    }

    /**
     * Verify if cineplex exists using parameters passed
     * @param name      Name of cineplex to check
     * @return boolean  Return true if cineplex already exist, else false
     */
    public static boolean isExistingCineplex(String name) {
        ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
        int i;
        
        for (i = 0; i < cineplexList.size(); i++) {
            if (cineplexList.get(i).getName().equals(name))
                return true;
        }
        return false;
    }


    /**
     * Verify if name of cineplex is empty
     * @param name Name of cineplex to check
     * @return  boolean  Return true if name of cineplex is empty, else false
     */
    public static boolean isEmptyName(String name) {
        return name.equals("");
    }

    /**
     * Verify that a list of cinemas minimally has 3 cinemas
     * @param cinemaList      List of cinemas to check
     * @return  boolean  Return true if list of cinemas already exist, else false
     */
    public static boolean hasThreeCinema(ArrayList<Cinema> cinemaList) {
        return cinemaList.size() >= 3;
    }
}
