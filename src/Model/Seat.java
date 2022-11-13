package Model;

import java.io.Serializable;

/**
 * Represents a Seat
 */
public class Seat implements Serializable{
	/**
	 * This seat's ID
	 */
	private int ID;

	/**
	 * This seat's occupancy
	 */
	private boolean occupied;

	/**
	 * Creates a new Seat with the given ID and occupancy
	 * @param ID This Seat's ID.
	 * @param occupied This Seat's occupancy
	 */
	Seat(int ID, boolean occupied){
		this.ID = ID;
		this.occupied = occupied;
	}

	/**
	 * Changes the ID of this Seat
	 * @param ID This Seat's new ID.
	 */
	void setSeatID(int ID) {
		this.ID = ID;
	}

	/**
	 * Gets the ID of this Seat.
	 * @return this Seat's ID.
	 */
	int getSeatID() {
		return ID;
	}

	/**
	 * Changes the occupancy of this Seat
	 * @param occupied This Rating's new occupancy.
	 */
	void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Gets the occupancy of this Seat.
	 * @return this Seat's occupancy.
	 */
	boolean isOccupied() {
		return occupied;
	}
}
