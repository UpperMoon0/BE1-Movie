package ui;

public class AdminMenu extends Menu {
    // Data initialization
    protected static void dataInit() {
        options.clear();
        title = "Choose an option:";
        options.add("Add a movie");
        options.add("Remove movie");
        options.add("Change movie price");
        options.add("Logout");
        options.add("Exit");
    }

    // Choice effect
    protected static void choiceEffect(int choice) {
        switch(choice) {
            case 1:
                // Add a movie
                System.out.println("------------------------");

                break;
            case 2:
                // Remove movie
                System.out.println("------------------------");

                break;
            case 3:
                // Change movie price
                System.out.println("------------------------");

                break;
            case 4:
                // Logout
                System.out.println("------------------------");
                UserLoginMenu.menu();
                chooseSuccess = true;
                break;
            case 5:
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
        dataInit();
        do {
            choiceEffect(getChoice());
        } while (!chooseSuccess);
    }
}
