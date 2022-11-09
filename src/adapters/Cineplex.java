package adapters;

import java.util.ArrayList;

public class Cineplex {
    public final static String FILENAME = "database/cineplex.txt";

    public void insertCineplex(interfaces.Cineplex cineplex) {
        ArrayList<interfaces.Cineplex> cineplexList = this.getAllCineplex();
        cineplexList.add(cineplex);
        Writer.update(FILENAME, cineplexList);
    }

    public ArrayList<interfaces.Cineplex> getAllCineplex() {
        return (ArrayList<interfaces.Cineplex>) Reader.read(FILENAME);
    }

    public void updateCineplex(String oldName, String newName){
        ArrayList<interfaces.Cineplex> cineplexList = getAllCineplex();

        interfaces.Cineplex c = getCineplex(cineplexList, oldName);
        if (c != null) {
            c.setName(newName);
        }

        Writer.update(FILENAME, cineplexList);
    }

    public interfaces.Cineplex getCineplex(ArrayList<interfaces.Cineplex> cineplexList, String name){
        for (interfaces.Cineplex c : cineplexList){
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }

    public void deleteCineplex(String name){
        ArrayList<interfaces.Cineplex> cineplexList = getAllCineplex();
        ArrayList<interfaces.Cineplex> newCineplexList = new ArrayList<interfaces.Cineplex>();

        for (int i = 0; i < cineplexList.size(); i++){
            interfaces.Cineplex cineplex = cineplexList.get(i);
            if (!cineplex.getName().equals(name))
                newCineplexList.add(cineplex);
        }

        Writer.update(FILENAME, newCineplexList);
    }
}
