package main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.controller.Authenticate;
import main.controller.ContactController;
import main.controller.PatientController;
import main.model.Appointment;
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
		
		//Patient patient = new Patient(loggedInUser.getId(), loggedInUser.getContact(), loggedInUser.getRole(), 
		//		"O-", new ArrayList<Appointment>(), new ArrayList<String>(), new ArrayList<String>() );
		
		patientController  = new PatientController();
		
		Patient selectedPatient = null;
		// Patient patient = patientController.getPatientList().stream().filter(a -> a.getId() == loggedInUser.getId()).collect(Collectors.toList()).get(0);
		for(Patient patient : patientController.getPatientList()) 
		{ 
		   if(patient.getId().equals(loggedInUser.getId()) )
		   { 
			   selectedPatient = patient;
		   }
		   else
		   {
			   // TODO user set bloodtype etc
			   List<Appointment> patientAppointment = new ArrayList<Appointment>();
			   List<String> diagnosis = new ArrayList<String>();
			   List<String> treatment = new ArrayList<String>();
			   
			   selectedPatient = new Patient(loggedInUser.getId(), contact, Role.PATIENT, 
					   "O-", patientAppointment,  diagnosis, treatment);
					   //null, null, null, null);
			     
			   patientController.createPatient(selectedPatient);
		   }
		}
		
		
		int choice = -1;
		Scanner sc = new Scanner(System.in);
		
		do{
		    printMenu();
		    System.out.println("Enter your choice: ");
			choice = sc.nextInt();
			
			switch (choice) {
			    case 1:
			    	patientController.viewPatientRecord(selectedPatient);
			        break;
			    case 2:
			    	// TODO Update Personal Information
			    	loggedInUser.printContact();
			        break;
			    case 3:
			    	// TODO patientController view available appt slots	
			    	availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");
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
