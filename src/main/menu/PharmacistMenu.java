package main.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import main.controller.Authenticate;
import main.controller.ContactController;
import main.controller.PrescriptionController;
import main.controller.ReplenishmentRequestController;
import main.model.Pharmacist;
import main.model.Prescription;
import main.view.InventoryView;
import main.model.Person;
import main.model.Contact;

public class PharmacistMenu extends Menu{

    private Pharmacist model = new Pharmacist();
    private ReplenishmentRequestController requestController = new ReplenishmentRequestController();
    private InventoryView invView= new InventoryView();

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
        PrescriptionController prescriptionController = new PrescriptionController(null, null);

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
                    //add prescription
                    promptForPrescriptions();
                    break;
                case 4:
                    // prescriptionController.updatePrescriptionStatus();
                    break;
                case 5:
                    invView.printInventory(null);
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

    public void promptForPrescriptions(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Add Prescriptions to Appointment Outcome");

        System.out.print("How many prescriptions do you want to add? ");
        int numPrescriptions = scanner.nextInt();
        scanner.nextLine(); // consume newline

        ArrayList<Prescription> prescriptions = new ArrayList<>();

        for (int i = 0; i < numPrescriptions; i++) {
            System.out.println("Enter details for prescription " + (i + 1) + ":");
            System.out.print("Medicine ID: ");
            String medId = scanner.nextLine();

            System.out.print("Dosage: ");
            String dosage = scanner.nextLine();

            System.out.print("Quantity: ");
            String quantity = scanner.nextLine();
            scanner.nextLine();

            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dateAsString = currentDate.format(formatter);


            prescriptions.add(new Prescription(medId, dosage, quantity, dateAsString));
        }
    }

    
    
}
