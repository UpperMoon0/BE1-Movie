package ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.Movie;
import util.FileManager;
import util.InputManager;

public class AdminMenu extends Menu {
    // Data initialization
    protected static void dataInit() {
        options.clear();
        title = "Choose an option:";
        options.add("Add a movie");
        options.add("Remove movie");
        options.add("Change movie price");
        options.add("Logout");
        options.add("Exit");
    }

    // Choice effect
    protected static void choiceEffect(int choice) {
        switch(choice) {
            case 1:
                // Add a movie
                System.out.println("------------------------");
                addMovie();
                break;
            case 2:
                // Remove movie
                System.out.println("------------------------");
                removeMovie();
                break;
            case 3:
                // Change movie price
                System.out.println("------------------------");
                changeMoviePrice();
                break;
            case 4:
                // Logout
                System.out.println("------------------------");
                UserLoginMenu.menu();
                chooseSuccess = true;
                break;
            case 5:
                // Exit
                System.out.println("Goodbye!");
                chooseSuccess = true;
                break;
            default:
                break;
        }
    }

    // Menu
    public static void menu() {
        // Get the current time
        LocalTime time = LocalTime.now();

        // Greet the user
        if (time.getHour() >= 0 && time.getHour() < 12) {
            System.out.println("Good morning " + UI.getCurrentAccount() + "!");
        } else if (time.getHour() >= 12 && time.getHour() < 18) {
            System.out.println("Good afternoon " + UI.getCurrentAccount() + "!");
        } else {
            System.out.println("Good evening " + UI.getCurrentAccount() + "!");
        }

        // Menu
        dataInit();
        do {
            choiceEffect(getChoice());
        } while (!chooseSuccess);
    }

    // Add a movie
    public static void addMovie() {
        printMovieList();
        do {
            // Create movieData file if not exist
            FileManager.createMovieDataFileIfNotExist();
            // Add movie to file
            Movie movie = new Movie(InputManager.inputMovieName(),
                                    InputManager.inputMoviePrice());
            if (!FileManager.checkMovieExist(movie.getName())) {
                FileManager.writeToFile("data/movieData.txt", movie.toString() + "\n");
                System.out.println(UI.ANSI_GREEN + "Movie added successfully!" + UI.ANSI_RESET);
                System.out.println("------------------------");
                break;
            } else
                System.out.println(UI.ANSI_RED + "Movie already exist, please try again!" + UI.ANSI_RESET);
        } while (true);
    }

    // Remove movie
    public static void removeMovie() {
        List<String> movieData = FileManager.readEveryLine("data/movieData.txt");
        printMovieList();
        System.out.println("Enter the number of movie you want to remove\n");
        Scanner sc = new Scanner(System.in);
        int movieNumber = sc.nextInt();

        if (movieNumber >= 1 && movieNumber <= movieData.size()) {
            movieData.remove(movieNumber - 1);
            try {
                String newData = String.join("\n", movieData);
                Files.write(Paths.get("data/movieData.txt"), newData.getBytes(), StandardOpenOption.TRUNCATE_EXISTING);
                System.out.println("Movie removed successfully!!!");
            } catch (IOException e) {
                System.out.println("Error writing to file.");
            }
        } else {
            System.out.println("Invalid movie number!");
        }
    }

    // Change movie price
    public static void changeMoviePrice() {
        printMovieList();
        do {
            // Create movieData file if not exist
            FileManager.createMovieDataFileIfNotExist();
            // Change movie price
            String movieName = InputManager.inputMovieName();
            if (FileManager.checkMovieExist(movieName)) {
                FileManager.changeMoviePrice(movieName, InputManager.inputMoviePrice());
                System.out.println(UI.ANSI_GREEN + "Movie price changed successfully!" + UI.ANSI_RESET);
                System.out.println("------------------------");
                break;
            } else
                System.out.println(UI.ANSI_RED + "Movie not found, please try again!" + UI.ANSI_RESET);
        } while (true);
    }

    public static void printMovieList() {
        // Get movies list
        List<String> movieData = FileManager.readEveryLine("data/movieData.txt");

        System.out.println("Movie list:");
        int i = 1;
        for (String data : movieData) {
            if (data != null) {
                String[] parts = data.split(",");
                System.out.printf("%d. %s, price: %s VND%n", i, parts[0], parts[1]);
                i++;
            }
        }
    }
}
