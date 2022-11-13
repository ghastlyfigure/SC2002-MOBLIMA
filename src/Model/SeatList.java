package Model;

import java.io.Serializable;

/**
 * Represents a Seat List
 */
public class SeatList implements Serializable{
	/**
	 * This seat list's list of seats
	 */
	private final Seat[][] seatList;

	/**
	 * This seat list's number of rows
	 */
	private int row;

	/**
	 * This seat list's number of columns
	 */
	private int column;

	/**
	 * Creates a new Seat List with the given rows and columns
	 * @param row This Seat List's number of rows.
	 * @param column This Seat List's number of columns
	 */
	public SeatList(int row, int column) {
		int i;
		this.row = row;
		this.column = column;
		seatList = new Seat[row][column];
		for (i = 0; i < row; i++) {
			for(int j = 0; j < column; j++)
				seatList[i][j] = new Seat(i * this.row + j, false);
		}
	}

	/**
	 * Print the layout of this Seat List, displayed in a matrix of rows and columns
	 * X = occupied
	 * 0 = not occupied
	 */
	public void displaySeatLayout() {
		int split = column / 2;
		System.out.println("Seating List of Cinema:");
		System.out.println("X = occupied, O = not occupied.");
		System.out.println();
		// TODO: implement print according to row, col
		for (int i=0; i<column; i++) {
			System.out.print("==");
		}
		System.out.println();
		for (int i=0; i<(column/2)-1; i++) {
			System.out.print("  ");
		}
		System.out.print("SCREEN");
		System.out.println();
		for (int i=0; i<column; i++) {
			System.out.print("==");
		}
		System.out.println();

		for (int i=0; i<row; i++) {
			for(int j=0; j<column; j++) {
				if(j == column / 2 - 1) {
					System.out.print(" ");
				}
				if(seatList[i][j].isOccupied()){
					System.out.print("X ");
				}
				else {
					System.out.print("O ");
				}
			}
			System.out.print("\n");
		}

		for (int i=0; i<column; i++) {
			System.out.print("==");
		}
		System.out.println();
		for (int i=0; i<(column/2)-2; i++) {
			System.out.print("  ");
		}
		System.out.print("ENTRANCE");
		System.out.println();
		for (int i=0; i<column; i++) {
			System.out.print("==");
		}
		System.out.println();
	}

	/**
	 * Changes the number of rows of this Seat List
	 * @param row This Seat List's new number of rows
	 */
	void setRow(int row) {
		this.row = row;
	}

	/**
	 * Gets the number of rows of this Seat List
	 * @return this Seat List's number of rows.
	 */
	int getRow() {
		return row;
	}

	/**
	 * Changes the number of columns of this Seat List
	 * @param column This Seat List's new number of columns
	 */
	void setColumn(int column) {
		this.column = column;
	}

	/**
	 * Gets the number of columns of this Seat List
	 * @return this Seat List's number of columns.
	 */
	int getColumn() {
		return column;
	}

	/**
	 * Gets the total number of seats in this Seat List
	 * @return this Seat List's total number of seats.
	 */
	int getTotalSeatNumber() {
		return row * column;
	}

	/**
	 * Gets the total number of unoccupied seats in this Seat List
	 * @return this Seat List's total number of unoccupied seats
	 */
	int getTotalAvailableSeat() {
		int sum = 0; 
		int i, j;
		for (i = 0; i < row; i++) {
			for(j = 0; j < column; j++) {
				if(seatList[i][j].isOccupied()) {
					sum++;
				}
			}
		}
		return sum;
	}

	/**
	 * Changes the occupancy of a specific seat in this Seat List to occupied
	 * @param ID This Seat's ID
	 */
	public void assignSeat(int ID) {
		seatList[ID / row][ID % row].setOccupied(true);
	}

	/**
	 * Changes the occupancy of a specific seat in this Seat List to unoccupied
	 * @param ID This Seat's ID
	 */
	void unassignSeats(int ID) {
		seatList[ID / row][ID % row].setOccupied(false);
	}

	/**
	 * Check if a particular seat is occupied
	 * @param ID			Occupancy status of Seat ID
	 * @return boolean		Occupancy status of Seat is returned
	 */
	public boolean isAvailable(int ID) {
		return seatList[ID / row][ID % row].isOccupied();
	}

}
