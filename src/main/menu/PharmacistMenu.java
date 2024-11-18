package main.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.controller.AppointmentOutcomeController;
import main.controller.Authenticate;
import main.controller.ContactController;
import main.controller.PrescriptionController;
import main.controller.ReplenishmentRequestController;
import main.model.Pharmacist;
import main.model.Prescription;
import main.util.PrescriptionStatus;
import main.view.InventoryView;
import main.model.Person;
import main.model.AppointmentOutcome;
import main.model.Contact;
import main.model.Inventory;

public class PharmacistMenu extends Menu{

    private Pharmacist model = new Pharmacist();
    private ReplenishmentRequestController requestController = new ReplenishmentRequestController();
    private Inventory inventory = new Inventory();
    private InventoryView invView = new InventoryView();
    private AppointmentOutcomeController apptOCtrl= new AppointmentOutcomeController();
    private PrescriptionController prescriptionController = new PrescriptionController();

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
                    apptOCtrl.printAllAppointmentOutcomes();
                    break;
                case 4:
                    updatePrescriptionStatus();
                    break;
                case 5:
                    invView.printInventory(inventory);
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

    private void updatePrescriptionStatus(){
        Scanner sc = new Scanner(System.in);
        apptOCtrl.printAllAdminOutcomes();
        System.out.println("Enter Appointment Outcome ID: ");
        String outcomeId = sc.next();
        List<Prescription> prescriptions = prescriptionController.findPrescriptionsByApptOutcomeId(outcomeId);
        for (Prescription prescription : prescriptions) {
            prescriptionController.updateStatus(prescription.getPrescriptionId(), PrescriptionStatus.DISPENSED);
        }
    }



    
}
