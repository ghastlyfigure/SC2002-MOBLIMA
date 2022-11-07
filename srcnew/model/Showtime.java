package model;

import java.time.LocalDateTime;

public class Showtime {
    public enum TypeOfDay {
        WEEKDAY,
        WEEKEND,
        PUBLIC_HOLIDAY,
    }

    private Cinema cinema;
    private Movie movie;
    private LocalDateTime datetime;
    private TypeOfDay typeOfDay;

    public Showtime(Cinema cinema, Movie movie, LocalDateTime datetime) {
        this.cinema = cinema;
        this.movie = movie;
        this.datetime = datetime;

        // TODO: Determine right TypeOfDay
        this.typeOfDay = TypeOfDay.WEEKDAY;
    }

    // to avoid floating points, all money calculations will be done in cents (int)
    public int calculateTicketPrice(int age) {
        // TODO: calculate based on this.typeOfDay, age, this.cinema.getCinemaClass(), this.movie.getType()
        return 0;
    }
}
