package util;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import core.Movie;
import ui.UI;

public class InputManager {
    // Get user input for a string
    public static String inputString (String message) {
        String inputStr;

        do {
            System.out.println(message);
            UI.sc = new Scanner(System.in);
            inputStr = UI.sc.nextLine();
            if (inputStr.isEmpty()) 
                System.out.println("String can't be empty, please try again!");
        } while (inputStr.isEmpty());

        return inputStr;
    }

    // Get user input for username
    public static String inputUsername () {
        String inputStr;

        do {
            inputStr = inputString("Enter your username:");
            if (!inputStr.matches("^[A-Za-z]\\d{1,}[A-Za-z0-9]{2,10}$")) {
                System.out.println("Username must:");
                System.out.println("- Start with a letter");
                System.out.println("- Contain at least 1 digit");
                System.out.println("- Length from 4 to 12 characters");
                System.out.println("- Can't contain any special characters");
            }
        } while (!inputStr.matches("^[A-Za-z]\\d{1,}[A-Za-z0-9]{4,12}$"));

        return inputStr;
    }

    // Get user input for password
    public static String inputPassword () {
        String inputStr;

        do {
            inputStr = inputString("Enter your password:");
            if (!inputStr.matches("(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]{1,12}"))
                System.out.println("Password must contain at least 1 digit and 1 uppercase letter, without any special characters, and can't be longer than 12 characters, please try again!");
        } while (!inputStr.matches("(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]{1,12}"));

        return inputStr;
    }

    // Get user input for choosing a movie
    public static String inputMovie() {
        // Get movies list
        List<Movie> moviesList = new ArrayList<Movie>(); 
        List<String> movieData = FileManager.readEveryLines("data/movieData.txt");
        for (String movie : movieData) {
            if (movie != null) {
                String[] parts = movie.split(",");
                moviesList.add(new Movie(parts[0], Integer.parseInt(parts[1])));
            }
        }

        // Show movies list
        System.out.println("Choose a movie:");
        for (int i = 0; i < moviesList.size(); i++) {
            System.out.println((i + 1) + ". " + moviesList.get(i));
        }

        // Get user input
        System.out.println("Your choice:");
        int inputInt;

        do {
            inputInt = UI.sc.nextInt();
            if (inputInt > 0 && inputInt <= moviesList.size())
                return moviesList.get(inputInt - 1).getName() + ", price: " + moviesList.get(inputInt - 1).getPrice() + " VND";
            else
                System.out.println("Invalid choice, please try again!");
        } while (true);
    }

    // Get user input for choosing a showtime
    public static LocalDateTime inputShowtime() {
        String inputDate,
               inputTime;

        LocalDateTime inputDateTime;

        System.out.println("Choose a showtime:");

        do {
            // Input date
            inputDate = inputString("Date (dd/MM/yyyy):");

            // Show all available showtimes, input time
            System.out.println("Time (HH:mm):");
            for (LocalTime showtime : Movie.getShowtimesList()) {
                System.out.println(showtime.toString());
            }
            inputTime = inputString("Your choice:");

            // Check if input is valid
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                inputDateTime = LocalDateTime.parse(inputDate + " " + inputTime, formatter);

                if (!inputDateTime.isAfter(LocalDateTime.now()))
                    System.out.println("Can't choose past showtime, please try again!!");
                else if (!Movie.getShowtimesList().contains(inputDateTime.toLocalTime()))
                    System.out.println("Must choose a showtime listed above, please try again!");
                else 
                    return inputDateTime;
            } catch (Exception e) {
                System.out.println("Invalid date or time, please try again!");
            }
        } while (true);
    }

    // Get user input for password
    public static String inputSeat() {
        String inputStr;

        do {
            inputStr = inputString("Choose a seat:");
            if (!inputStr.matches("^[A-J]{1}\\d$"))
                System.out.println("Invalid seat, please try again!");
        } while (!inputStr.matches("^[A-J]{1}\\d$"));

        return inputStr;
    }
}
