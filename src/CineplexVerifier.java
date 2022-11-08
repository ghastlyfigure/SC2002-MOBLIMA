import java.util.ArrayList;

public class CineplexVerifier {
	static CineplexManager cineplexManager = new CineplexManager();
   
    public static boolean isValidCineplex(String name, ArrayList<Cinema> cinemaList) {
        boolean isValid = true;
        if (isExistingCineplex(name)){
        	System.out.println("Cineplex already exists!");
            isValid = false;
        }
        if (isEmptyName(name)) {
        	System.out.println("Cineplex Name cannot be empty!");
        	isValid = false;
        }
        if (!hasThreeCinema(cinemaList))
        	System.out.println("Cineplex must have at least three cinemas!");
            isValid = false;
        
        return isValid;
    }
    
    public static boolean isExistingCineplex(String name) {
        ArrayList<Cineplex> cineplexList = cineplexManager.getAllCineplex();
        int i;
        
        for (i = 0; i < cineplexList.size(); i++) {
            if (cineplexList.get(i).getName().equals(name))
                return true;
        }
        return false;
    }
    
    public static boolean isEmptyName(String name) {
    	if(name.equals("")) {
    		return true;
    	}
    	return false;
    }

    public static boolean hasThreeCinema(ArrayList<Cinema> cinemaList) {
    	if(cinemaList.size() < 3) {
    		return false;
    	}
    	
    	return true;
    }
}
