package ui;

import java.util.Scanner;

import util.DataInit;

public class UI {
    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // Set this to true to enable debug mode
    private static final boolean isDebug = false;

    // Current account name
    private static String currentAccount;
    
    // Main function
    public static void main(String[] args) {       
        DataInit.dataInit();
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
