package util;

import java.io.File;
import java.util.ArrayList;


public class DataInit {
    public static void dataInit() {
        File movieData = new File("data/movieData.txt");
        // Check file exist
        if (!movieData.exists()) {
            FileManager.createFileIfNotExist("data/movieData.txt");
        }
        // Enter 10 movies default to file
        ArrayList<String> movieDefault = new ArrayList<>();
        movieDefault.add("Spiderman\n");
        movieDefault.add("Batmann\n");
        movieDefault.add("Venom\n");
        movieDefault.add("Superman\n");
        movieDefault.add("Cô dâu 8 tuổi\n");
        movieDefault.add("Thám tử Conan\n");
        movieDefault.add("The forest\n");
        movieDefault.add("Joker\n");
        movieDefault.add("Rambo\n");
        movieDefault.add("Thợ săn kho báu");
        for (int i = 0; i < 10; i++) {
            FileManager.writeToFile("data/movieData.txt", movieDefault.get(i));
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
