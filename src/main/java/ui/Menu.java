package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    protected static boolean chooseSuccess = false; 
    protected static String title;
    protected static ArrayList<String> options = new ArrayList<String>();

    protected static int getChoice() {
        int choice;
        
        // Print title and options
        System.out.println(title);
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ": " + options.get(i));
        }

        // Get user input
        do {
            System.out.print("Your choice: ");
            choice = 0;
            try {
                UI.sc = new Scanner(System.in);
                choice = UI.sc.nextInt();

                if (choice > 0 && choice < options.size()) {
                    return choice;
                }
                else
                    System.out.println("Invalid choice, please try again!");
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        } while (true);
    }
}
