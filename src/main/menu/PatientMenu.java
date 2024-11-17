package main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.controller.AppointmentController;
import main.controller.AppointmentSlotController;
import main.controller.Authenticate;
import main.controller.ContactController;
import main.controller.PatientController;
import main.model.Appointment;
import main.model.AvailabilitySlot;
import main.model.Contact;
import main.model.Patient;
import main.model.Person;
import main.util.Role;
import main.view.AvailabilitySlotView;
import main.controller.AvailabilitySlotController;


public class PatientMenu extends Menu{
	

	Person currentUser = Authenticate.getLoggedInUser();

	PatientController patientController;
    AvailabilitySlotController availSlotController = new AvailabilitySlotController();
    AvailabilitySlotView availView = new AvailabilitySlotView();
    //AppointmentSlotController apptSlotController = new AppointmentSlotController();
	// ContactController contactController = new ContactController(currentUser.getContact());
	AppointmentController apptController = new AppointmentController();


    public void printMenu(){
        System.out.println("=== Patient Menu ===");
        System.out.println("1. View Medical Record");
        System.out.println("2. Update Personal Information");
        System.out.println("3. View Available Appointment Slots");
        System.out.println("4. Schedule an Appointment");
		System.out.println("5. Reschedule an Appointment");
        System.out.println("6. Cancel an Appointment");
        System.out.println("7. View Scheduled Appointments");
        System.out.println("8. View Past Appointment Outcome Records");
        System.out.println("9. Logout");
    }

	public void handleUserInput(){
		Person loggedInUser = Authenticate.getLoggedInUser();
		Contact contact = loggedInUser.getContact();
		ContactController contactController = new ContactController(contact);
		patientController  = new PatientController();
		
		Patient selectedPatient = null;	
		List<Patient> patients = patientController.getPatientList();
		
		for(Patient patient : new ArrayList<>(patients))
		{ 
			if(patient.getId().equals(loggedInUser.getId()) )
			{ 
				selectedPatient = patient;
				break;
			}	
		}
		if (selectedPatient == null)
		{
			List<Appointment> patientAppointment = new ArrayList<Appointment>();
			List<String> diagnosis = new ArrayList<String>();
			List<String> treatment = new ArrayList<String>();
			   
			selectedPatient = new Patient(loggedInUser.getId(), contact, Role.PATIENT, 
			"O-", patientAppointment,  diagnosis, treatment);
			   //null, null, null, null);
				     
			patientController.createPatient(selectedPatient);
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
			    	contactController.updateContact();
			        break;
			    case 3:
			    	// TODO patientController view available appt slots	
					availSlotController.printAvailabilitySlotsByDoctor("D0001");
			        break;
			    case 4:
			    	// TODO patientController schedule appt
                    //AvailabilitySlot selectedSlot = selectSlot(availSlotController.getAvailabilitySlotsByDoctor("D0001"));
                    //apptSlotController.bookAppointment(Authenticate.getLoggedInUser().getId(), selectedSlot.getAvailabilitySlotId());
			        break;
			    case 5:
			    	// TODO patientController reschedule appt

			        break;
			    case 6:
			    	// TODO patientController cancel schedule appt
					Appointment latestAppointment = apptController.getConfirmedAppointmentByPatientId(Authenticate.getLoggedInUser().getId());

					
					apptController.cancelAppointment(latestAppointment);
			        break;
			    case 7:
					// TODO patientController view schedule appt
					List<Appointment> schedAppointments = apptController.getConfirmedAppointmentsByPatientId(Authenticate.getLoggedInUser().getId());
					apptController.printScheduledAppointments(schedAppointments);
					break;
				case 8: 
					// TODO patientController view Past Appointment Outcome Records
		            break;
				case 9: 
					// logout
					Authenticate.logout();
		            break;
		        default:
		            break;
		    }
				
		}while(choice < 8);
			
	}

	public AvailabilitySlot selectSlot(List<AvailabilitySlot> slots) {
        // Display available slots with indices
        availView.printAvailabilitySlots(slots);


        // Get the user's choice
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the slot you'd like to select: ");
        int choice = scanner.nextInt();


        // Validate the choice and return the corresponding slot
        int index = 1;
        for (AvailabilitySlot slot : slots) {
            if (slot.isAvailable()) {
                if (index == choice) {
                    return slot;
                }
                index++;
            }
        }
       
        System.out.println("Invalid selection. Please try again.");
        return selectSlot(slots); // Retry on invalid input
    }


}
