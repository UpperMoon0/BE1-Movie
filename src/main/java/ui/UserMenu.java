package ui;

import java.time.LocalTime;

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
                break;
            case 2:
                // View my tickets
                System.out.println("------------------------");
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
}