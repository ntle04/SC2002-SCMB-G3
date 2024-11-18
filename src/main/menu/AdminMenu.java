package main.menu;

import java.util.Scanner;
import java.util.List;
import main.controller.AppointmentController;

// import controllers
import main.controller.Authenticate;
import main.controller.StaffController;
import main.controller.ContactController;
import main.controller.ReplenishmentRequestController;
import main.controller.InventoryController;

//import models
import main.model.Staff;
import main.model.Inventory;
import main.model.Person;
import main.model.Contact;


//import views
import main.view.StaffView;
import main.view.InventoryView;
import main.view.AdministratorView;


public class AdminMenu extends Menu{
    private AdministratorView adView = new AdministratorView();
    private StaffView staffView = new StaffView();
    private StaffController staffCon = new StaffController(staffView);
    private ReplenishmentRequestController repCon = new ReplenishmentRequestController();
    private Inventory inv = new Inventory();
    private InventoryView invView = new InventoryView();
    private InventoryController invCon = new InventoryController(inv, repCon);

    private AppointmentController apptCtrl = new AppointmentController();

    Scanner sc = new Scanner(System.in);

    
    public void printMenu(){
        System.out.println("========== Adminstrator Menu ==========");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Update Replenishment Requests");
        System.out.println("5. View personal information");
        System.out.println("6. Update personal information");
        System.out.println("7. Logout");
    }

    public void handleUserInput(){
        Person loggedInUser = Authenticate.getLoggedInUser();
		Contact contact = loggedInUser.getContact();
		ContactController contactController = new ContactController(contact);

        int choice = -1;

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine();
            
            switch (choice) {
                case 1:
                    handleStaffActions();
                    break;
                case 2:
                    System.out.println("=========== All Appointments ==========");
                    apptCtrl.printAllAppointments();
                    break;
                case 3:
                    handleInvActions();
                    break;
                case 4:
                    invCon.updateRepReq();
                    break;
                case 5:
                    contactController.printContact();
                    break;
                case 6:
                    contactController.updateContact();
                    break;
                case 7:
                    Authenticate.logout();
                    break;
                default:
                    break;
            }

        }while(choice < 8);

    };

    public void handleStaffActions(){
        int choice = -1;
        adView.printStaffActions();
        choice = sc.nextInt();
        sc.nextLine();

        switch(choice){
            case 1:
                staffCon.createStaff();
                break;
            case 2:
                staffCon.updateStaff();
                break;
            case 3:
                staffCon.removeStaff();
                break;
            case 4:
                List<Staff> staffList = staffCon.getStaffList();
                staffView.printAllStaff(staffList);
                break;
            case 5:
                staffFilter();
                break;
        }
    }

    public void staffFilter(){
        adView.printFilterMenu();

        int choice = sc.nextInt();
        sc.nextLine();

        switch(choice){
            case 1: 
                System.out.println("Enter staff role (Doctor, Pharmacist, Administrator):");
                String role = sc.nextLine();
                List<Staff> roleFiltered = staffCon.filterByRole(role.toUpperCase());
                staffView.printAllStaff(roleFiltered);
                break;
            case 2:
                System.out.println("Enter staff gender (M/F):");
                char gender = sc.next().charAt(0);
                List<Staff> genderFiltered = staffCon.filterByGender(gender);
                staffView.printAllStaff(genderFiltered);
                break;
            case 3:
                System.out.printf("Enter lower age boundary: ");
                int minAge = sc.nextInt();
                System.out.printf("Enter upper age boundary: ");
                int maxAge = sc.nextInt();
                List<Staff> ageFiltered = staffCon.filterByAge(minAge, maxAge);
                staffView.printAllStaff(ageFiltered);
                break;
        }
    }

    public void handleInvActions(){
        int choice = -1;
        adView.printInvActions();
        choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                invView.printInventory(inv);
                break;
            case 2:
                invCon.createMedicine();
                break;
            case 3:
                invCon.removeMedicine();
                break;
            case 4:
                invCon.updateMedicine();
                break;
        }
    }
}
