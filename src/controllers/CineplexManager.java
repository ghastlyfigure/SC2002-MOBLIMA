package controllers;

import interfaces.*;

import java.util.ArrayList;

public class CineplexManager {
    public final static String filename = "database/cineplex.txt";

    public final static int name = 0;
    public final static int cinema = 1;

    private static adapters.Cineplex cineplexAdapter;

    public CineplexManager() {
        cineplexAdapter = new adapters.Cineplex();
    }
    
    public void createCineplex(String name, ArrayList<Cinema> cinemaList) throws CineplexException {
        if (isExistingCineplex(name)) {
            throw new CineplexDoesNotExistException();
        }

        if (isEmptyName(name)) {
            throw new CineplexEmptyNameException();
        }

        if (hasThreeCinema(cinemaList)) {
            throw new CineplexInsufficientCinemaException();
        }

        interfaces.Cineplex cineplex = new interfaces.Cineplex(name, cinemaList);
        cineplexAdapter.insertCineplex(cineplex);
    }
    
    public Cineplex getCineplex(String name) {
        return cineplexAdapter.getCineplex(this.cineplexAdapter.getAllCineplex(), name);
    };
    
    public void updateCineplex(String oldName, String newName){
        cineplexAdapter.updateCineplex(oldName, newName);

    }

    public void deleteCineplex(String name){
        cineplexAdapter.deleteCineplex(name);
    }

    private boolean isEmptyName(String name) {
        return name.equals("");
    }

    private boolean hasThreeCinema(ArrayList<Cinema> cinemaList) {
        return cinemaList.size() >= 3;
    }

    private boolean isExistingCineplex (String name) {
        return this.getCineplex(name) != null;
    }
}
