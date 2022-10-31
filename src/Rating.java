public class Rating {
	private int score;
	private String review;
	
	Rating(int score, String review){
		this.score = score;
		this.review = review;
	}
	
	void setScore(int score) {
		this.score = score;
	}
	
	int getScore() {
		return score;
	}
	
	void setReview(String review) {
		this.review = review;
	}
	
	String getReview() {
		return review;
	}
}
