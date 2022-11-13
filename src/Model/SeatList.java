package Model;

import java.io.Serializable;

public class SeatList implements Serializable{
	private Seat[][] seatList;
	private int row;
	private int column;
	
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
	
	public void printLayout( ) {
		int i, j;
		int split = column / 2;
		System.out.println("Seating List of Cinema:");
		System.out.println("X = occupied, O = not occupied.");
		System.out.println();
		// TODO: implement print according to row, col
		System.out.println("==============================================");
		System.out.println("SCREEN");
		System.out.println("==============================================");
		for (i = 0; i < row; i++) {
			for(j = 0; j < column; j++) {
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
		// implement print according to row, col
		System.out.println("==============================================");
		System.out.println("ENTRANCE");
		System.out.println("==============================================");
	}
	
	void setRow(int row) {
		this.row = row;
	}

	int getRow() {
		return row;
	}
	
	void setColumn(int column) {
		this.column = column;
	}
	
	int getColumn() {
		return column;
	}
	
	int getTotalSeatNumber() {
		return row * column;
	}
	
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
	
	public void assignSeat(int ID) {
		seatList[ID / row][ID % row].setOccupied(true);
	}
	
	void unassignSeats(int ID) {
		seatList[ID / row][ID % row].setOccupied(false);
	}
	
	public boolean isAvailable(int ID) {
		return seatList[ID / row][ID % row].isOccupied();
	}

}
