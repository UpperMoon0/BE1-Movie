package util;

import java.util.Scanner;

import ui.UI;

public class InputManager {
    public static String inputString (String message) {
        String inputStr;

        do {
            System.out.println(message);
            UI.sc = new Scanner(System.in);
            inputStr = UI.sc.nextLine();
            if (inputStr.isEmpty()) 
                System.out.println("String can't be empty, please try again!");
        } while (inputStr.isEmpty());

        return inputStr;
    }

    public static String inputUsername (String message) {
        String inputStr;

        do {
            inputStr = inputString(message);
            if (!inputStr.matches("[a-zA-Z0-9]{1,12}"))
                System.out.println("Username can only contain letters and numbers, and can't be longer than 12 characters, please try again!");
        } while (!inputStr.matches("[a-zA-Z0-9]{1,12}"));

        return inputStr;
    }

    public static String inputPassword (String message) {
        String inputStr;

        do {
            inputStr = inputString(message);
            if (!inputStr.matches("(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]{1,12}"))
                System.out.println("Password must contain at least 1 digit and 1 uppercase letter, without any special characters, and can't be longer than 12 characters, please try again!");
        } while (!inputStr.matches("(?=.*\\d)(?=.*[A-Z])[a-zA-Z0-9]{1,12}"));

        return inputStr;
    }
}
