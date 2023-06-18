
package ui;

import util.FileManager;

public class UI {
    private static boolean isDebug = true;
    public static void main(String[] args) {
        FileManager.createFile("data/userData.txt");
    }

    public static boolean getIsDebug() {
        return isDebug;
    }
    
}
