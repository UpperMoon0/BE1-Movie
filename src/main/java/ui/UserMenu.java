package ui;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import core.Ticket;
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
        seat = InputManager.inputSeat(movie, showtime);

        Ticket ticket = new Ticket(UI.getCurrentAccount() ,movie, showtime, seat);
        FileManager.writeToFile("data/ticketData.txt", ticket.toString() + "\n");
        System.out.println("Ticket bought successfully!");
        System.out.println("------------------------");
    }

    // View ticket
    private static void viewTicket() {
        // Read ticketData.txt file
        List<String> ticketData = FileManager.readEveryLine("data/ticketData.txt");
        List<Ticket> myTickets = new ArrayList<Ticket>();

        System.out.println("Your tickets:");

        // Check if user has ticket and print them
        for (String ticketInformation : ticketData) {
            Ticket ticket = Ticket.parseTicket(ticketInformation);
            if (ticket.getOwner().equals(UI.getCurrentAccount())) {
                myTickets.add(ticket);
                System.out.println("------------------------");
                System.out.println("Owner: " + ticket.getOwner());
                System.out.println("Movie: " + ticket.getMovie());
                String minute = ticket.getShowtime().getMinute() < 10 ? "0" + ticket.getShowtime().getMinute() : ticket.getShowtime().getMinute() + ""; 
                System.out.println("Showtime: " + ticket.getShowtime().getDayOfMonth() + "/" + ticket.getShowtime().getMonthValue() + "/" + ticket.getShowtime().getYear() + " " + ticket.getShowtime().getHour() + ":" + minute);
                System.out.println("Seat: " + ticket.getSeat());
            }
        }

        // Check if user has no ticket
        if (myTickets.size() == 0) {
            System.out.println("------------------------");
            System.out.println("You have no ticket!");
        }

        System.out.println("------------------------");
    }
}