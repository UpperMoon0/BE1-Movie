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
    public static String inputString(String message) {
        String inputStr;
        UI.sc = new Scanner(System.in);

        do {
            System.out.println(message);
            inputStr = UI.sc.nextLine().trim();
            if (inputStr.isEmpty()) {
                System.out.println(UI.ANSI_RED + "Input can't be empty, please try again!" + UI.ANSI_RESET);
            }
        } while (inputStr.isEmpty());

        return inputStr;
    }

    // Get user input for username
    public static String inputUsername() {
        String inputStr;
        String regex = "^[a-zA-Z][a-zA-Z0-9]{3,11}$";

        do {
            inputStr = inputString("Enter your username:");
            if (!inputStr.matches(regex)) {
                System.out.println(UI.ANSI_RED +
                    "Username must:\n" +
                    "- Start with a letter\n" +
                    "- Contain at least 1 digit\n" +
                    "- Length from 4 to 12 characters\n" +
                    "- Can't contain any special characters\n" +
                    "Please try again!" +
                    UI.ANSI_RESET);
            }
        } while (!inputStr.matches(regex));

        return inputStr;
    }

    // Get user input for password
    public static String inputPassword() {
        String inputStr;
        String regex = "(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]{1,12}";

        do {
            inputStr = inputString("Enter your password:");
            if (!inputStr.matches(regex)) {
                System.out.println(UI.ANSI_RED + 
                    "Password must:\n" +
                    "- Contain at least 1 digit and 1 uppercase letter\n" +
                    "- Without any special characters,\n" +
                    "- Can't be longer than 12 characters\n" +
                    "Please try again!" + 
                    UI.ANSI_RESET);
            }
        } while (!inputStr.matches(regex));

        return inputStr;
    }


    // Get user input for choosing a movie
    public static String inputMovie() {
        FileManager.createMovieDataFileIfNotExist();
        // Get movies list
        List<Movie> moviesList = new ArrayList<Movie>();
        List<String> movieData = FileManager.readEveryLine("data/movieData.txt");

        System.out.println("Choose a movie:");
        int i = 1;
        for (String data : movieData) {
            if (data != null) {
                String[] parts = data.split(",");
                moviesList.add(new Movie(parts[0], Integer.parseInt(parts[1])));
                System.out.printf("%d. %s, price: %s VND%n", i, parts[0], parts[1]);
                i++;
            }
        }

        // Get user input
        System.out.println("Your choice:");
        int inputInt;

        do {
            try {
                UI.sc = new Scanner(System.in);
                inputInt = UI.sc.nextInt();
                if (inputInt > 0 && inputInt <= moviesList.size()) {
                    return moviesList.get(inputInt - 1).getName();
                } else {
                    System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
            }
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
                System.out.printf("%s%n", showtime);
            }
            inputTime = inputString("Your choice:");

            // Check if input is valid
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                inputDateTime = LocalDateTime.parse(inputDate + " " + inputTime, formatter);

                if (!inputDateTime.isAfter(LocalDateTime.now())) {
                    System.out.println(UI.ANSI_RED + "Showtime must be in the future, please try again!" + UI.ANSI_RESET);
                } else if (!Movie.getShowtimesList().contains(inputDateTime.toLocalTime())) {
                    System.out.println(UI.ANSI_RED + "Invalid showtime, please try again!" + UI.ANSI_RESET);
                } else {
                    return inputDateTime;
                }
            } catch (Exception e) {
                System.out.println(UI.ANSI_RED + "Invalid showtime, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }


    // Get user input for choosing a seat
    public static String inputSeat(String movie, LocalDateTime showtime) {
        String inputStr;

        List<String> availableSeatsList = SeatMap.printSeatMap(movie, showtime);

        do {
            inputStr = inputString("Choose a seat:");
            if (!availableSeatsList.contains(inputStr)) {
                System.out.println(UI.ANSI_RED + "Seat is not available, please try again!" + UI.ANSI_RESET);
            }
        } while (!availableSeatsList.contains(inputStr));

        return inputStr;
    }

    // Get admin input for movie name
    public static String inputMovieName() {
        String inputStr;
        String regex = "^[a-zA-Z0-9\\s]+$";

        do {
            inputStr = inputString("Enter the movie name:");
            if (!inputStr.matches(regex)) {
                System.out.println(UI.ANSI_RED + 
                    "Movie name must:\n" +
                    "- Not contain any special characters\n" +
                    "Please try again!" +
                    UI.ANSI_RESET);
            }
        } while (!inputStr.matches(regex));

        return inputStr;
    }

    // Get admin input for movie price
    public static int inputMoviePrice() {
        int inputInt;

        do {
            try {
                inputInt = Integer.parseInt(inputString("Enter the movie price:"));
                if (inputInt > 0) {
                    return inputInt;
                } else {
                    System.out.println(UI.ANSI_RED + "Invalid price, please try again!" + UI.ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(UI.ANSI_RED + "Invalid price, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }
}
