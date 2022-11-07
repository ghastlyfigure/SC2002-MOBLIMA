package view.user;

import model.Movie;
import store.Store;
import view.View;

public class MovieView extends View {
    private final Movie movie;

    public MovieView(Movie movie) {
        this.movie = movie;
    }

    @Override
    public String display(Store store) {
        return this.movie.toString();
    }

    @Override
    public String prompt(Store store) {
        return "Enter anything";
    }

    @Override
    public View handleInput(Store store, String input) {
        return new MovieListView();
    }
}
