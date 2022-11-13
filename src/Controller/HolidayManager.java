package Controller;

import Model.Holiday;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

//
public class HolidayManager {

    /**
     * File name of database file to access
     */
    public final static String filename = "database/holidays.txt";

    /**
     * Create a new holiday and add it into the database file after validating the attributes
     * If attributes are invalid, do nothing
     * If database file exists, read existing records, append a new holiday object, and save the file
     * If database file does not exist, write holiday object to a new file and save
     * @param holidayDate   Date of this holiday
     */
    public void createHoliday(LocalDate holidayDate) {
        Holiday holiday = new Holiday(holidayDate);
        ArrayList<Holiday> holidayData = new ArrayList<Holiday>();
        File holidayFile = new File(filename);
        if (holidayFile.exists()) 
            holidayData = getAllHolidays();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            holidayData.add(holiday);
            out.writeObject(holidayData);
            out.flush();
            out.close();
        } catch (IOException e) {
            // 
        }
    }

    /**
     * Read every Cineplex in the database file and return them
     * If database file is not found, ignore error and return an empty list
     * @return Model.{@link Holiday}    Return list of Holidays if found, else empty list
     */
    public ArrayList<Holiday> getAllHolidays() {
        try {
            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(filename));   
            ArrayList<Holiday> holidayList = (ArrayList<Holiday>) oi.readObject();
            oi.close();
            return holidayList;
        } catch (ClassNotFoundException | IOException e) {
            // ignore error
        } 
        return new ArrayList<Holiday>();
    }

    /**
     * Delete a Holiday in the Database file corresponding to the date attribute passed in
     * @param dateGiven  Date of Holiday to delete
     */
    public void delete(LocalDate dateGiven) {
        ArrayList<Holiday> allHolidayData = getAllHolidays();
        ArrayList<Holiday> newHolidayData = new ArrayList<Holiday>();
        
        for (int i=0; i<allHolidayData.size(); i++){
            Holiday h = allHolidayData.get(i);
            if (!(h.getDateOfHoliday().equals(dateGiven)))
                newHolidayData.add(h);
        }

        overwriteFile(filename, newHolidayData);
    }

    /**
     * Overwrite database file with new data list of holiday
     * @param filename      Filename to check for
     * @param data          New ArrayList of Holiday to write to the file
     */
    public void overwriteFile(String filename, ArrayList<Holiday> data){
        File holidayFile = new File(filename);
        if (holidayFile.exists()) 
            holidayFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(data);
            out.flush();
            out.close();
        } catch (IOException e) {
            // ignore error
        }
    }

    /**
     * Verify a date is a holiday by checking against every record in the database file
     * @param dateGiven     Date of Holiday to check
     * @return boolean      Return true if input date is a holiday, else false
     */
    public boolean isHoliday(LocalDate dateGiven) {
        ArrayList<Holiday> allHolidayData = getAllHolidays();
        for (int i=0; i<allHolidayData.size(); i++){
            if (allHolidayData.get(i).getDateOfHoliday().equals(dateGiven))
                return true;
        }
        return false;
    }
    
}
