package Model;

import java.io.Serializable;

public class Rating implements Serializable {
	private String name;
	private double score;
	private String review;
	
	public Rating(String name, double score, String review){
		this.name = name;
		this.score = score;
		this.review = review;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getName() {
		return name;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public double getScore() {
		return score;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public String getReview() {
		return review;
	}

	public String displayRating() {
		return "Name: " + getName() + "\n"
		     + "Score: " + getScore() + "\n"
		     + "Review: " + getReview();
	}

	public boolean isIdenticalRating(Object unknownObject) {
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
