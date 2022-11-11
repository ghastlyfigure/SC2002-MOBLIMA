package Controller;

import Model.Holiday;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

//
public class HolidayManager {
    
    public final static String filename = "database/holidays.txt";

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

    public boolean isHoliday(LocalDate dateGiven) {
        ArrayList<Holiday> allHolidayData = getAllHolidays();
        for (int i=0; i<allHolidayData.size(); i++){
            if (allHolidayData.get(i).getDateOfHoliday().equals(dateGiven))
                return true;
        }
        return false;
    }
    
}
