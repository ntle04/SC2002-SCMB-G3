package main;

import java.util.Scanner;

import main.controller.Authenticate;
import main.menu.Menu;
import main.util.Role;


public class HMSApp
{
    public static void main(String [] args){
        boolean loginSucc = false;
        int attempts = 0;
        int maxAttempts = 8;
        Scanner sc = new Scanner(System.in);

        //login screen
        System.out.println("Welcome to HMS!");

        while(!loginSucc && attempts < maxAttempts){
            //prompt for credentials
            System.out.println("Enter your hospital ID: ");
            String id = sc.next();
            System.out.println("Enter your password: ");
            String password = sc.next();
            //validate credentials
            loginSucc = Authenticate.validateLogin(id, password);

            if (!loginSucc) {
                System.out.println("Invalid ID or password. Please try again.");
            }
        }

        if(loginSucc){
            System.out.println("Login successful.");
            Role role = Authenticate.getRole();
            Menu menu = Menu.getMenuForRole(role);
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

        // Person sarah = new Person("Sarah", 'F', "999");
        // sarah.printContact();
        // sarah.updateContact();
        // sarah.printContact();
    }
}

