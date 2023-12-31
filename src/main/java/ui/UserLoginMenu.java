package ui;

import core.User;

import util.FileManager;
import util.InputManager;


public class UserLoginMenu extends Menu {
    // Data initialization
    protected static void dataInit() {
        options.clear();
        title = "Hello user! Please choose an option:";
        options.add("Login");
        options.add("Register");
        options.add("Back to role menu");
        options.add("Exit");
    }

    // Choice effect
    protected static void choiceEffect(int choice) {
        switch(choice) {
            case 1:
                // Login
                if (login()) {
                    System.out.println("------------------------");
                    UserMenu.menu();
                    chooseSuccess = true;
                }
                break;
            case 2:
                // Register
                System.out.println("------------------------");
                register();
                UserMenu.menu();
                chooseSuccess = true;
                break;
            case 3:
                // Back to role menu
                System.out.println("------------------------");
                RoleMenu.menu();
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
        dataInit();
        do {
            choiceEffect(getChoice());
        } while (!chooseSuccess);
    }

    // Login
    private static boolean login() {
        // Get username and password
        String 
            username = InputManager.inputUsername(),
            password = InputManager.inputPassword();

        User user = new User(username, password);
        
        // Check if username and password match
        if (FileManager.checkUsernamePasswordMatch(user.toString(), false)) {
            System.out.println(UI.ANSI_GREEN + "Login successful!" + UI.ANSI_RESET);
            UI.setCurrentAccount(username);
            return true;
        } else {
            System.out.println(UI.ANSI_RED + "Username or password is incorrect, please try again!" + UI.ANSI_RESET);
            System.out.println("------------------------");
        }

        return false;
    }

    // Register 
    private static void register() {
        User user;
        String 
            username,
            password;

        // Get username
        do {
            username = InputManager.inputUsername();

            // Check if username already exists
            if (FileManager.checkUsernameExist(username, false))
                System.out.println(UI.ANSI_RED + "Username already exists, please try again!" + UI.ANSI_RESET);
        } while (FileManager.checkUsernameExist(username, false));

        // Get password
        password = InputManager.inputPassword();

        // Write to file
        user = new User(username, password);
        UI.setCurrentAccount(username);
        FileManager.writeToFile("data/userData.txt", user.toString() + "\n");
        System.out.println(UI.ANSI_GREEN + "Register successful!" + UI.ANSI_RESET);  
        System.out.println("------------------------");
    }
}