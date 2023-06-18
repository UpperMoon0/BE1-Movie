package util;

import java.io.File;
import java.io.IOException;

import ui.UI;

public class FileManager {
    public static File createFile(String path) {
        try {
            File file = new File(path);
            
            // Create the parent directories if they don't exist
            file.getParentFile().mkdirs();

            // Create the file
            if (file.createNewFile()) {
                if (UI.getIsDebug()) 
                    System.out.println("File created: " + file.getName());
            } else {
                if (UI.getIsDebug()) 
                    System.out.println("User data file already exists.");
            }

            return file;
        } catch (IOException e) {
            if (UI.getIsDebug()) 
                e.printStackTrace();          
        }

        return null;
    }
}
