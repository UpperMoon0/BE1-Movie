package util;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SeatMap {
    static List<String> ticketInfo;

    // Print seat map
    public static List<String> printSeatMap(String movie, LocalDateTime showtime) {
        // Read ticket info
        ticketInfo = FileManager.readEveryLine("data/ticketData.txt");
        // Filter ticket info
        filterTicketInfo(movie, showtime);
        // Print seat map
        System.out.println("Seat map:");
        System.out.println("  1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < 9; i++) {
            System.out.print((char)('A' + i) + " ");
            for (int j = 0; j < 9; j++) {
                if (checkSeat((char)('A' + i) + "" + (j + 1)))
                    System.out.print("O ");
                else
                    System.out.print("X ");
            }
            System.out.println();
        }
        return getAvailableSeats(movie);
    }

    // Filter ticket info
    public static void filterTicketInfo(String movie, LocalDateTime showtime) {
        List<String> filteredTicketInfo = new ArrayList<String>();
        for (String ticket : ticketInfo) {
            String[] parts = ticket.split(",");
            if (parts[1].equals(movie) && parts[2].equals(showtime.toString())) {
                filteredTicketInfo.add(ticket);
            }
        }
        ticketInfo = filteredTicketInfo;
    }

    // Check if a seat is available
    public static boolean checkSeat(String seat) {
        for (String ticket : ticketInfo) {
            String[] parts = ticket.split(",");
            if (parts[3].equals(seat)) {
                return false;
            }
        }
        return true;
    }

    // Get available seats
    public static List<String> getAvailableSeats(String movie) {
        List<String> availableSeatsList = new ArrayList<String>();
        // Get available seats
        for (char row = 'A'; row <= 'I'; row++) {
            for (int col = 1; col <= 9; col++) {
                String seat = row + "" + col;
                if (checkSeat(seat)) {
                    availableSeatsList.add(seat);
                }
            }
        }
        return availableSeatsList;
    }
}
