public class Rating {
	private String name;
	private double score;
	private String review;
	
	Rating(String name, double score, String review){
		this.name = name;
		this.score = score;
		this.review = review;
	}
	
	void setName(String name) {
		this.name = name;
	}
	
	
	String getName() {
		return name;
	}
	
	void setScore(double score) {
		this.score = score;
	}
	
	double getScore() {
		return score;
	}
	
	void setReview(String review) {
		this.review = review;
	}
	
	String getReview() {
		return review;
	}
	
	String displayRating() {
		return "Name: " + getName() + "\n"
		     + "Score: " + getScore() + "\n"
		     + "Review: " + getReview();
	}
	   
	boolean isIdenticalRating(Object unknownObject) {
	    if (!(unknownObject instanceof Rating)) {
	        return false;
	    }
	    Rating rating = (Rating) unknownObject;
	    if(this.name.equals(rating.getName()) && 
	       this.score == rating.getScore() && this.review.equals(rating.getReview())) {
	    	return true;
	    }
	    else {
	    	return false;
	    }
	}
}
