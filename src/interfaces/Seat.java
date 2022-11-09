package interfaces;

import java.io.Serializable;

public class Seat implements Serializable{
	private int ID;
	private boolean occupied;
	
	Seat(int ID, boolean occupied){
		this.ID = ID;
		this.occupied = occupied;
	}
	
	void setSeatID(int ID) {
		this.ID = ID;
	}
	
	int getSeatID() {
		return ID;
	}
	
	void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}
	
	boolean isOccupied() {
		return occupied;
	}
}
