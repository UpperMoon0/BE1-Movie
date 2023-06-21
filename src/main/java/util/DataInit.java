package util;

import java.io.File;


public class DataInit {
    public static void dataInit() {
        File movieData = new File("data/movieData.txt");
        // Check file exist
        if (!movieData.exists()) {
            FileManager.createFileIfNotExist("data/movieData.txt");
        }
        // Enter 10 movies default to file
        for (int i = 0; i < 10; i++) {
            String enterMovie = InputManager.inputString("Enter" + i + "movie");
            FileManager.writeToFile("data/movieData.txt", enterMovie + "\n");
        }
    }
    // add movie when there are already 10 default movies
    public static void addMovie() {
        do {
            File movieData = new File("data/movieData.txt");
            // Check file
            if (!movieData.exists()) {
                System.out.println("File doesn't exist.");
            }
            // Read file
            FileManager.readFromFile(String.valueOf(movieData));
            // Add movie to file
            String addMovie = InputManager.inputString("Enter a movie");
            // If admin enters "Quit", the program will quit.
            // Note equalsIgnorCase checks the string entered by admin, it doesnot interest uppercase or lowercase
            // Quit --> true, Dragonball --> false
            if (addMovie.equalsIgnoreCase("Quit")) {
                break;
            }
            FileManager.writeToFile(String.valueOf(movieData), addMovie + "\n");
        } while (true);
    }
}
