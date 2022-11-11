package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Holiday implements Serializable{

    private LocalDate dateOfHoliday;

    public Holiday(LocalDate dateOfHoliday){
        this.dateOfHoliday = dateOfHoliday;
    }

    // TODO: setdateofholiday
    public void setDateOfHoliday(LocalDate dateOfHoliday){
        this.dateOfHoliday = dateOfHoliday;
    }

    public LocalDate getDateOfHoliday(){
        return dateOfHoliday;
    }

    public String getDateToString(){
        return dateOfHoliday.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public String toString() {
        return "The holiday date is " + this.getDateToString();
    }
}
