public class Cinema {
	private String name;
	private Theatre[] theatreList;
	
	Cinema(String name, Theatre[] theatreList){
		this.name = name;
		this.theatreList = theatreList;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getName() {
		return name;
	}
	
	void setTheatreList(Theatre theatre, int index) {
		theatreList[index - 1] = theatre;
	}
	
	Theatre[] getTheatreList() {
		return theatreList;
	}
}
