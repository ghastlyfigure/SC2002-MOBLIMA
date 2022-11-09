package adapters;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Reader {
    public static Object read(String fileName) {
        Object o = new Object();

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
            o = in.readObject();
            in.close();
        } catch (ClassNotFoundException | IOException e) {
            // TODO Print error
        }

        return o;
    }
}
