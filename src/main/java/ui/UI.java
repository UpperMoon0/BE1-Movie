package ui;

public class UI {
    private static boolean isDebug = true;
    public static void main(String[] args) {
        RoleMenu.roleMenu();
    }

    public static boolean getIsDebug() {
        return isDebug;
    }
}
