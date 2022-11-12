package Controller;

import Model.*;
import Verify.CinemaVerifier;

import java.util.ArrayList;

public class CinemaManager {
    private CineplexManager cineplexManager;
    public String filename;

    public final static int name = 0;
    public final static int code = 1;
    public final static int type = 2;
    public final static int seatlist = 3;
    public final static int timeslot = 4;

    public CinemaManager() {
        this.cineplexManager = new CineplexManager();
        this.filename = cineplexManager.filename;
    }

    public CinemaManager(CineplexManager cineplexManager) {
        this.cineplexManager = cineplexManager;
        this.filename = cineplexManager.filename;
    }
    
    public void setCineplexManager(CineplexManager cineplexManager){
        this.cineplexManager = cineplexManager;
    }

    public CineplexManager getCineplexManager(){
        return this.cineplexManager;
    }
    
    public void createCinema(
            String cinemaName, String cineplexName, String code, CinemaType cinemaType, SeatList seatList
    ) {
    	int i;
        if (CinemaVerifier.isValidCinema(cinemaName, code, cinemaType, seatList)){
            Cinema cinema = new Cinema(cinemaName, code, cinemaType, seatList);
            ArrayList<Cineplex> cineplexList = this.cineplexManager.getAllCineplex();
            ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();
            for (i = 0; i < cineplexList.size(); i++){
                Cineplex cineplex = cineplexList.get(i);
                if (cineplex.getName().equals(cineplexName)){
                    ArrayList<Cinema> cinemaList = cineplex.getCinemaList();
                    cinemaList.add(cinema);
                    cineplex.setCinemaList(cinemaList);
                }
                newCineplexList.add(cineplex);
            }
            this.cineplexManager.updateFile(filename, newCineplexList);
        } else {
            // do nothing
        }
    } 

    public ArrayList<Cinema> getAllCinema(){
    	int i, j;
        ArrayList<Cineplex> cineplexList = this.cineplexManager.getAllCineplex(); 
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            cineplex.getCinemaList().forEach(n->cinemaList.add(n));

        }
        return cinemaList;
    }

    public ArrayList<Cinema> getCinemaOfCineplex(String cineplexName){
    	int i, j;
        ArrayList<Cineplex> cineplexList = this.cineplexManager.getAllCineplex();
        ArrayList<Cinema> cinemaList = new ArrayList<Cinema>();

        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if(cineplex.getName().equals(cineplexName)){
            	for(j = 0; j < cineplex.getCinemaList().size(); j++) {
            		cinemaList.add(cineplex.getCinemaList().get(j));
            	}
            }
        }
        return cinemaList;
    }
    
    public ArrayList<Cinema> getCinema(int choice, Object attribute) {
        int i;
    	ArrayList<Cinema> cinemaList = getAllCinema();
        ArrayList<Cinema> newCinemaList = new ArrayList<Cinema>();
        Cinema cinema = null;

        for (i = 0; i < cinemaList.size(); i++){
            cinema = cinemaList.get(i);
            switch (choice) {
            	case name:
            		if(cinema.getCinemaName().equals((String)attribute)) {
            			newCinemaList.add(cinema);
            		}
            		break;
                case code:
                    if (cinema.getCinemaCode().equals((String)attribute)) {
                    	newCinemaList.add(cinema);
                    }
                    break;
                case type:
                    if (cinema.getCinemaType().equals((CinemaType)attribute))
                        newCinemaList.add(cinema);
                    break;
                default:
                    break;
            }
        }
        return newCinemaList;
    }
    
    public void updateCinema(int choice, String code, Object newValue) {
    	int i, j;
        ArrayList<Cineplex> cineplexList = this.cineplexManager.getAllCineplex();
        ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();

        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            ArrayList<Cinema> oldCinemaList = cineplex.getCinemaList();
            ArrayList<Cinema> newCinemaList = new ArrayList<Cinema>();

            for (j = 0; j < oldCinemaList.size(); j++){
                Cinema cinema = oldCinemaList.get(j);
                if (cinema.getCinemaCode().equals(code)){
                    switch (choice) {
                    	case name:
                    		cinema.setCinemaName((String)newValue);
                        case type:
                            cinema.setCinemaType((CinemaType)newValue);
                            break;
                        case timeslot:
                            cinema.setMovieTimeslot((ArrayList<MovieTimeslot>)newValue);
                            break; 
                        default:
                            break;
                    } 
                }
                newCinemaList.add(cinema);
            }

            cineplex.setCinemaList(newCinemaList);
            newCineplexList.add(cineplex);
        }
        this.cineplexManager.updateFile(filename, newCineplexList);
    }

    public void removeCinema(String code) {
    	int i, j;
        ArrayList<Cineplex> oldCineplexList = this.cineplexManager.getAllCineplex();
        ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();

        for (i = 0; i < oldCineplexList.size(); i++){
            Cineplex cineplex = oldCineplexList.get(i);
                if (cineplex.getCinemaList().size() > 3){
                    ArrayList<Cinema> oldCinemaList = cineplex.getCinemaList();
                    ArrayList<Cinema> newCinemaList = new ArrayList<Cinema>();
                    
                    for (j = 0; j < oldCinemaList.size(); j++){
                        Cinema cinema = oldCinemaList.get(j);
                        if (!cinema.getCinemaCode().equals(code)) {
                        	newCinemaList.add(cinema);
                        }                           
                    }
                    cineplex.setCinemaList(newCinemaList);
                }
            newCineplexList.add(cineplex);
        }
        this.cineplexManager.updateFile(filename, newCineplexList);
    } 
}
