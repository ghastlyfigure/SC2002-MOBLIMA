package model;

import java.util.List;
import java.util.Objects;

public class Movie {
    public enum Status {
        COMING_SOON,
        PREVIEW,
        NOW_SHOWING
    }

    public enum Type {
        THREE_D,
        BLOCKBUSTER
    }

    public static class Review {
        private int score;
        private String review;

        public Review(int score, String review) {
            if (!(1 <= score && score <= 5)) {
                throw new IllegalArgumentException("Invalid rating, should be between 1 - 5");
            }
            this.score = score;
            this.review = review;
        }

        public int getScore() {
            return this.score;
        }

        @Override
        public String toString() {
            return String.format("[%d / 5] %s", score, review);
        }
    }

    private String title;
    private Status status;
    private Type type;
    private String synopsis;
    private String director;
    private List<String> cast;
    private int ratingSum;
    private List<Review> reviews;

    public Movie(String title, Status status, Type type, String synopsis, String director, List<String> cast, List<Review> reviews) {
        if (title.length() < 2) {
            throw new IllegalArgumentException("Movie title should be at least length 2");
        }
        this.title = title;

        this.status = status;
        this.type = type;

        if (synopsis.isEmpty()) {
            throw new IllegalArgumentException("Synopsis should be non empty");
        }
        this.synopsis = synopsis;

        if (director.isEmpty()) {
            throw new IllegalArgumentException("Director should be non empty");
        }
        this.director = director;

        if (cast.isEmpty()) {
            throw new IllegalArgumentException("Cast list should be non empty");
        }
        for (String s : cast) {
            if (s.isEmpty()) {
                throw new IllegalArgumentException("Cast should be non empty");
            }
        }
        this.cast = cast;

        this.reviews = reviews;

        this.ratingSum = 0;
        for (Review r : reviews) {
            this.ratingSum += r.getScore();
        }
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        this.ratingSum += review.getScore();
    }

    public Type getType() {
        return this.type;
    }

    public String getTitle() {
        return this.title;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("\nTitle:\t").append(this.title);
        sb.append("\nStatus:\t").append(this.status);
        sb.append("\nSynopsis:\t").append(this.synopsis);
        sb.append("\nDirector:\t").append(this.director);

        sb.append("\nCast:\t");
        for (int i = 0; i < this.cast.size(); i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(this.cast.get(i));
        }

        sb.append("\nOverall rating:\t");
        if (this.reviews.size() == 0) {
            sb.append("NA");
        } else {
            sb.append(String.format("%.1f", (float) this.ratingSum / this.reviews.size()));
        }

        sb.append("\n\nReviews:\n");
        for (int i = 0; i < this.reviews.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, this.reviews.get(i).toString()));
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Movie && Objects.equals(((Movie) other).title, this.title);
    }
}
