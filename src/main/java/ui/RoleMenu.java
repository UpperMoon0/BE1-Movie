package ui;

public class RoleMenu extends Menu {
    // Data initialization
    protected static void dataInit() {
        options.clear();
        title = "Choose a role:";
        options.add("User");
        options.add("Admin");
        options.add("Exit");
    }

    // Choice effect
    protected static void choiceEffect(int choice) {
        switch(choice) {
            case 1:
                UserLoginMenu.menu();
                chooseSuccess = true;
                break;
            case 2:
                AdminLoginMenu.menu();
                chooseSuccess = true;
                break;
            case 3:
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
