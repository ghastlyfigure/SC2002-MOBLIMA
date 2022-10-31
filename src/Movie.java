public class Movie {
	private String name;
	private String status;
	private String synopsis;
	private String director;
	private String[] cast;
	private int showtime;
	private int duration;
	private Seat[][] seatList;
	
	Movie(String name, String status, String synopsis, String director, String[] cast, int showtime, int duration, Seat[][] seatList){
		this.name = name;
		this.status = status;
		this.synopsis = synopsis;
		this.director = director;
		this.cast = cast;
		this.showtime = showtime;
		this.duration = duration;
		this.seatList = seatList;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	String getName() {
		return name;
	}
	
	void setStatus(String status) {
		this.status = status;
	}
	
	String getStatus() {
		return status;
	}
	
	void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	
	String getSynopsis() {
		return synopsis;
	}
	
	void setDirector(String director) {
		this.director = director;
	}
	
	String getDirector() {
		return director;
	}
	
	void setCast(String cast, int index) {
		this.cast[index - 1] = cast;
	}
	
	String[] getCast() {
		return cast;
	}
	
	void setShowtime(int showtime) {
		this.showtime = showtime;
	}
	
	int getShowtime() {
		return showtime;
	}
	
	void setDuration(int duration) {
		this.duration = duration;
	}
	
	int getDuration() {
		return duration;
	}
}
