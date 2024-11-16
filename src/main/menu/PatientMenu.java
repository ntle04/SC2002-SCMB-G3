package main.menu;

import java.util.Scanner;

import main.controller.AvailabilitySlotController;

public class PatientMenu extends Menu{

    AvailabilitySlotController availabilitySlotController = new AvailabilitySlotController();

    public void printMenu(){
        System.out.println("=== Patient Menu ===");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointment Slots");
        System.out.println("4. Schedule an Appointment");
        System.out.println("5. Cancel an Appointment");
        System.out.println("6. View Scheduled Appointments");
        System.out.println("7. View Past Appointment Outcome Records");
        System.out.println("8. Logout");
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
                    availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");
                    break;
                case 4:
                    break;
                case 5:
                    break;
                default:
                    break;
            }

        }while(choice < 8);

    };
    
}
