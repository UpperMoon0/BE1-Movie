package ui;

import java.time.LocalTime;
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
        InputManager.printMovieList();
        do {
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
        List<String> movieData = InputManager.printMovieList();

        System.out.println("Please choose a movie number to remove:");

        do {
            // Remove movie
            UI.sc = new Scanner(System.in);
            int movieNumber = UI.sc.nextInt();
            if (movieNumber > 0 && movieNumber <= movieData.size()) {
                String[] parts = movieData.get(movieNumber - 1).split(",");
                String movie = parts[0];
                FileManager.removeMovie(movie);
                System.out.println(UI.ANSI_GREEN + "Movie removed successfully!" + UI.ANSI_RESET);
                System.out.println("------------------------");
                break;
            } else {
                System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }

    // Change movie price
    public static void changeMoviePrice() {
        List<String> movieData = InputManager.printMovieList();

        System.out.println("Please choose a movie number to change price:");

        do {
            // Change movie price
            UI.sc = new Scanner(System.in);
            int movieNumber = UI.sc.nextInt();
            if (movieNumber > 0 && movieNumber <= movieData.size()) {
                String[] parts = movieData.get(movieNumber - 1).split(",");
                String movie = parts[0];
                FileManager.changeMoviePrice(movie, InputManager.inputMoviePrice());
                System.out.println(UI.ANSI_GREEN + "Movie price changed successfully!" + UI.ANSI_RESET);
                System.out.println("------------------------");
                break;
            } else {
                System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }
}
