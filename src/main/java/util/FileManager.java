package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.Movie;
import ui.UI;

public class FileManager {
    // Create a file if it doesn't exist
    public static File createFileIfNotExist(String path) {
        File file = new File(path);

        // Create the file if it doesn't exist
        if (!file.exists()) {
            // Create the parent directories if they don't exist
            file.getParentFile().mkdirs();

            try {
                // Create the file
                file.createNewFile();
            } catch (IOException e) {
                if (UI.getIsDebug())
                    e.printStackTrace();
            }
        }

        return file;
    }

    // Write to a file
    public static void writeToFile(String path, String str) {
        // Create the file if it doesn't exist
        createFileIfNotExist(path);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            // Write to the file
            bw.append(str);
        } catch (IOException e) {
            if (UI.getIsDebug())
                    e.printStackTrace();
        }
    }

    // Read from a file
    public static List<String> readEveryLine(String path) {
        List<String> fileContent = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            // Read line by line
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
        } catch (FileNotFoundException e) {
            if (UI.getIsDebug())
                    e.printStackTrace();
        } catch (IOException e) {
            if (UI.getIsDebug())
                    e.printStackTrace();
        }

        return fileContent;
    }

    // Check if a username exists in a file
    public static boolean checkUsernameExist(String username, boolean isAdmin) {
        String path = isAdmin ? "data/adminData.txt" : "data/userData.txt";

        // Create the file if it doesn't exist
        createFileIfNotExist(path);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    return true;
                }
            }
        } catch (IOException e) {
            if (UI.getIsDebug())
                    e.printStackTrace();
        }

        return false;
    }

    // Check if a user exists in a file
    public static boolean checkUsernamePasswordMatch(String userData, boolean isAdmin) {
        String path = isAdmin ? "data/adminData.txt" : "data/userData.txt";

        // Create the file if it doesn't exist
        createFileIfNotExist(path);

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals(userData)) {
                    return true;
                }
            }
        } catch (IOException e) {
            if (UI.getIsDebug())
                    e.printStackTrace();
        }

        return false;
    }

    // Create movieData file if not exist
    public static void createMovieDataFileIfNotExist() {
        String path = "data/movieData.txt";

        File file = new File(path);
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

            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                // Write to the file
                for (Movie mv : defaultMoviesList) {
                    bw.write(mv.toString());
                    bw.newLine();
                }
            } catch (IOException e) {
                if (UI.getIsDebug())
                    e.printStackTrace();
            }
        }
    }

    // Check if a movie exists in a file
    public static boolean checkMovieExist(String movie) {
        String path = "data/movieData.txt";

        // Create the file if it doesn't exist
        createMovieDataFileIfNotExist();

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(movie)) {
                    return true;
                }
            }
        } catch (IOException e) {
            if (UI.getIsDebug())
                e.printStackTrace();
        }

        return false;
    }

    // Change movie price
    public static void changeMoviePrice(String movie, int price) {
        String path = "data/movieData.txt";

        // Create the file if it doesn't exist
        createMovieDataFileIfNotExist();

        List<String> fileContent = new ArrayList<>();

        // Read the file
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                // Change the price of the movie
                if (parts[0].equals(movie)) {
                    fileContent.add(movie + "," + price);
                } else {
                    fileContent.add(line);
                }
            }
        } catch (IOException e) {
            if (UI.getIsDebug())
                e.printStackTrace();
        }

        // Write to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            for (String str : fileContent) {
                bw.write(str);
                bw.newLine();
            }
        } catch (IOException e) {
            if (UI.getIsDebug())
                e.printStackTrace();
        }
    }
}