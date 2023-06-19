package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

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
    public static void writeToFile (String filePath, String dataOfUser) {
        try {
        File file = new File(filePath);       
        file.getParentFile().mkdirs();      
        if (!file.exists()) {
            if (UI.getIsDebug()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println(file.getName() + " already exists.");
            }
            file.createNewFile();
        }      
        PrintWriter writer = new PrintWriter(file);
        writer.println(dataOfUser);
        writer.close();
        if (UI.getIsDebug()) {
            System.out.println("Content written to the file: " + file.getName());
        }
    } catch (IOException e) {
        if (UI.getIsDebug()) {
            e.printStackTrace();
        }
        }
    }
    public static void readFromFile (String pathname) {
        File file = new File(pathname);
        if (!file.exists()) {
            System.out.println("FIle not exist!!!");
            System.out.close();
        }
        try {   
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferReader = new BufferedReader(fileReader);
            String line;
            while((line = bufferReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String inputString (String massage) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(massage);
            String value = sc.nextLine();
            if (!value.isEmpty()) {
                return value;
            }
        } while (true);
    }
    
}