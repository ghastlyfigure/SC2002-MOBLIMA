package view.user;

import model.Movie;
import store.Store;
import view.View;

import java.util.List;

// TODO: Handle case when n = 0
public class MovieListView extends View {
    @Override
    public String display(Store store) {
        StringBuilder sb = new StringBuilder();
        List<Movie> movies = store.getMovies();
        for (int i = 0; i < movies.size(); i++) {
            sb.append(String.format("%d. %s\n", i + 1, movies.get(i).getTitle()));
        }
        return sb.toString();
    }

    @Override
    public String prompt(Store store) {
        return String.format("Enter the index of the movie you would like to see (1 - %d)", store.getMovies().size());
    }

    @Override
    public View handleInput(Store store, String input) {
        List<Movie> movies = store.getMovies();
        int idx = this.parseIndex(input, 1, movies.size());
        Movie movie = movies.get(idx - 1);
        return new MovieView(movie);
    }
}
