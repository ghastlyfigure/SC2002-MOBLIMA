package Model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a Cineplex
 * It has a name and cinema list in that cineplex
 */
public class Cineplex implements Serializable{
	private String name;
	private ArrayList<Cinema> cinemaList;

	/**
	 * Creates a new Cineplex with the given cineplex name and cinema list
	 * @param name This Cineplex name.
	 * @param cinemaList This Cineplex cinema list. Each cineplex will have 3 cinemas
	 */
	public Cineplex(String name, ArrayList<Cinema> cinemaList) {
		this.name = name;
		this.cinemaList = cinemaList;
	}

	/**
	 * Changes the name of this Cineplex
	 * @param name This Cineplex new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this Cineplex.
	 * @return name this Cineplex name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the cinema list of this Cineplex
	 * @param cinemaList This Cinema's new cinema list.
	 */
	public void setCinemaList(ArrayList<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}

	/**
	 * Gets the cinema list of this Cineplex.
	 * @return this Cineplex cinema list.
	 */
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}

	/**
	 * String to return when this Cineplex is being called
	 * @return String
	 */
	public String displayCineplex(){
		int i;
        String cinemaList = "";
        
        for (i = 0; i < getCinemaList().size() - 1; i++) {
        	cinemaList = cinemaList + this.cinemaList.get(i) + ",\n";
        }
		
		cinemaList = cinemaList + this.cinemaList.get(this.cinemaList.size() - 1);

        return "\nCineplex Name: " + getName() + "\n"
        		+ cinemaList;
    }
}
