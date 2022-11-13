package Controller;

import Model.Cinema;
import Model.Cineplex;
import Verify.CineplexVerifier;

import java.util.ArrayList;
import java.io.File;  
import java.io.IOException;  
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CineplexManager {

    /**
     * File name of database file to access
     */
    public final static String filename = "database/cineplex.txt";

    /**
     * Declaring constants to be used
     * Ensures better readibility
     */
    public final static int name = 0;
    public final static int cinema = 1;

    /**
     * Create a new Cinemaplex and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new Cinemaplex object, and save the file
     * If database file does not exist, write Cinemaplex object to a new file and save
     * @param name          Name of Cineplex to add this Cinema
     * @param cinemaList    List of cinemas of this cineplex
     */

    public void createCineplex(String name, ArrayList<Cinema> cinemaList) {
        if (CineplexVerifier.isValidCineplex(name, cinemaList)) {
            Cineplex cineplex = new Cineplex(name, cinemaList);
            ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
            File cineplexFile = new File(filename);
            if (cineplexFile.exists()) 
                cineplexList = getAllCineplex();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
                cineplexList.add(cineplex);
                out.writeObject(cineplexList);
                out.flush();
                out.close();
            } catch (IOException e) {
                //
            }
        }
    }

    /**
     * Read every Cineplex in the database file and return them
     * If database file is not found, ignore error and return an empty list
     * @return Model.{@link Cineplex}    Return list of Cineplexes if found, else empty list
     */
    public ArrayList<Cineplex> getAllCineplex(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));   
            ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) in.readObject();
            in.close();
            return cineplexList;
        } catch (ClassNotFoundException | IOException e) {
            // 
        }
        return new ArrayList<Cineplex>();
    };

    /**
     * Read Cineplex in database file using name passed in
     * @param name          Name of cineplex to find
     * @return Cineplex     Return Cineplex if found, else null object
     */
    public Cineplex getCineplex(String name){
    	int i;
        ArrayList<Cineplex> cineplexList = getAllCineplex();
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if (cineplex.getName().equals(name))
                return cineplex;
        }
        return null;
    };

    /**
     * Update the name of Cineplex corresponding with the old name input in database file
     * @param oldName    Name of cineplex to find
     * @param newName    New name used to update cineplex
     */
    public void updateCineplex(String oldName, String newName){   
    	int i;
    	ArrayList<Cineplex> cineplexList = getAllCineplex();
        ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();
                    
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if (cineplex.getName().equals(oldName)) {
                cineplex.setName(newName);
            	newCineplexList.add(cineplex);
            }
            updateFile(filename, newCineplexList);
        }
    }

    /**
     * Delete a Cineplex in the Database file corresponding to the name attribute passed in
     * @param name  Name of Cineplex to delete
     */
    public void deleteCineplex(String name){
        int i;
    	ArrayList<Cineplex> cineplexList = getAllCineplex();
        ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();
        
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if (!cineplex.getName().equals(name))
                newCineplexList.add(cineplex);
        }

        updateFile(filename, newCineplexList);
    }

    /**
     * Overwrite database file with new data list of Cineplex
     * @param filename      Filename to check for
     * @param cineplexList    New ArrayList of Cineplex to write to the file
     */
    public void updateFile(String filename, ArrayList<Cineplex> cineplexList){
        File tempFile = new File(filename);
        if (tempFile.exists()) 
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(cineplexList);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }
}
