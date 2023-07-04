package ui;

import java.util.Scanner;

import util.FileManager;

public class UI {
    // ANSI color codes
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";

    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // Set this to true to enable debug mode
    private static final boolean isDebug = true;

    // Current account name
    private static String currentAccount;
    
    // Main function
    public static void main(String[] args) {   
        FileManager.createMovieDataFileIfNotExist();    
        RoleMenu.menu();
        sc.close();
    }

    // Getters
    public static boolean getIsDebug() {
        return isDebug;
    }

    public static String getCurrentAccount() {
        return currentAccount;
    }

    // Setters
    public static void setCurrentAccount(String currentAccount) {
        UI.currentAccount = currentAccount;
    }
}
