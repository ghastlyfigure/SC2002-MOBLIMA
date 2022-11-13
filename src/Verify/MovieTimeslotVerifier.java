package Verify;

import Controller.MovieTimeSlotManager;
import Model.Movie;
import Model.MovieTimeslot;

import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.LocalTime;

public class MovieTimeslotVerifier {
	static MovieTimeSlotManager timeslotManager = new MovieTimeSlotManager();

    /**
     * Verify if time slot can be created based on parameters passed
     * @param code          Code of cinemas that this time slot will be added to
     * @param movie         Movie that this time slot will be screening
     * @param timeslot      Date and time which the time slot will begin screening
     * @return boolean      Return true if session can be created, else false
     */
    public static boolean isValidSession(String code, Movie movie, LocalDateTime timeslot) {
        LocalDate date = timeslot.toLocalDate();
        LocalTime startTime = timeslot.toLocalTime();
        LocalTime endTime = startTime.plusMinutes((long) (60 * movie.getDuration()));
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
                if (beforeEndTime.isBefore(startTime) && afterStartTime.isAfter(endTime))
                    return true;
            }
        }

        System.out.println("Invalid session provided!");

        return false;
    }
}
