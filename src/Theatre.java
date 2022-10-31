public class Theatre {
	private int number;
	private Movie[] movieList;
	
	Theatre(int number, Movie[] movieList){
		this.number = number;
		this.movieList = movieList;
	}
	
	void setNumber(int number) {
		this.number = number;
	}
	
	int getNumber() {
		return number;
	}
	
	void setMovieList(Movie movie, int index) {
		movieList[index] = movie;
	}
	
	Movie[] getMovieList() {
		return movieList;
	}
}
