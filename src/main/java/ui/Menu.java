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
        int i = 1;
        for (String option : options) {
            System.out.printf("%d: %s\n", i, option);
            i++;
        }

        // Get user input
        do {
            System.out.print("Your choice: ");
            try {
                UI.sc = new Scanner(System.in);
                choice = UI.sc.nextInt();

                if (choice > 0 && choice <= options.size()) {
                    return choice;
                } else {
                    System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
                }
            } catch (Exception e) {
                System.out.println(UI.ANSI_RED + "Invalid choice, please try again!" + UI.ANSI_RESET);
            }
        } while (true);
    }
}
