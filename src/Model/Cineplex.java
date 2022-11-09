package Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Cineplex implements Serializable{
	private String name;
	private ArrayList<Cinema> cinemaList;
	
	public Cineplex(String name, ArrayList<Cinema> cinemaList) {
		this.name = name;
		this.cinemaList = cinemaList;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setCinemaList(ArrayList<Cinema> cinemaList) {
		this.cinemaList = cinemaList;
	}
	
	public ArrayList<Cinema> getCinemaList() {
		return cinemaList;
	}

	public String displayCineplex(){
		int i;
        String cinemaList = "";
        
        for (i = 0; i < getCinemaList().size() - 1; i++) {
        	cinemaList = cinemaList + this.cinemaList.get(i) + ",\n";
        }
		
		cinemaList = cinemaList + this.cinemaList.get(this.cinemaList.size() - 1);

        return "\nModel.Cineplex Name: " + getName() + "\n"
        		+ cinemaList;
    }
}
