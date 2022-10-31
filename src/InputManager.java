import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputManager {
    private static Scanner sc = new Scanner(System.in);

    public static String getString(){
        String input = "";
        boolean valid = false;
        while(valid == false) {
        	input = sc.nextLine();
        	if(input.equals("")) {
        		System.out.println("Cannot be empty, try again!");
        	}
        	else {
        		valid = true;
        	}
        }
        return input;
    }
    
    public static int getInt(){
        int input = -1;
        boolean valid = false;
        while(valid == false) {
            if(sc.hasNextInt() == true){
                input = sc.nextInt();
                valid = true;
            }
            else{
                System.out.println("Wrong input!");
            }
            sc.nextLine();
        }
        return input;
    }
    
    public static LocalDate getDate() {
    	LocalDate date = null;
        String input;
        boolean valid = false;
        while(valid == false){
            try{
                input = sc.nextLine();
                date = LocalDate.parse(input, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                valid = true;
            }
            catch(DateTimeParseException e){
                System.out.println("Must be of pattern DD/MM/YYYY!");
            }
        }
        return date;
    }
}
