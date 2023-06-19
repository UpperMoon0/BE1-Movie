package ui;

import java.io.File;
import util.FileManager;

public class UI {
    private static final boolean isDebug = true;
    public static void main(String[] args) {       
        RoleMenu.roleMenu();
    }

    public static boolean getIsDebug() {
        return isDebug;
    }
}
