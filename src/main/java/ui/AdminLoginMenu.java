package ui;

import core.Admin;
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
                    chooseSuccess = true;
                }
                break;
            case 2:
                // Register
                System.out.println("------------------------");
                register();
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
            username = InputManager.inputString("Enter your username:"),
            password = InputManager.inputString("Enter your password:");

        Admin admin = new Admin(username, password);
        
        // Check if username and password match
        if (FileManager.checkUsernamePasswordMatch(admin.toString())) {
            System.out.println("Login successful!");
            UI.setCurrentAccount(username);
            return true;
        } else {
            System.out.println("Username or password is incorrect!");
        }

        return false;
    }

    // Register 
    public static void register() {
        Admin admin;
        String 
            username,
            password;

        // Get username
        do {
            username = InputManager.inputUsername("Enter your username:");

            // Check if username already exists
            if (FileManager.checkUsernameExist(username))
                System.out.println("Username already exists, please try again!");
        } while (FileManager.checkUsernameExist(username));

        // Get password
        password = InputManager.inputPassword("Enter your password:");

        // Write to file
        admin = new Admin(username, password);
        UI.setCurrentAccount(username);
        FileManager.writeToFile("data/adminData.txt", admin.toString() + "\n");
        System.out.println("Register successful!");    
    }
}
