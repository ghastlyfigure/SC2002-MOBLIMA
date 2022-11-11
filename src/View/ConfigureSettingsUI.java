package View;

import Controller.HolidayManager;
import Controller.InputManager;
import Controller.TicketCostManager;
import Model.Holiday;
import Model.TicketCostType;

import java.time.LocalDate;
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
        System.out.println("(1) Display Public Holidays");
        System.out.println("(2) Add a Public Holiday");
        System.out.println("(3) Remove a Public Holiday");
        System.out.println("(4) Set Public Holiday Charges");
        System.out.println("(5) Set Weekend Charges");
        System.out.println("(6) Back");

        switch (choice = InputManager.getInt()) {
            case 1 -> displayHolidayList();
            case 2 -> addHoliday();
            case 3 -> removeHoliday();
            case 4 -> setHolidayPrice();
            case 5 -> setWeekendPrice();
            case 6 -> System.out.println("Returning back to Admin Menu");
            default -> System.out.println("Invalid input! Please try again.");
        }
    }

    public boolean displayHolidayList(){
        ArrayList<Holiday> holidayList = holidayManager.getAllHolidays();
        if (holidayList.isEmpty()){
            System.out.println("The Holiday List is Empty!");
            return false;
        } else {
            for (Model.Holiday h : holidayList) {
                System.out.println(h.getDateOfHoliday());
            }
            return true;
        }
    }

    public void addHoliday() {
        System.out.println("Enter date of holiday in DD/MM/YYYY format");
        LocalDate holiday = InputManager.getDate();
        holidayManager.createHoliday(holiday);
        System.out.println("Successfully Created new Holiday");
    }

    public void removeHoliday() {
        System.out.println("Enter date of holiday in DD/MM/YYYY format to remove");
        LocalDate holiday = InputManager.getDate();
        holidayManager.delete(holiday);
        System.out.println("Holiday removed");
    }

    public void setHolidayPrice() {
        System.out.println("Enter a new price to set for holidays: ");
        double newPrice = InputManager.getDouble();
        TCM.editCostModifier(TicketCostType.Holiday, newPrice);
        System.out.println("New price set.");
    }

    public void setWeekendPrice() {
        System.out.println("Enter a new price to set for weekend: ");
        double newPrice = InputManager.getDouble();
        TCM.editCostModifier(TicketCostType.Weekend, newPrice);
        System.out.println("New price set.");
    }
}
