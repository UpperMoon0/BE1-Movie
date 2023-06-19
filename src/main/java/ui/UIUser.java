package ui;


import core.User;
import java.util.ArrayList;
import util.FileManager;


public class UIUser extends ArrayList<User> {
    
    
    
    

    public User login() {
        
        String username = FileManager.inputString("Enter your username");
        String password = FileManager.inputString("Enter your password");
        do {
            for (User user : this) {
                if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                    return user; 
                }
            }
            return null;
        }while (true);
    }
    public void register() {
        String username;
        String password;
        do {
            username = FileManager.inputString("Enter your username");
            if (username.length() > 12) {
                System.out.println("Username exceeds the maximum limit of 12 characters. Please try again.");
            } else {
                boolean userNameExist = false;
                for (User user : this) {
                    if (username.equals(user.getUsername())) {
                        System.out.println("Username already exist!!!");
                        userNameExist = true;
                        break;
                    }
                }
                if (!userNameExist) {
                    break;
                }
            }
        } while (true);
        
    do {
        password = FileManager.inputString("Enter your password");
        if (password.length() > 12) {
            System.out.println("Password exceeds the maximum limit of 12 characters. Please try again.");
        } else if (!password.matches(".*[A-Z].*") && !password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one word UPPERCASE and one number");   
        } else {
            break;
        }
        
        
    } while (true);   
   
    User newUser = new User(username, password);
    this.add(newUser);   
    String userData = username + "," + password;
    FileManager.writeToFile("src/data/user.txt", userData);
    System.out.println("Register successful!!!");    
       
        
        
    }
}