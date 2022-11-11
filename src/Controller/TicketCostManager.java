package Controller;

import Model.*;

import java.util.HashMap;
import java.util.Map;

// TODO: Ticket Cost Manager
public class TicketCostManager {
    private HolidayManager holidayManager;
    private Map<TicketCostModifier,Double> ticketCostMap = new HashMap<>();

    // stores ticket prices here as admin manipulates them
    public TicketCostManager(){
        this.holidayManager = new HolidayManager();

        ticketCostMap.put(MovieType.TwoD, 0.0);
        ticketCostMap.put(MovieType.ThreeD, 3.0);
        ticketCostMap.put(MovieType.Blockbuster, 3.0);

        ticketCostMap.put(CinemaType.BronzeClass, 0.0);
        ticketCostMap.put(CinemaType.SilverClass, 1.0);
        ticketCostMap.put(CinemaType.GoldClass, 3.0);

        ticketCostMap.put(TicketCostType.Holiday, 12.0);
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

    public double calculateCost(Cinema Cinema, MovieTimeslot selectedSlot, TicketCostType costType) {
        double totalCost;
        MovieType type = selectedSlot.getMovie().getType();
        totalCost = getCost(Cinema.getCinemaType()) + getCost(type) + getCost(costType);
        return totalCost;
    }
}

