package main.menu;

import java.util.Scanner;

public class PharmacistMenu extends Menu{

    public void printMenu(){
        System.out.println("=== Pharmacist Menu ===");
        System.out.println("1. View Appointment Outcome Record");
        System.out.println("2. Update Prescription Status");
        System.out.println("3. View Medication Inventory ");
        System.out.println("4. Submit Replenishment Request");
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
