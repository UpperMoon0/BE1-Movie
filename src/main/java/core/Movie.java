package core;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private int price;
    private static List<LocalTime> showtimesList = new ArrayList<LocalTime>();

    static {
        // Showtimes
        showtimesList.add(LocalTime.of(10, 0));
        showtimesList.add(LocalTime.of(12, 0));
        showtimesList.add(LocalTime.of(14, 0));
        showtimesList.add(LocalTime.of(16, 0));
        showtimesList.add(LocalTime.of(18, 0));
        showtimesList.add(LocalTime.of(20, 0));
        showtimesList.add(LocalTime.of(22, 0));
    }

    // Constructor
    public Movie(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public static List<LocalTime> getShowtimesList() {
        return showtimesList;
    }

    public String toString() {
        return name + "," + price;
    }
}
