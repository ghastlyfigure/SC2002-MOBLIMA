package Model;

import java.io.Serializable;

/**
 * Represents a Rating
 */
public class Rating implements Serializable {
	/**
	 * This rating's name
	 */
	private String name;

	/**
	 * This rating's score
	 */
	private double score;

	/**
	 * This rating's ID
	 */
	private String review;

	/**
	 * Creates a new Rating with the given name, score, and review
	 * @param name This Rating's name.
	 * @param score This Movie's score
	 * @param review This Movie's review.
	 */
	public Rating(String name, double score, String review){
		this.name = name;
		this.score = score;
		this.review = review;
	}

	/**
	 * Changes the name of this Rating
	 * @param name This Rating's new name.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of this Rating.
	 * @return this Rating's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Changes the score of this Rating
	 * @param score This Rating's new score.
	 */
	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * Gets the score of this Rating.
	 * @return this Rating's score.
	 */
	public double getScore() {
		return score;
	}

	/**
	 * Changes the review of this Rating
	 * @param review This Rating's new review.
	 */
	public void setReview(String review) {
		this.review = review;
	}

	/**
	 * Gets the review of this Rating.
	 * @return this Rating's review.
	 */
	public String getReview() {
		return review;
	}

	/**
	 * String to return when this Rating is being called
	 * @return String
	 */
	public String displayRating() {
		return "Name: " + getName() + "\n"
		     + "Score: " + getScore() + "\n"
		     + "Review: " + getReview();
	}

	/**
	 * Checks whether unknown object is identical to this Rating
	 * @param unknownObject     object to be compared to
	 * @return boolean  Return true if object is identical to this Rating, else false
	 */
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
