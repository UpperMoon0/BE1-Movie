package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import core.Movie;
import ui.UI;

public class FileManager {
    // Create a file
    public static File createFileIfNotExist(String path) {
        try {
            File file = new File(path);
            
            // Create the file if it doesn't exist
            if (!file.exists()) {
                // Create the parent directories if they don't exist
                file.getParentFile().mkdirs();

                // Create the file
                file.createNewFile();
            }

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
            // Create the file if it doesn't exist
            createFileIfNotExist(path);

            // Write to the file
            BufferedWriter bw = new BufferedWriter(new FileWriter(path, true));
            bw.append(str);
            bw.close();
        } catch (Exception e) {
            if (UI.getIsDebug()) 
                e.printStackTrace();
        }
    }

    // Read from a file
    public static List<String> readEveryLine(String path) {
        File file = new File(path);
        List<String> fileContent = new ArrayList<String>();

        // Check if file exists
        if (file.exists()) {
            try {
                // Read data from file
                BufferedReader br = new BufferedReader(new FileReader(path));
                String line;
                // Read line by line
                while ((line = br.readLine()) != null) {
                    fileContent.add(line);
                }

                br.close();
            } catch (Exception e) {
                if (UI.getIsDebug()) 
                    e.printStackTrace();
            }
        } else {
            if (UI.getIsDebug()) 
                System.out.println("Read every line failed, file doesn't exist! " + path);
        }

        return fileContent;
    }

    // Check if a username exists in a file
    public static boolean checkUsernameExist(String username) {
        String path = "data/userData.txt";
        try {
            // Create the file if it doesn't exist
            createFileIfNotExist(path);

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
            // Create the file if it doesn't exist
            createFileIfNotExist(path);

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

    public static void createMovieDataFileIfNotExist() {
        String path = "data/movieData.txt";

        File file = new File(path);
        try {
            // Check if file exists
            if (!file.exists()) {
                // Default movies 
                List<Movie> defaultMoviesList = new ArrayList<Movie>();
                defaultMoviesList.add(new Movie("Spiderman", 100000));
                defaultMoviesList.add(new Movie("Batmann", 100000));
                defaultMoviesList.add(new Movie("Venom", 100000));
                defaultMoviesList.add(new Movie("Superman", 100000));
                defaultMoviesList.add(new Movie("Balika Vadhu: The Child Bride", 70000));
                defaultMoviesList.add(new Movie("Detective Conan: The Scarlet Bullet", 120000));
                defaultMoviesList.add(new Movie("The forest", 100000));
                defaultMoviesList.add(new Movie("Joker", 100000));
                defaultMoviesList.add(new Movie("Rambo", 100000));
                defaultMoviesList.add(new Movie("Annabelle", 100000));
                defaultMoviesList.add(new Movie("Demon Slayer: Kimetsu no Yaiba the Movie: Mugen Train", 140000));
                defaultMoviesList.add(new Movie("Demon Slayer: Kimetsu no Yaiba To the Swordsmith Village", 140000));
                defaultMoviesList.add(new Movie("Your name", 120000));
                defaultMoviesList.add(new Movie("Weathering with you", 120000));
                defaultMoviesList.add(new Movie("Suzume", 120000));

                // Create the file if it doesn't exist
                createFileIfNotExist(path);

                // Write to the file
                for (Movie mv : defaultMoviesList) {
                    writeToFile(path, mv.toString() + "\n");
                }
            }
        } catch (Exception e) {
            if (UI.getIsDebug())
                e.printStackTrace();
        }
    }
}