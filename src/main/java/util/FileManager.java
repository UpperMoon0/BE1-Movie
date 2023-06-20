package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import ui.UI;

public class FileManager {
    // Create a file
    public static File createFile(String path) {
        try {
            File file = new File(path);
            
            // Create the parent directories if they don't exist
            file.getParentFile().mkdirs();

            // Create the file
            file.createNewFile();

            return file;
        } catch (Exception e) {
            if (UI.getIsDebug()) 
                e.printStackTrace();          
        }

        return null;
    }

    // Write to a file
    public static void writeToFile(String path, String str) {
        try {
            File file = new File(path);   
            
            // Create the file if it doesn't exist
            if (!file.exists()) 
                file.createNewFile();

            // Write to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
            bw.append(str);
            bw.close();
        } catch (Exception e) {
            if (UI.getIsDebug()) 
                e.printStackTrace();
        }
    }

    // Check if a username exists in a file
    public static boolean checkUsernameExist(String username) {
        String path = "data/userData.txt";
        try {
            File file = new File(path);   
            
            // Create the file if it doesn't exist
            if (!file.exists())
                file.createNewFile();

            // Read the file
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].contains(username)) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (Exception e) {
            if (UI.getIsDebug()) 
                e.printStackTrace();
        }
        return false;
    }

    // Check if a user exists in a file
    public static boolean checkUsernamePasswordMatch(String userData) {
        String path = "data/userData.txt";
        try {
            File file = new File(path);   
            
            // Create the file if it doesn't exist
            if (!file.exists())
                file.createNewFile();

            // Read the file
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(userData)) {
                    br.close();
                    return true;
                }
            }
            br.close();
        } catch (Exception e) {
            if (UI.getIsDebug()) 
                e.printStackTrace();
        }
        return false;
    }
}