package ui;

import core.User;
import java.util.Scanner;

import util.FileManager;
import util.InputManager;


public class UserLoginMenu {
    // User login / register menu
    public static void userLoginMenu() {
        boolean chooseSuccess = false;
        do {
            int choice;
            boolean inputSuccess = false;
            
            System.out.println("Choose an action:");
            System.out.println("1: Login");
            System.out.println("2: Register");
            System.out.println("3: Back to role menu");
            System.out.println("4: Exit");

            do {
                System.out.print("Your choice: ");
                choice = 0;
                try {
                    UI.sc = new Scanner(System.in);
                    choice = UI.sc.nextInt();

                    if (choice > 0 && choice < 5) {
                        inputSuccess = true;
                    }
                    else
                        System.out.println("Invalid choice, please try again!");
                } catch (Exception e) {
                    System.out.println("Invalid choice, please try again!");
                }
            } while (!inputSuccess);

            switch(choice) {
                case 1:
                    login();
                    chooseSuccess = true;
                    break;
                case 2:
                    register();
                    chooseSuccess = true;
                    break;
                case 3:
                    RoleMenu.roleMenu();
                    chooseSuccess = true;
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    chooseSuccess = true;
                    break;
                default:
                    break;
            }
        } while (!chooseSuccess);
    }

    // Login
    private static void login() {
        // Get username and password
        String 
            username = InputManager.inputString("Enter your username:"),
            password = InputManager.inputString("Enter your password:");

        User user = new User(username, password);
        
        // Check if username and password match
        if (FileManager.checkUsernamePasswordMatch(user.toString())) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Username or password is incorrect!");
        }
    }

    // Register 
    public static void register() {
        User user;
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
        user = new User(username, password);
        FileManager.writeToFile("data/userData.txt", user.toString() + "\n");
        System.out.println("Register successful!");    
    }
}