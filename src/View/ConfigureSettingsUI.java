package View;

import Controller.HolidayManager;
import Controller.InputManager;
import Controller.TicketCostManager;
import Model.Holiday;
import Model.TicketCostType;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ConfigureSettingsUI {
    private int choice;
    private HolidayManager holidayManager = new HolidayManager();
    private TicketCostManager TCM = new TicketCostManager();

    public void main(){
        while (choice != 6){
            configureSettingsMenu();
        }
    }
    public void configureSettingsMenu(){
        System.out.println();
        System.out.println("|=========================================|");
        System.out.println("|=======|Configure System Settings=|======|");
        System.out.println("|=========================================|");
        System.out.println();
        System.out.println("\t(1) Display Public Holidays");
        System.out.println("\t(2) Add a Public Holiday");
        System.out.println("\t(3) Remove a Public Holiday");
        System.out.println("\t(4) Set Public Holiday Charges");
        System.out.println("\t(5) Set Weekend Charges");
        System.out.println("\t(6) Back");
        System.out.println();
        System.out.println("\tPlease select an option: ");

        switch (choice = InputManager.getInt()) {
            case 1 -> displayHolidayList();
            case 2 -> addHoliday();
            case 3 -> removeHoliday();
            case 4 -> setHolidayPrice();
            case 5 -> setWeekendPrice();
            case 6 -> System.out.println("Returning back to Admin Menu");
            default -> System.out.println("Invalid input! Please Try Again.");
        }
    }

    public boolean displayHolidayList(){
        ArrayList<Holiday> holidayList = holidayManager.getAllHolidays();
        if (holidayList.isEmpty()){
            System.out.println("The Holiday List is Empty!");
            return false;
        } else {
            System.out.println("=====================");
            System.out.println("\tHoliday List");
            System.out.println("=====================");
            for (int i=0; i<holidayList.size(); i++){
                System.out.println("Holiday #" + (i+1) + " - " + holidayList.get(i).getDateOfHoliday().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            }
            return true;
        }
    }

    public void addHoliday() {
        displayHolidayList();
        System.out.println("Enter date of holiday in DD/MM/YYYY format");
        LocalDate holiday = InputManager.getDate();
        holidayManager.createHoliday(holiday);
        System.out.println("Successfully Created new Holiday");
    }

    public void removeHoliday() {
        displayHolidayList();
        System.out.println("Enter Date of Holiday to be removed in DD/MM/YYYY format: ");
        LocalDate holiday = InputManager.getDate();
        holidayManager.delete(holiday);
        System.out.println("Holiday successfully removed!");
    }

    public void setHolidayPrice() {
        System.out.println("Enter a New Price to set for Holidays: ");
        double newPrice = InputManager.getDouble();
        TCM.editCostModifier(TicketCostType.Holiday, newPrice);
        System.out.println("New Price successfully set.");
    }

    public void setWeekendPrice() {
        System.out.println("Enter a New Price to set for Weekend: ");
        double newPrice = InputManager.getDouble();
        TCM.editCostModifier(TicketCostType.Weekend, newPrice);
        System.out.println("New Price successfully set");
    }
}
