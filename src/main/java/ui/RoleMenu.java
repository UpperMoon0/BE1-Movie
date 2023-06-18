package ui;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RoleMenu {
    public static void roleMenu() {
        boolean chooseSuccess = false;
        do {
            int choice;
            boolean inputSuccess = false;
            
            System.out.println("Choose your role:");
            System.out.println("1: User");
            System.out.println("2: Admin");
            System.out.println("3: Exit");

            do {
                System.out.print("Your choice: ");
                choice = 0;
                try {
                    Scanner sc = new Scanner(System.in);
                    choice = sc.nextInt();

                    if (choice > 0 && choice < 4) {
                        inputSuccess = true;
                        sc.close();
                    }
                    else
                        System.out.println("Invalid choice, please try again!");
                } catch (InputMismatchException e) {
                    System.out.println("Invalid choice, please try again!");
                }
            } while (!inputSuccess);

            switch(choice) {
                case 1:
                    chooseSuccess = true;
                    break;
                case 2:
                    chooseSuccess = true;
                    break;
                case 3:
                    System.out.println("Goodbye!");
                    chooseSuccess = true;
                    break;
                default:
                    break;
            }
        } while (!chooseSuccess);
    }
}
