package main.menu;

import java.util.Scanner;
import java.util.List;

import main.controller.ReplenishmentRequestController;
import main.model.Pharmacist;
import main.model.ReplenishmentRequest;

public class PharmacistMenu extends Menu{

    private Pharmacist model = new Pharmacist();
    private ReplenishmentRequestController requestController = new ReplenishmentRequestController();

    public void printMenu(){
        System.out.println("=== Pharmacist Menu ===");
        System.out.println("1. View Appointment Outcome Record"); 
        System.out.println("2. Update Prescription Status"); 
        System.out.println("3. View Medication Inventory ");
        System.out.println("4. Submit Replenishment Request");
        System.out.println("5. View Submitted Replenishment Request(s)");
        System.out.println("6. Logout");
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
                    model.updatePrescriptionStatus();
                    break;
                case 3:

                    break;
                case 4:
                    model.submitReplenishmentRequest();
                    break;
                case 5:
                    requestController.printAllReq(requestController.getAllRequests());
                    break;
                default:
                    break;
            }

        }while(choice < 6);

    };

    
    
}
