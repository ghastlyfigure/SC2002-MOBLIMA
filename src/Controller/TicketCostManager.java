package Controller;

import Model.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Class which manages price of tickets, and price modifiers such as Weekend or Holiday charge.
 */
public class TicketCostManager {
    private HolidayManager holidayManager;
    private Map<TicketCostModifier,Double> ticketCostMap = new HashMap<>();

    /**
     * Stores default ticket prices here as admin manipulates them
     */
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

    /**
     * Add new cost modifier to the map
     * @param costModifier   Cost modifier to add
     * @param cost           Value of cost modifier
     */
    public void newCostModifier(TicketCostModifier costModifier, double cost){
        ticketCostMap.put(costModifier, cost);
    }

    /**
     * Change the price of given cost modifier if it exists
     * @param costModifier      Cost modifier to add
     * @param newCost           New value for cost modifier
     */
    public void editCostModifier(TicketCostModifier costModifier, double newCost){
        if(ticketCostMap.containsKey(costModifier))    
            ticketCostMap.put(costModifier, newCost);
        // else don't add anything
    }

    /**
     * Remove the cost modifier
     * @param costModifier Cost modifier to be removed
     */
    public void removeCostModifier(TicketCostModifier costModifier){
        ticketCostMap.remove(costModifier);
    }

    /**
     * Return all cost modifiers and their values
     * @return Map of all cost modifiers and their values
     */
    public Map<TicketCostModifier, Double> getAllCostModifiers(){
        return ticketCostMap;
    }

    /**
     * Return value for given cost modifiers
     * @param costModifier      Cost modifiers, whose value is to be retrieved
     * @return Double           The vlaue of given cost modifier
     */
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

    /**
     * Return the total cost based on given parameters
     * Checks whether movie falls on a holiday or weekend, or a combination of both, and takes the higher cost
     * Based on input "costType" in this function can only be student, senior, or normal
     * @param cinema        Cinema required to be calculated
     * @param slot          Time slot required to be calculated
     * @param costType      Cost type required to be calculated
     * @return Double       The total cost of the ticket with given parameters
     */
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

