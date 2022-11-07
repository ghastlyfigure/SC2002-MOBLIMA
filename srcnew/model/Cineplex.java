package model;

import java.util.List;

public class Cineplex {
    private List<Cinema> cinemas;
    private String name;

    public Cineplex(String name, List<Cinema> cinemas) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException("Cineplex name cannot be empty");
        }
        this.name = name;

        if (cinemas.size() < 3) {
            throw new IllegalArgumentException("A cineplex should have 3 or more cinemas");
        }
        this.cinemas = cinemas;
    }
}
