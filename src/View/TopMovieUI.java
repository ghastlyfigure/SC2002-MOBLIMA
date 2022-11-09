package View;

import Controller.InputManager;
import Controller.MovieManager;
import Controller.TransactionManager;
import Model.Movie;
import Model.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;



public class TopMovieUI {

    private int choice;
    private MovieManager movieManager;
    public TopMovieUI() {
        this.movieManager = new MovieManager();
    }

    public TopMovieUI(MovieManager movieManager) {
        this.movieManager = movieManager;
    }
    public void main() {
        while (choice != 3)
            display();
    }

    public void display() {
        System.out.println("Option List: \n"
                            + "List Top 5 Movies according to\n"
                            + "\t1. According to ticket sales\n"
                            + "\t2. According to ratings\n"
                            + "3. Go back to main menu\n"
                            + "Please choose an option: ");

        choice = InputManager.getInt();
        switch (choice) {
            case 1:
                listBySales();
                break;
            case 2:
                listByReviews();
                break;
            case 3:
                System.out.println("Returning back to main menu");
                break;
            default:
                System.out.println("Invalid input!\n" +
                                   "Please try again");
        }
    }

    public void listMovieDetails(Movie movie) {
        System.out.println("Movie ID: " + movie.getID());
        System.out.println("Movie Title: " + movie.getName());
        System.out.println("Movie Model.Rating: " + movie.getAverageScore());
    }

    public void listByReviews() {
        int i;
        ArrayList<Movie> movieList = movieManager.readMovie();
        Collections.sort(movieList, new SortByRating());
        int qty = 0;
        if (movieList.size() < 5) {
            qty = movieList.size();
        }
        else {
            qty = 5;
        }
        for (i = 0; i < qty; i++) {
            listMovieDetails(movieList.get(i));
        }
        MovieInfoUI details = new MovieInfoUI();
        details.main();
    }

    public void listBySales() {
        int qty = 0;
        int i;
        ArrayList<Movie> movieList = movieManager.readMovie();
        Collections.sort(movieList, new SortBySales());
        if (movieList.size() < 5) {
            qty = movieList.size();
        }
        else {
            qty = 5;
        }
        for (i = 0; i < qty; i++) {
            listMovieDetails(movieList.get(i));
        }
        MovieInfoUI details = new MovieInfoUI();
        details.main();
    }

    class SortByRating implements Comparator<Movie> {
        public int compare(Movie x, Movie y) {
            String x_score = x.getAverageScore();
            String y_score = y.getAverageScore();
            if (x_score == "N/A" && y_score == "N/A"){
                return 0;
            }
            if (x_score == "N/A"){
                return 1;
            }
            if (y_score == "N/A") {
                return -1;
            }
            double diff = Double.parseDouble(x_score) - Double.parseDouble(y_score);
            if (diff > 0) {
                return -1;
            }
            if (diff < 0) {
                return 1;
            }
            return 0;
        }
    }

    class SortBySales implements Comparator<Movie> {

        public int compare(Movie x, Movie y) {
            int x_sales = 0;
            int y_sales = 0;
            int i;
            TransactionManager transMgr = new TransactionManager();
            ArrayList<Transaction> transList = transMgr.readAllTransaction();
            for (i = 0; i < transList.size(); i++) {
                if (transList.get(i).getMovie() == x) {
                    x_sales++;
                }
                if (transList.get(i).getMovie() == y) {
                    y_sales++;
                }
            }
            return x_sales - y_sales;
        }
    }
}
