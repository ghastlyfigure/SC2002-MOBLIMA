package model;

import java.util.Objects;

public class Cinema {
    public enum Class {
        PLATINUM,
        GOLD,
        SILVER
    }

    private String code;
    private Class cinemaClass;

    public Cinema(String code, Class cinemaClass) {
        if (code.length() != 3) {
            throw new IllegalArgumentException("Cinema code must be of length 3");
        }
        this.code = code;
        this.cinemaClass = cinemaClass;
    }

    public Class getCinemaClass() {
        return this.cinemaClass;
    }

    public String getLayout() {
        // TODO
        return "";
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof Cinema && Objects.equals(((Cinema) other).code, this.code);
    }
}
