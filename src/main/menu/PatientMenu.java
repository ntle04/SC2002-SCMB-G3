/*package main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.controller.Authenticate;
import main.controller.ContactController;
import main.controller.PatientController;
//import main.model.Appointment;
import main.model.Contact;
import main.model.Patient;
import main.model.Person;
import main.util.Role;

import main.controller.AvailabilitySlotController;


public class PatientMenu extends Menu{
	
	private PatientController patientController;

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
		Person loggedInUser = Authenticate.getLoggedInUser();
		Contact contact = loggedInUser.getContact();
		ContactController contactController = new ContactController(contact);
		
		Patient patient = new Patient(loggedInUser.getId(), loggedInUser.getContact(), loggedInUser.getRole(),
				"P"+loggedInUser.getId(), "O", new ArrayList<Appointment>(), 
	    		new ArrayList<String>(), new ArrayList<String>() );
		
		patientController  = new PatientController(patient);
		
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		do{
		    printMenu();
		    System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
			    case 1:
			    	patientController.viewPatientRecord();
			        break;
			    case 2:
			    	loggedInUser.printContact();
			        break;
			    case 3:
			    	// TODO patientController view available appt slots	
			        break;
			    case 4:
			    	// TODO patientController schedule appt
			        break;
			    case 5:
			    	// TODO patientController cancel appt
			        break;
			    case 6:
			    	// TODO patientController view schedule appt
			        break;
			    case 7:
			    	// TODO patientController view Past Appointment Outcome Records
		            break;
		        default:
		            break;
		    }
				
		}while(choice < 8);
			
	};

}
