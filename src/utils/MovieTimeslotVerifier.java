package utils;

import controllers.MovieTimeSlotManager;
import interfaces.Movie;
import interfaces.MovieTimeslot;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class MovieTimeslotVerifier {
	static MovieTimeSlotManager timeslotManager = new MovieTimeSlotManager();

    public static boolean isValidSession(String code, Movie movie, LocalDateTime timeslot) {
        LocalDate date = timeslot.toLocalDate();
        LocalTime startTime = timeslot.toLocalTime();
        LocalTime endTime = startTime.plusMinutes((long) (60.0 * movie.getDuration()));
        int i;

        ArrayList<MovieTimeslot> timeslotList = timeslotManager.getAllTimeslotByCinema(code, date);
        MovieTimeslot sessionBefore = null;
        MovieTimeslot sessionAfter = null;

        if (timeslotList.isEmpty()) {
            return true;
        } 
       
        else {
            sessionBefore = timeslotList.get(0);
            if (endTime.isBefore(sessionBefore.getStartTime())) 
                return true;

            sessionBefore = timeslotList.get(timeslotList.size() - 1);
            if (startTime.isAfter(sessionBefore.getEndTime())) 
                return true;

            for (i = 0; i < (timeslotList.size()-1); i++) {
                sessionBefore = timeslotList.get(i);
                sessionAfter = timeslotList.get(i+1);
                LocalTime beforeEndTime = sessionBefore.getEndTime();
                LocalTime afterStartTime = sessionAfter.getStartTime();
                if (afterStartTime.isBefore(startTime) && beforeEndTime.isAfter(endTime)) 
                    return true;
            }
        }

        System.out.println("Invalid session provided");

        return false;
    }
}
