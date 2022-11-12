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
        System.out.println("Option List: ");
        System.out.println();
        System.out.println("List Top 5 Movies According to:");
        System.out.println("\t1. Ticket Sales");
        System.out.println("\t2. Ratings");
        System.out.println("\t3. Return to Main Menu");
        System.out.println();
        System.out.println("\tPlease select an option: ");

        choice = InputManager.getInt();
        switch (choice) {
            case 1 -> listBySales();
            case 2 -> listByReviews();
            case 3 -> System.out.println("Returning back to main menu");
            default -> {
                System.out.println("Invalid input!");
                System.out.println("Please try again");
            }
        }
    }

    public void listMovieDetails(Movie movie) {
        System.out.println("Movie ID: " + movie.getID());
        System.out.println("Movie Title: " + movie.getName());
        System.out.println("Movie Rating: " + movie.getAverageScore());
    }

    // TODO: Fix Top5 ListByReviews
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

    // TODO: Fix top5 listBySales
    public void listBySales() {
        int qty = 0;
        int i;
        ArrayList<Movie> movieList = movieManager.readMovie();
        movieList.sort(new SortBySales());

        //code is wrong here
        listMovieDetails(movieList.get(0));
        System.out.println("debug end");

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
            TransactionManager transMgr = new TransactionManager();
            ArrayList<Transaction> transList = transMgr.readAllTransaction();

            // code is correct here
            System.out.println(transList.get(0).getMovie().getName());
            System.out.println("debug end");

            for (int i = 0; i < transList.size(); i++) {
                if (transList.get(i).getMovie().equals(x)) {
                    x_sales++;
                }
                if (transList.get(i).getMovie().equals(y)) {
                    y_sales++;
                }
            }
            return x_sales - y_sales;
        }
    }
}
