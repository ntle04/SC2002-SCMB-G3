package main;

import java.io.Console;
import main.controller.Authenticate;
import main.menu.Menu;


public class HMSApp
{
    public static void main(String [] args){
        boolean loginSucc = false;
        int attempts = 0;
        int maxAttempts = 8;

        Console console = System.console();

        //login screen
        System.out.println("Welcome to HMS!");

        while(!loginSucc && attempts < maxAttempts){
            //prompt for credentials
            System.out.println("Enter your ID: ");
            String id = console.readLine();
            System.out.println("Enter your password: ");
            char[] passwordArr = console.readPassword();
            String password = new String(passwordArr);
            //validate credentials
            loginSucc = Authenticate.validateLogin(id, password);

            if (!loginSucc) {
                System.out.println("Invalid ID or password. Please try again.");
            }
        }

        if(loginSucc){
            Menu menu = Menu.getMenuForRole(Authenticate.getLoggedInUser().getRole());
            if(menu!=null){
                menu.handleUserInput();
            }else{
                System.out.println("Unknown role. Please contact admin for assistance.");
                loginSucc = false;
            }
        }
        else{
            System.out.println("You have reached the max failed attempts. Access denied.");
        }

    }
}

