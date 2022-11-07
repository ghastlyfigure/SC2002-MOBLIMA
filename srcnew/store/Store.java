package store;

import model.Booking;
import model.Cinema;
import model.Cineplex;
import model.Movie;
import model.Showtime;

import java.util.ArrayList;
import java.util.List;

// TODO: persist this to local storage
// also, be careful of this becoming a god class
public class Store {
    private List<Cineplex> cineplexes = new ArrayList<>();
    private List<Cinema> cinemas = new ArrayList<>();
    private List<Booking> bookings = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<Showtime> showtimes = new ArrayList<>();

    public void addMovie(Movie movie) {
        this.movies.add(movie);
    }

    public void deleteMovie(int idx) {
        if (!(1 <= idx && idx <= this.movies.size())) {
            throw new IllegalArgumentException("Movie index should be between 1 - " + this.movies.size());
        }
        this.movies.remove(idx - 1);
    }

    public List<Movie> getMovies() {
        return this.movies;
    }
}
