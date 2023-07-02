package ui;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import core.Ticket;
import core.User;
import util.FileManager;
import util.InputManager;

public class UserMenu extends Menu {
    // Data initialization
    protected static void dataInit() {
        options.clear();
        title = "Choose an option:";
        options.add("Buy a ticket");
        options.add("View my tickets");
        options.add("Logout");
        options.add("Exit");
    }

    // Choice effect
    protected static void choiceEffect(int choice) {
        switch(choice) {
            case 1:
                // Buy a ticket
                System.out.println("------------------------");
                buyTicket();
                chooseSuccess = true;
                break;
            case 2:
                // View my tickets
                System.out.println("------------------------");
                viewTicket();
                break;
            case 3:
                // Logout
                System.out.println("------------------------");
                UserLoginMenu.menu();
                chooseSuccess = true;
                break;
            case 4:
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

    // Buy a ticket
    private static void buyTicket() {
        String movie;
        LocalDateTime showtime;
        String seat;

        // Get user input
        movie = InputManager.inputMovie();
        showtime = InputManager.inputShowtime();
        seat = InputManager.inputSeat();

        Ticket ticket = new Ticket(UI.getCurrentAccount() ,movie, showtime, seat);
        FileManager.writeToFile("data/ticketData", ticket.toString() + "\n");
        System.out.println("Ticket bought successfully!");
    }
    // Parse ticket
    public static Ticket parseTicket(String ticketInformation) {
        // Check ticket
        String[] parts = ticketInformation.split(",");
        String username = parts[0];
        String movie = parts[1];
        LocalDateTime showtime = LocalDateTime.parse(parts[2]);
        String seat = parts[3];
        Ticket ticket = new Ticket(username, movie, showtime, seat);
        return ticket;
    }
    // View ticket
    private static void viewTicket() {
        // Read ticketData file
        List<String> ticketData = FileManager.readEveryLines("data/ticketData");
        // Check information of ticket's user
        for (String ticketInformation : ticketData) {
            Ticket ticket = parseTicket(ticketInformation);
            // If the username of the current user matches the username of the ticket being processed, then print the ticket
            if (ticket.getOwner().equals(UI.getCurrentAccount())) {
                System.out.println("Owner: " + ticket.getOwner());
                System.out.println("Moive: " + ticket.getMovie());
                System.out.println("Showtime: " + ticket.getShowtime());
                System.out.println("Seat: " + ticket.getSeat());
                System.out.println("------------------------");
            } else {
                System.out.println("You didn't buy ticket.");
            }
        }
    }
}