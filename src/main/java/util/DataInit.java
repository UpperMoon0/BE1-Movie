package util;

import java.io.File;


public class DataInit {
    public static void dataInit() {
        FileManager.createMovieDataFileIfNotExist();
    }
    // add movie when there are already 10 default movies
    public static void addMovie() {
        do {
            File movieData = new File("data/movieData.txt");
            // Check file
            if (!movieData.exists()) {
                System.out.println("File doesn't exist.");
            }
            // Add movie to file
            String addMovie = InputManager.inputString("Enter a movie");
            // If admin enters "Quit", the program will quit.
            // Note eualsIgnorCase checks the string entered by admin, it doesnot interest uppercase or lowercase.
            // Quit --> true, Dragonball --> false.q
            if (addMovie.equalsIgnoreCase("Quit")) {
                break;
            }
            FileManager.writeToFile(String.valueOf(movieData), addMovie + "\n");
        } while (true);
    }
}
