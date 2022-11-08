import java.util.ArrayList;
import java.io.File;  
import java.io.IOException;  
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class CineplexManager {
    public final static String filename = "database/cineplex.txt";

    public final static int name = 0;
    public final static int cinema = 1;
    
    public void createCineplex(String name, ArrayList<Cinema> cinemaList) {
        if (CineplexVerifier.isValidCineplex(name, cinemaList)) {
            Cineplex cineplex = new Cineplex(name, cinemaList);
            ArrayList<Cineplex> cineplexList = new ArrayList<Cineplex>();
            File cineplexFile = new File(filename);
            if (cineplexFile.exists()) 
                cineplexList = getAllCineplex();
            try {
                ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
                cineplexList.add(cineplex);
                out.writeObject(cineplexList);
                out.flush();
                out.close();
            } catch (IOException e) {
                //
            }
        }
    }

    public ArrayList<Cineplex> getAllCineplex(){
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));   
            ArrayList<Cineplex> cineplexList = (ArrayList<Cineplex>) in.readObject();
            in.close();
            return cineplexList;
        } catch (ClassNotFoundException | IOException e) {
            // 
        }
        return new ArrayList<Cineplex>();
    }; 
    
    public Cineplex getCineplex(String name){
    	int i;
        ArrayList<Cineplex> cineplexList = getAllCineplex();
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if (cineplex.getName().equals(name))
                return cineplex;
        }
        return null;
    };
    
    public void updateCineplex(String oldName, String newName){   
    	int i;
    	ArrayList<Cineplex> cineplexList = getAllCineplex();
        ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();
                    
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if (cineplex.getName().equals(oldName)) {
                cineplex.setName(newName);
            	newCineplexList.add(cineplex);
            }
            updateFile(filename, newCineplexList);
        }
    }

    public void deleteCineplex(String name){
        int i;
    	ArrayList<Cineplex> cineplexList = getAllCineplex();
        ArrayList<Cineplex> newCineplexList = new ArrayList<Cineplex>();
        
        for (i = 0; i < cineplexList.size(); i++){
            Cineplex cineplex = cineplexList.get(i);
            if (!cineplex.getName().equals(name))
                newCineplexList.add(cineplex);
        }

        updateFile(filename, newCineplexList);
    }

    public void updateFile(String filename, ArrayList<Cineplex> cineplexList){
        File tempFile = new File(filename);
        if (tempFile.exists()) 
            tempFile.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
            out.writeObject(cineplexList);
            out.flush();
            out.close();
        } catch (IOException e) {
            //
        }
    }
}
