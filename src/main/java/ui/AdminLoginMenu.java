package ui;

import core.Admin;
import core.User;
import util.FileManager;
import util.InputManager;


public class AdminLoginMenu extends Menu{
    // Data initialization
    protected static void dataInit() {
        options.clear();
        title = "Hello admin! Please choose an option:";
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
                    AdminMenu.menu();
                    chooseSuccess = true;
                }
                break;
            case 2:
                // Register
                System.out.println("------------------------");
                register();
                AdminMenu.menu();
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
        if (FileManager.checkUsernamePasswordMatch(user.toString(), true)) {
            System.out.println(UI.ANSI_GREEN + "Login successful!" + UI.ANSI_RESET);
            UI.setCurrentAccount(username);
            return true;
        } else {
            System.out.println(UI.ANSI_RED + "Username or password is incorrect!" + UI.ANSI_RESET);
            System.out.println("------------------------");
        }

        return false;
    }

    // Register 
    private static void register() {
        Admin admin;
        String 
            username,
            password;

        // Get username
        do {
            username = InputManager.inputUsername();

            // Check if username already exists
            if (FileManager.checkUsernameExist(username, true))
                System.out.println(UI.ANSI_RED + "Username already exists, please try again!" + UI.ANSI_RESET);
        } while (FileManager.checkUsernameExist(username, true));

        // Get password
        password = InputManager.inputPassword();

        // Write to file
        admin = new Admin(username, password);
        UI.setCurrentAccount(username);
        FileManager.writeToFile("data/adminData.txt", admin.toString() + "\n");
        System.out.println(UI.ANSI_GREEN + "Register successful!" + UI.ANSI_RESET);    
        System.out.println("------------------------");
    }
}
