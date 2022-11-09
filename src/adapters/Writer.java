package adapters;

import java.io.*;

public class Writer {
    // wewant to update whatever that can be serialized
    public static void update(String fileName, Serializable s) {
        File file = new File(fileName);
        if (file.exists())
            file.delete();
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
            out.writeObject(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            // TODO: Print error
        }
    }
}
