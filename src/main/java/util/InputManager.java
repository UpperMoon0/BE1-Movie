package util;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.InputMismatchException;
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
        // Print movies list and get movies data
        List<String> movieData = printMovieList();

        // Get user input
        System.out.println("Your choice:");

        do {
            try {
                UI.sc = new Scanner(System.in);
                int movieNumber = UI.sc.nextInt();
                if (movieNumber > 0 && movieNumber <= movieData.size()) {
                    String[] parts = movieData.get(movieNumber - 1).split(",");
                    return parts[0];
                } else {
                    System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
                }
            } catch (InputMismatchException e) {
                System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }

    // Print movies list and return movies data
    public static List<String> printMovieList() {
        // Get movies data
        List<String> movieData = FileManager.readEveryLine("data/movieData.txt");

        // Print movies list
        System.out.println("Movie list:");
        int i = 1;
        for (String data : movieData) {
            if (data != null) {
                String[] parts = data.split(",");
                System.out.printf("%d. %s, price: %s VND\n", i, parts[0], parts[1]);
                i++;
            }
        }

        return movieData;
    }

    // Get user input for choosing a showtime
    public static LocalDateTime inputShowtime() {
        String inputDateTime;
        LocalDateTime parsedDateTime;

        // Create formatter
        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy H:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy H:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HH:mm"))
                .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy H"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HH"))
                .appendOptional(DateTimeFormatter.ofPattern("dd/M/yyyy H"))
                .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HH"))
                .toFormatter();

        // Show all available showtimes
        System.out.println("Available showtimes:");
        for (LocalTime showtime : Movie.getShowtimesList()) {
            System.out.printf("%s\n", showtime);
        }

        while (true) {
            // Input date and time
            inputDateTime = inputString("Choose date and time (day/month/year time):");

            // Check if input is valid
            try {
                parsedDateTime = LocalDateTime.parse(inputDateTime, formatter);

                if (!parsedDateTime.isAfter(LocalDateTime.now())) {
                    System.out.println(UI.ANSI_RED + "Showtime must be in the future, please try again!" + UI.ANSI_RESET);
                } else if (!Movie.getShowtimesList().contains(parsedDateTime.toLocalTime())) {
                    System.out.println(UI.ANSI_RED + "Invalid showtime, please try again!" + UI.ANSI_RESET);
                } else {
                    return parsedDateTime;
                }
            } catch (DateTimeParseException e) {
                System.out.println(UI.ANSI_RED + "Invalid date or time format, please try again!" + UI.ANSI_RESET);
            }
        }
    }

    // Get user input for choosing a seat
    public static String inputSeat(String movie, LocalDateTime showtime) {
        String seat;

        // Print seat map and get available seat list
        List<String> availableSeatList = SeatMap.printSeatMap(movie, showtime);

        // Get user input
        do {
            seat = inputString("Choose a seat:");
            seat = seat.toUpperCase();
            if (!availableSeatList.contains(seat)) {
                System.out.println(UI.ANSI_RED + "Seat is not available, please try again!" + UI.ANSI_RESET);
            }
        } while (!availableSeatList.contains(seat));

        return seat;
    }

    // Get admin input for movie name
    public static String inputMovieName() {
        String inputStr;
        String regex = "^[^,]+$";

        do {
            inputStr = inputString("Enter the movie name:");
            if (!inputStr.matches(regex)) {
                System.out.println(UI.ANSI_RED + 
                    "Movie name must:\n" +
                    "- Not contain any comma character (,)\n" +
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
            } catch (NumberFormatException e) {
                System.out.println(UI.ANSI_RED + "Invalid price, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }
}
