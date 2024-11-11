package main.menu;

import java.util.Scanner;

public class AdminMenu extends Menu{
    public void printMenu(){
        System.out.println("=== Adminstrator Menu ===");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
        System.out.println("5. Logout");
    }

    public void handleUserInput(){
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }

        }while(choice < 5);

    };
    
}
