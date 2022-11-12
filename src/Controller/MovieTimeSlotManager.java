package Controller;

import Model.*;
import Verify.MovieTimeslotVerifier;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;

public class MovieTimeSlotManager {
    private CinemaManager cinemaManager = new CinemaManager();
    public String filename;
    
    public final static int movie = 0;
    public final static int ID = 1;
    public final static int timeslot = 2;
    public final static int seat = 3;

    public MovieTimeSlotManager(){
        this.cinemaManager = new CinemaManager();
        this.filename = cinemaManager.filename;
    }
    
    public MovieTimeSlotManager(CinemaManager cinemaManager){
        this.cinemaManager = cinemaManager;
        this.filename = cinemaManager.filename;
    }
    
    public void setCinemaManager(CinemaManager cinemasCtrl) {
        this.cinemaManager = cinemaManager;
    }

    public CinemaManager getCinemaManager(){
        return this.cinemaManager;
    }
   
    public void createTimeslot(String code, Movie movie, LocalDateTime timeslotDateTime) {
    	int i;	
        if (MovieTimeslotVerifier.isValidSession(code, movie, timeslotDateTime)) {
            SeatList seatList = cinemaManager.getCinema(1, code).get(0).getSeatList();
            MovieTimeslot timeslot = new MovieTimeslot(movie, timeslotDateTime, seatList, getLastID()+1);
            ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
            ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
            for (i = 0; i < cinemaList.size(); i++){
                Cinema cinema = cinemaList.get(i);
                if (cinema.getCinemaCode().equals(code)){
                    timeslotList = cinema.getMovieTimeslot();
                    timeslotList.add(timeslot);
                    cinema.setMovieTimeslot(timeslotList);
                    this.cinemaManager.updateCinema(4, code, timeslotList);
                    timeslotList.clear();
                    break;
                }
            }
            System.out.println("Model.Movie Session created successfully!");
        }
    }
    
    public int getLastID(){
        int lastID = -1;
        int timeslotID, i;
        ArrayList<MovieTimeslot> timeslotList = getAllTimeslot();
        for (i = 0; i < timeslotList.size(); i++){
            timeslotID = timeslotList.get(i).getTimeslotID();
            if (timeslotID > lastID)
                lastID = timeslotID;
        }
        return lastID;
    }

    public ArrayList<MovieTimeslot> getAllTimeslot() {
    	int i, j;
    	
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        Cinema cinema = null;
        for (i = 0; i < cinemaList.size(); i++) {
            cinema = cinemaList.get(i);
            for (j = 0; j < cinema.getMovieTimeslot().size(); j++) {
                cinema.getMovieTimeslot().forEach(n->timeslotList.add(n));
            }
        }
        return timeslotList;
    }; 
   
    public MovieTimeslot getSpecificTimeslot(String code, LocalDateTime timeslotDate) {
    	int i;
        ArrayList<Cinema> cinemaList = this.cinemaManager.getCinema(1, code);
        Cinema cinema = null;
        if(cinemaList.isEmpty())
            return null;
        else{
            cinema = cinemaList.get(0);
        }

        ArrayList<MovieTimeslot> timeslotList = cinema.getMovieTimeslot();
        MovieTimeslot timeslot = null;

        for (i = 0; i < timeslotList.size(); i++){
            timeslot = timeslotList.get(i);
            if (timeslot.getTimeslot().equals(timeslotDate))
                return timeslot;
        }
        return null;
    }; 

    public ArrayList<MovieTimeslot> getAllTimeslotByCinema(String code, LocalDate timeslotDate) {
    	int i;
        ArrayList<Cinema> cinemaList = this.cinemaManager.getCinema(1, code);
        if (cinemaList.isEmpty()) {
            return null;
        } else {
            ArrayList<MovieTimeslot> timeslotList = cinemaList.get(0).getMovieTimeslot();
            ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
            MovieTimeslot timeslot = null;
            for (i = 0; i < timeslotList.size(); i++) {
                timeslot = timeslotList.get(i);
                if (timeslot.getTimeslot().toLocalDate().equals(timeslotDate))
                    newTimeslotList.add(timeslot);
            }
            return newTimeslotList;
        }        
    }
    
    public ArrayList<MovieTimeslot> getTimeslot(int choice, Object attribute) {
    	int i, j;
        ArrayList<MovieTimeslot> timeslotList = getAllTimeslot();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
        MovieTimeslot t = null;
        
        for (i = 0; i < timeslotList.size(); i++){
            t = timeslotList.get(i);
            switch(choice){
                case movie:  
                    if (t.getMovie().getID() == (int)attribute)
                        newTimeslotList.add(t);
                        break;
                case ID:               
                    for (j = 0; i < timeslotList.size(); j++){
                        t = timeslotList.get(j);
                        if (t.getTimeslotID() == (int)attribute)
                            newTimeslotList.add(t);
                    }
                    break;
                case timeslot:
                    if (t.getTimeslot().equals((LocalDateTime)attribute))
                        newTimeslotList.add(t);
                        break;
                default:
                    break;
            }           
        }
        return newTimeslotList;
    }; 
    
    public MovieTimeslot getTimeslotByID(int ID) {
    	int i;
        ArrayList<MovieTimeslot> timeslotList = getAllTimeslot();
        MovieTimeslot timeslot = null;

        for (i = 0; i < timeslotList.size(); i++){
            timeslot = timeslotList.get(i);
            if (timeslot.getTimeslotID() == ID)
                return timeslot;
        }
        return timeslot;
    }; 
    
    public void updateTimeslotByCinema(int choice, String code, Object oldValue, Object newValue) {
    	int i, j;
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList  = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();

        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            
            if (cinema.getCinemaCode().equals(code)){
                timeslotList = cinema.getMovieTimeslot();
                newTimeslotList.clear();  
                
                for (j = 0; j < timeslotList.size(); j++){
                    MovieTimeslot t = timeslotList.get(j);
                    switch(choice) {
                        case movie:  
                            if (t.getMovie().getID() == (int)oldValue) {
                                if (MovieTimeslotVerifier.isValidSession(code, (Movie) newValue, t.getTimeslot()))
                                    t.setMovie((Movie) newValue);
                            }
                            break;
                        case ID:
                            if (t.getTimeslotID() == (int)oldValue)
                                t.setTimeslotID((int)newValue);
                            break;
                        case timeslot:
                            if (t.getTimeslot().equals((LocalDateTime)oldValue)) {
                                if (MovieTimeslotVerifier.isValidSession(code, t.getMovie(), (LocalDateTime) newValue)) 
                                    t.setTimeslot((LocalDateTime)newValue);
                            }
                            break;           
                        default:
                            break;
                    }
                    newTimeslotList.add(t);
                }
                this.cinemaManager.updateCinema(4, code, newTimeslotList);
                break; 
            }
        }
    } 

    // TODO: Debug updateTimeslotByID
    public void updateTimeslotByID(int choice, int ID, Object newValue) {
    	int i, j;
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
        MovieTimeslot t = null;

        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            timeslotList = cinema.getMovieTimeslot();
            newTimeslotList.clear();

            for (j = 0; j < timeslotList.size(); j++){
                t = timeslotList.get(j);
                
                if (t.getTimeslotID() == ID)
                    switch (choice){
                        case movie:
                            if (MovieTimeslotVerifier.isValidSession(cinema.getCinemaCode(), (Movie)newValue, t.getTimeslot())) 
                                t.setMovie((Movie)newValue);
                            break;
                        case timeslot:
                            if (MovieTimeslotVerifier.isValidSession(cinema.getCinemaCode(), t.getMovie(), (LocalDateTime)newValue))
                                t.setTimeslot((LocalDateTime)newValue);
                            break;
                        case seat:
                            t.setSeatList((SeatList) newValue);
                            break;
                        default:
                            break;
                    }
                newTimeslotList.add(t);
            }

            this.cinemaManager.updateCinema(4, cinema.getCinemaCode(), newTimeslotList);
        }
    }

    public void updateSeatList(int ID, SeatList seatList) {
    	int i, j;
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
        MovieTimeslot timeslot = null;

        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            timeslotList = cinema.getMovieTimeslot();
            newTimeslotList.clear();  // ensure it started without existing session
            
            for (j = 0; j < timeslotList.size(); j++){
                timeslot = timeslotList.get(j);
                if (timeslot.getTimeslotID() == ID){
                    timeslot.setSeatList(seatList);
                }
                newTimeslotList.add(timeslot);
            }
            this.cinemaManager.updateCinema(4, cinema.getCinemaCode(), newTimeslotList);
        }
    } 

    public void updateMovie(int choice, int ID, Object newValue) {
    	int i, j;
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
        
        MovieTimeslot timeslot = null;

        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            timeslotList = cinema.getMovieTimeslot();
            newTimeslotList.clear();  
            
            for (j = 0; j < timeslotList.size(); j++){
                timeslot = timeslotList.get(j);
                if (timeslot.getMovie().getID() == ID){
                    switch (choice){
                        case (MovieManager.ID):
                            timeslot.getMovie().setID((int)newValue);
                            break;
                        case (MovieManager.name):
                        	if(((String)newValue).equals("")) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setName((String)newValue);
                                break;
                        	}
                        case (MovieManager.type):
                            timeslot.getMovie().setType((MovieType)newValue);
                            break;
                        case (MovieManager.synopsis):
                        	if(((String)newValue).equals("")) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setSynopsis((String)newValue);
                                break;
                        	}                           
                        case (MovieManager.rating):
                        	if(((String)newValue).equals("")) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setRating((String) newValue);
                                break;
                        	}
                        case (MovieManager.duration):
                        	if(((double)newValue) < 0) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setDuration((double)newValue);
                                break;
                        	}
                        case (MovieManager.start_date):
                        	if(((LocalDate)newValue).isAfter(timeslot.getMovie().getEndDate())) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setStartDate((LocalDate) newValue);
                                break;
                        	}
                        case (MovieManager.end_date):
                        	if(((LocalDate)newValue).isBefore(timeslot.getMovie().getStartDate())) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setendDate((LocalDate) newValue);
                                break;
                        	}
                        case (MovieManager.director):
                        	if(((String)newValue).equals("")) {
                        		break;
                        	}
                        	else {
                        		timeslot.getMovie().setDirector((String)newValue);
                                break;
                        	}
                        case (MovieManager.cast):
                        	if(((ArrayList<String>)newValue).size() < 2) {
                        		break;
                        	}
                        	else {
                        		int k;
                        		for(k = 0; k < ((ArrayList<String>)newValue).size(); k++) {
                        			if(((ArrayList<String>)newValue).get(k).equals("")) {
                        				break;
                        			}
                        		}
                        	}
                            timeslot.getMovie().setCastList((ArrayList<String>) newValue);
                            break;
                        case (MovieManager.review):
                            timeslot.getMovie().setRatingList((ArrayList<Rating>) newValue);
                            break;
                        default:
                            break;
                    }
                }
                newTimeslotList.add(timeslot);
            }
            this.cinemaManager.updateCinema(4, cinema.getCinemaCode(), newTimeslotList);
        }
    }

    public void deleteTimeslotByCinema(String code, LocalDateTime timeslotDate){
    	int i, j;
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
        
        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            if (cinema.getCinemaCode().equals(code)){
                timeslotList = cinema.getMovieTimeslot();
                newTimeslotList.clear();  
                
                for (j = 0; j < timeslotList.size(); j++){
                    MovieTimeslot timeslot = timeslotList.get(j);
                    if (!(timeslot.getTimeslot().equals(timeslotDate)))
                        newTimeslotList.add(timeslot);
                }
                this.cinemaManager.updateCinema(4, code, newTimeslotList);
                break;
             }
        }
    }

    public void deleteTimeslotByID(int ID){
    	int i, j;
        ArrayList<Cinema> cinemaList  = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();

        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            timeslotList = cinema.getMovieTimeslot();
            newTimeslotList.clear(); 
            
            for (j = 0; j < timeslotList.size(); j++){
                MovieTimeslot timeslot = timeslotList.get(j);
                if (!(timeslot.getTimeslotID() == ID))
                    newTimeslotList.add(timeslot);
            }
            this.cinemaManager.updateCinema(4, cinema.getCinemaCode(), newTimeslotList);
        }
    }

    
    public void deleteTimeslotByMovie(int ID) {
    	int i, j;
        ArrayList<Cinema> cinemaList = this.cinemaManager.getAllCinema();
        ArrayList<MovieTimeslot> timeslotList = new ArrayList<MovieTimeslot>();
        ArrayList<MovieTimeslot> newTimeslotList = new ArrayList<MovieTimeslot>();
        MovieTimeslot timeslot = null;

        for (i = 0; i < cinemaList.size(); i++) {
            Cinema cinema = cinemaList.get(i);
            timeslotList = cinema.getMovieTimeslot();
            newTimeslotList.clear();  // ensure it started without existing session
            for (j = 0; j < timeslotList.size(); j++){
                timeslot = timeslotList.get(j);
                if (!(timeslot.getMovie().getID() == ID))
                    newTimeslotList.add(timeslot);
            }
            this.cinemaManager.updateCinema(4, cinema.getCinemaCode(), newTimeslotList);
        }
    }
    
    public boolean assignSeat(SeatList seatList, int seatID, int timeslotID){
        if(!seatList.isAvailable(seatID)){
            seatList.assignSeat(seatID);
            this.updateSeatList(timeslotID, seatList);
            return true;
        }
        else{
            System.out.println("Model.Seat is already occupied!");
            return false;
        }
    }
}
