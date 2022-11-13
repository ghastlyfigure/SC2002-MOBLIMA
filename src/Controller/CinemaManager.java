package Controller;

import Model.*;
import Verify.CinemaVerifier;

import java.util.ArrayList;

public class CinemaManager {

    /**
     * The Cineplex Manager to be referenced by this manager, Cinema Manager
     */
    private CineplexManager cineplexManager;

    /**
     * The file name of the database to be accessed by this manager
     */
    public String filename;

    /**
     * Declaring constants to be used in getCinema(), updateCinema()
     * Ensures better readibility
     */
    public final static int name = 0;
    public final static int code = 1;
    public final static int type = 2;
    public final static int seatlist = 3;
    public final static int timeslot = 4;

    /**
     * Default constructor
     */
    public CinemaManager() {
        this.cineplexManager = new CineplexManager();
        this.filename = cineplexManager.filename;
    }

    /**
     * Parameterized constructor with Cineplex Manager
     * @param cineplexManager    User-defined Cineplex Manager is referenced instead
     */
    public CinemaManager(CineplexManager cineplexManager) {
        this.cineplexManager = cineplexManager;
        this.filename = cineplexManager.filename;
    }

    /**
     * Change the Cineplex Manager referenced by this manager
     * @param cineplexManager    This controller's newly set Cineplex Manager
     */
    public void setCineplexManager(CineplexManager cineplexManager){
        this.cineplexManager = cineplexManager;
    }

    /**
     * Gets the Cineplex Manager referenced by this manager
     * @return CineplexManager     This controller's Cineplex Manager
     */
    public CineplexManager getCineplexManager(){
        return this.cineplexManager;
    }

    /**
     * Create a new Cinema and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new Cinema object, and save the file
     * If database file does not exist, write Cinema object to a new file and save
     * @param cineplexName  Name of Cineplex to add this Cinema
     * @param code          Code of this cinema
     * @param cinemaType    Type of this cinema
     * @param seatList      Seating plan of this cinema
     */
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

    /**
     * Read every Cinema in the database file and return them
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
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

    /**
     * Read every Cinema in the database file and return them
     * @param cineplexName             Name of cineplex to find
     * @return Model.{@link Cinema}    Return list of Cinemas if found, else empty list
     */
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

    /**
     * Read every Cinema based on an attribute in the Database file and return them
     * @param choice                  Given attribute to be check for (based on constant as defined)
     * @param attribute               Value of given attribute to be searched
     * @return Model.{@link Cinema}   Return list of Cinemas if any, else empty list
     */
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

    /**
     * Update a Cinema with new input attribute and cinema code in database file
     * @param choice    Attribute input to be checked (based on constant as defined)
     * @param code      Code of cinema to be updated
     * @param newValue  New value of cinema's attribute
     */
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

    /**
     * Delete a Cinema corresponding with the cinema code from the database file
     * @param code  Code of cinema to be deleted
     */
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
