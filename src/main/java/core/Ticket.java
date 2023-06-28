
package core;

import java.time.LocalDateTime;


public class Ticket {
    private String owner;
    private String movie;  
    private LocalDateTime showtime;
    private String seat;
    
    // Constructor
    public Ticket(String owner, String movie, LocalDateTime showTime, String seat) {
        this.owner = owner;
        this.movie = movie;
        this.showtime = showTime;
        this.seat = seat;
    }

    // Getters and setters
    public String getOwner () {
        return owner;
    }

    public String getMovie () {
        return movie;
    }

    public LocalDateTime getShowtime () {
        return showtime;
    }

    public String getSeat () {
        return seat;
    }

    public String toString () {
        return owner + "," + movie + "," + showtime + "," + seat;
    }
}