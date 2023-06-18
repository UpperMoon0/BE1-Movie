
package core;

import java.time.LocalDateTime;


public class Ticket {
    private String owner;
    private String movie;  
    private LocalDateTime showTime;
    private String seat;
    
    public Ticket(String owner, String movie, LocalDateTime showTime, String seat) {
        this.owner = owner;
        this.movie = movie;
        this.showTime = showTime;
        this.seat = seat;
    }
    public String getOwner () {
        return owner;
    }
    public void setOwner (String owner) {
        this.owner = owner;
    }
    public String getMovie () {
        return movie;
    }
    public void setMovie (String movie) {
        this.movie = movie;
    }
    public LocalDateTime getShowTime () {
        return showTime;
    }
    public void setShowTime (LocalDateTime showTime) {
        this.showTime = showTime;
    }
    public String getSeat () {
        return seat;
    }
    public void setSeat (String seat) {
        this.seat = seat;
    }
}