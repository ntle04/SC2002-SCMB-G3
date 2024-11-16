/*package main.menu;

import java.util.Scanner;

import main.controller.Authenticate;
import main.controller.ContactController;
import main.controller.ReplenishmentRequestController;
import main.model.Pharmacist;
import main.model.Person;
import main.model.Contact;

public class PharmacistMenu extends Menu{

    private Pharmacist model = new Pharmacist();
    private ReplenishmentRequestController requestController = new ReplenishmentRequestController();

    public void printMenu(){
        System.out.println("=== Pharmacist Menu ===");
        System.out.println("1. View Contact Information"); 
        System.out.println("2. Update Contact Information"); 
        System.out.println("3. View Appointment Outcome Record"); 
        System.out.println("4. Update Prescription Status"); 
        System.out.println("5. View Medication Inventory ");
        System.out.println("6. Submit Replenishment Request");
        System.out.println("7. View Submitted Replenishment Request(s)");
        System.out.println("8. Logout");
    }

    public void handleUserInput(){
        Person loggedInUser = Authenticate.getLoggedInUser();
        Contact contact = loggedInUser.getContact();
        ContactController contactController = new ContactController(contact);

        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                    contactController.printContact();
                    break;
                case 2:
                    contactController.updateContact();
                    break;
                case 3:
                    break;
                case 4:
                    model.updatePrescriptionStatus();
                    break;
                case 5:
                    break;
                case 6:
                    model.submitReplenishmentRequest();
                    break;
                case 7:
                    requestController.printAllReq(requestController.getAllRequests());
                    break;
                default:
                    Authenticate.logout();
                    break;
            }

        }while(choice < 8);

    };

    
    
} */
