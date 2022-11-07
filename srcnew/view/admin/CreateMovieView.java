package view.admin;

import model.Movie;
import store.Store;
import view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateMovieView extends View {
    private static final String HELP_MSG = "Enter the movie details as semicolon separated values:\n" +
            "title;CS|PR|NS;3D|BB;synopsis;director;cast1;cast2;...\n" +
            "Legend: CS = Coming Soon, PR = Preview, NS = Now Showing, BB = Blockbuster\n\n" +
            "E.g. \"Black Panther: Wakanda Forever;CS;BB;Black Panther;Ryan Coogler;Michael Jordan;Letitia Wright\"";

    private static final Map<String, Movie.Type> strToMovieType = new HashMap<>();
    private static final Map<String, Movie.Status> strToMovieStatus = new HashMap<>();
    static {
        strToMovieType.put("3D", Movie.Type.THREE_D);
        strToMovieType.put("BB", Movie.Type.BLOCKBUSTER);
        strToMovieStatus.put("CS", Movie.Status.COMING_SOON);
        strToMovieStatus.put("PR", Movie.Status.PREVIEW);
        strToMovieStatus.put("NS", Movie.Status.NOW_SHOWING);
    }

    @Override
    public String display(Store store) {
        return HELP_MSG;
    }

    @Override
    public String prompt(Store store) {
        return "Enter movie details";
    }

    @Override
    public View handleInput(Store store, String input) {
        String[] args = input.trim().split(";");
        if (args.length < 6) {
            throw new IllegalArgumentException("Missing movie details");
        }

        String title = args[0];
        String statusStr = args[1];
        if (!strToMovieStatus.containsKey(statusStr)) {
            throw new IllegalArgumentException("Invalid movie status");
        }
        Movie.Status status = strToMovieStatus.get(statusStr);

        String typeStr = args[2];
        if (!strToMovieType.containsKey(typeStr)) {
            throw new IllegalArgumentException("Invalid movie type");
        }
        Movie.Type type = strToMovieType.get(typeStr);

        String synopsis = args[3];
        String director = args[4];
        ArrayList<String> cast = new ArrayList<>();
        for (int i = 5; i < args.length; i++) {
            cast.add(args[i]);
        }

        Movie movie = new Movie(title, status, type, synopsis, director, cast, List.of());
        store.addMovie(movie);
        System.out.println("Movie added successfully");
        return new view.admin.IndexView();
    }
}
