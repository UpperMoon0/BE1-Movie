package ui;

import java.util.Scanner;

import util.DataInit;

public class UI {
    // Scanner
    public static Scanner sc = new Scanner(System.in);

    // Set this to true to enable debug mode
    private static final boolean isDebug = false;
    
    // Main function
    public static void main(String[] args) {       
        DataInit.dataInit();
        RoleMenu.roleMenu();
        sc.close();
    }

    // Getters
    public static boolean getIsDebug() {
        return isDebug;
    }
}
