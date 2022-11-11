package Model;

import java.util.Map;

// TODO: Ticket
public class Ticket {

    private CinemaType cinemaType;
    private MovieType movieType;
    private Movie movie;
    private AgeGroup ageGroup;

    // Constructor
    public Ticket(CinemaType cinemaType, MovieType movieType, Movie movie, AgeGroup ageGroup) {
        this.cinemaType = cinemaType;
        this.movieType = movieType;
        this.movie = movie;
        this.ageGroup = ageGroup;
    }

    public double calculateCost(Map<TicketCostModifier,Double> ticketCostMap, Cinema cinema, MovieTimeslot slot, TicketCostType costType, boolean isHoliday){
        double cost = ticketCostMap.get(cinema.getCinemaType()) +
                ticketCostMap.get(slot.getMovie().getType());
        if(slot.isWeekend())
            cost += 2.0;
        if(isHoliday){
            return cost + ticketCostMap.get(TicketCostType.Holiday);
        }
        else{
            return ticketCostMap.get(costType) + cost;
        }
    }

    @Override
    public String toString() {
        return String.format("");
    }
}
