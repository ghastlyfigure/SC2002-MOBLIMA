package Controller;

import Model.*;

import java.util.HashMap;
import java.util.Map;

public class TicketCostManager {
    private HolidayManager holidayManager;
    private Map<TicketCostModifier,Double> ticketCostMap = new HashMap<>();

    // stores ticket prices here as admin manipulates them
    public TicketCostManager(){
        this.holidayManager = new HolidayManager();

        // Movie Type
        ticketCostMap.put(MovieType.TwoD, 0.0);
        ticketCostMap.put(MovieType.ThreeD, 3.0);
        ticketCostMap.put(MovieType.Blockbuster, 3.0);
        // Cinema Type
        ticketCostMap.put(CinemaType.BronzeClass, 0.0);
        ticketCostMap.put(CinemaType.SilverClass, 1.0);
        ticketCostMap.put(CinemaType.GoldClass, 3.0);
        // Holidays, Weekends and Student, Senior Citizen
        ticketCostMap.put(TicketCostType.Holiday, 4.0);
        ticketCostMap.put(TicketCostType.Normal, 10.0);
        ticketCostMap.put(TicketCostType.SeniorCitizen, 6.0);
        ticketCostMap.put(TicketCostType.Student, 8.0);
        ticketCostMap.put(TicketCostType.Weekend, 3.0);
    }

    public void newCostModifier(TicketCostModifier costModifier, double cost){
        ticketCostMap.put(costModifier, cost);
    }

    public void editCostModifier(TicketCostModifier costModifier, double newCost){
        if(ticketCostMap.containsKey(costModifier))    
            ticketCostMap.put(costModifier, newCost);
        // else don't add anything
    }

    public void removeCostModifier(TicketCostModifier costModifier){
        ticketCostMap.remove(costModifier);
    }

    public Map<TicketCostModifier, Double> getAllCostModifiers(){
        return ticketCostMap;
    }

    public double getCost(TicketCostModifier costModifier){
        return ticketCostMap.getOrDefault(costModifier, 0.0);
    }

    // old cost calculation
//    public double calculateCost(Cinema Cinema, MovieTimeslot selectedSlot, TicketCostType costType) {
//        double totalCost;
//        MovieType type = selectedSlot.getMovie().getType();
//        totalCost = getCost(Cinema.getCinemaType()) + getCost(type) + getCost(costType);
//        return totalCost;
//    }

    // this function checks whether is holiday && || weekend
    // based on input "costType" in this function can be student, senior, or normal ONLY
    public double calculateCost(Cinema cinema, MovieTimeslot slot, TicketCostType costType){

        //cost = cinematype + movietype + persontype
        //all we need is holiday + weekend
        double cost = getCost(cinema.getCinemaType()) + getCost(slot.getMovie().getType()) + getCost(costType);
        boolean holiday = holidayManager.isHoliday(slot.getTimeslot().toLocalDate());
        boolean weekend = slot.isWeekend();

        if(holiday == true && weekend == true){
            return cost + getCost(TicketCostType.Holiday);
        } else {
            if(weekend == true){
                return cost + getCost(TicketCostType.Weekend);
            }
            if(holiday == true){
                return cost + getCost(TicketCostType.Holiday);
            }
        }
        return cost;
    }
}

