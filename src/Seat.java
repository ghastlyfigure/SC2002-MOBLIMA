
public class Seat {
	private boolean occupied;
	private String type;
	private int price;
	
	Seat(boolean occupied, String type){
		this.occupied = occupied;
		this.type = type;
	}
	
	boolean isOccupied() {
		return occupied;
	}
	
	void setType(String type) {
		this.type = type;
	}
	
	String getType() {
		return type;
	}
	
	void setPrice(int price) {
		this.price = price;
	}
	
	int getPrice() {
		return price;
	}
}
