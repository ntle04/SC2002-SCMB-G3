package main.menu;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import main.util.Role;
import main.util.RequestStatus;
import main.csvUitls.*;
import main.controller.AppointmentController;
// import controllers
import main.controller.Authenticate;
import main.controller.StaffController;
import main.controller.ContactController;
import main.controller.ReplenishmentRequestController;
import main.controller.AppointmentOutcomeController;

//import models
import main.model.Staff;
import main.model.Administrator;
import main.model.Appointment;
import main.model.Inventory;
import main.model.Person;
import main.model.Contact;
import main.model.ReplenishmentRequest;
import main.model.AppointmentOutcome;

//import views
import main.view.StaffView;
import main.view.AppointmentOutcomeView;
import main.view.InventoryView;
import main.view.AppointmentOutcomeView;
import main.view.ReplenishmentRequestView;


public class AdminMenu extends Menu{
    private StaffView staffView = new StaffView();
    private StaffController staffCon = new StaffController(staffView);
    //private Administrator admin = new Administrator();
    private Inventory inv = new Inventory();
    private InventoryView invView = new InventoryView();
    private ReplenishmentRequestController repCon = new ReplenishmentRequestController();

    private AppointmentController apptCtrl = new AppointmentController();
    private AppointmentOutcomeController apptOut = new AppointmentOutcomeController();

    private static final String file_path = Config.STAFF_LIST_FILE_PATH;
    Scanner sc = new Scanner(System.in);

    
    public void printMenu(){
        System.out.println("=== Adminstrator Menu ===");
        System.out.println("1. View and Manage Hospital Staff");
        System.out.println("2. View Appointments details");
        System.out.println("3. View and Manage Medication Inventory");
        System.out.println("4. Approve Replenishment Requests");
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
                    System.out.println("============= Scheduled Appointments =============");
                    List<Appointment> appointments = apptCtrl.getAllAppointments();
                    apptCtrl.printScheduledAppointments(appointments);
                    System.out.println("============= Completed Appointments =============");
                    apptOut.printAllAdminOutcomes();

                    break;
                case 3:
                    handleInvActions();
                    break;
                case 4:
                    //print the request list
                    List<ReplenishmentRequest> reqList = repCon.getAllRequests();
                    repCon.printAllReq(reqList);
                    System.out.printf("Enter request ID to approve: ");
                    String reqId = sc.nextLine();
                    System.out.printf("Enter updated status (Approved, Pending, Denied): ");
                    String status = sc.nextLine();
                    RequestStatus statEnum = RequestStatus.valueOf(status.toUpperCase());
                    repCon.updateRequestStatus(reqId, statEnum);
                    break;
                case 5:
                    contactController.printContact();
                    break;
                case 6:
                    contactController.updateContact();
                case 7:
                    Authenticate.logout();
                    return;
                default:
                    break;
            }

        }while(choice < 8);

    };
    
    public void printStaffActions(){
        System.out.println("Select your choice:");
        System.out.println("1. Add staff");
        System.out.println("2. Update staff");
        System.out.println("3. Remove staff");
        System.out.println("4. View all hospital staff");
        System.out.println("5. View filtered staff list");
    }

    public void handleStaffActions(){
        int choice = -1;
        printStaffActions();
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
        System.out.println("Filter by:");
        System.out.println("1. Role");
        System.out.println("2. Gender");
        System.out.println("3. Age");

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

    public void printInvActions(){
        System.out.println("Select your choice:");
        System.out.println("1. View inventory");
        System.out.println("2. Add stock");
        System.out.println("3. Remove stock");
        System.out.println("4. Update stock");
    }

    public void handleInvActions(){
        int choice = -1;
        printInvActions();
        choice = sc.nextInt();
        sc.nextLine();
        switch(choice){
            case 1:
                invView.printInventory(inv);
                break;
            case 2:
                inv.createMedicine();
                break;
            case 3:
                inv.removeMedicine();
                break;
            case 4:
                inv.updateMedicine();
                break;
        }
    }
}
