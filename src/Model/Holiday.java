package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Holiday
 * The price of movie tickets will be more expensive on a holiday
 */
public class Holiday implements Serializable{

    /**
     * this Holiday's date
     */
    private LocalDate dateOfHoliday;

    /**
     * Creates a Holiday with the given attributes
     * @param dateOfHoliday					This Holiday's date
     */
    public Holiday(LocalDate dateOfHoliday){
        this.dateOfHoliday = dateOfHoliday;
    }

    /**
     * Changes the date of this Holiday
     * @param dateOfHoliday This Holiday's new date.
     */
    public void setDateOfHoliday(LocalDate dateOfHoliday){
        this.dateOfHoliday = dateOfHoliday;
    }

    /**
     * Gets the date of this Holiday.
     * @return This Holiday's date.
     */
    public LocalDate getDateOfHoliday(){
        return dateOfHoliday;
    }

    /**
     * Converting this Holiday's date to a string for better readability
     * @return String This Holiday's date in string format
     */
    public String getDateToString(){
        return dateOfHoliday.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    /**
     * String to return when this Holiday is being called
     * @return String
     */
    public String toString() {
        return "The holiday date is " + this.getDateToString();
    }
}
