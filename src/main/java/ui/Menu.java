package ui;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    protected static boolean chooseSuccess = false; 
    protected static String title;
    protected static ArrayList<String> options = new ArrayList<String>();

    protected static int getChoice() {
        int choice;
        UI.sc = new Scanner(System.in);

        // Print title and options
        System.out.println(title);
        int i = 1;
        for (String option : options) {
            System.out.printf("%d: %s\n", i, option);
            i++;
        }

        // Get user input
        do {
            System.out.print("Your choice: ");
            try {
                choice = UI.sc.nextInt();

                if (choice > 0 && choice <= options.size()) {
                    return choice;
                } else {
                    System.out.println("Invalid choice, please try again!");
                }
            } catch (Exception e) {
                System.out.println("Invalid choice, please try again!");
            }
        } while (true);
    }
}
