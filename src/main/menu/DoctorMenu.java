package main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import main.controller.DoctorController;
import main.controller.PatientController;
import main.controller.Authenticate;
import main.controller.AvailabilitySlotController;
import main.controller.ContactController;
import main.util.TimeSlot;
// import main.view.DoctorView;
import main.model.AvailabilitySlot;
import main.model.Contact;
// import main.model.Doctor;
import main.model.Person;

public class DoctorMenu extends Menu{

    AvailabilitySlotController availabilitySlotController = new AvailabilitySlotController();
    // DoctorController doctorController = new DoctorController(null, null);

    public void printMenu(){
        System.out.println("=== Doctor Menu ===");
        System.out.println("1. View Patient Medical Records");
        System.out.println("2. Update Patient Medical Records");
        System.out.println("3. View Personal Schedule");
        System.out.println("4. Set Availability for Appointments");
        System.out.println("5. Accept or Decline Appointment Requests");
        System.out.println("6. View Upcoming Appointments");
        System.out.println("7. Record Appointment Outcome");
        System.out.println("8. Logout");
    }

    public void handleUserInput(){
        // Person loggedInUser = Authenticate.getLoggedInUser();
		// Contact contact = loggedInUser.getContact();
		// ContactController contactController = new ContactController(contact);
		
		// Doctor doctor = new Doctor(loggedInUser.getId(), loggedInUser.getContact(), loggedInUser.getRole(), new ArrayList<Patient>(), new ArrayList<AvailabilitySlot>(), new ArrayList<Appointment>() );

        // DoctorView docview = new DoctorView();
		
		// DoctorController doctorController  = new DoctorController(doctor, docview);
        int choice = -1;
        Scanner sc = new Scanner(System.in);

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            
            switch (choice) {
                case 1:
                // Scanner sca = new Scanner(System.in);
                // List<Patient> patientList = doctor.getPatients();
                // System.out.println("List of Patients:");
                // if (patientList.isEmpty()) {
                //     System.out.println("No patients available.");
                // } else {
                //     for (Patient patient : patientList) {
                //         System.out.println("Patient ID: " + patient.getPatientId() + ", Name: " + patient.getContact().getName());
                //     }
                // }
                // System.out.println("Enter Patient ID to view record:");
                // String patientId = sca.nextLine();
                // doctorController.viewSpecificPatientRecord(patientId);
                //     break;
                
                case 2:
                // List<Patient> patientList = doctor.getPatients();
                // System.out.println("List of Patients:");
                // if (patientList.isEmpty()) {
                //     System.out.println("No patients available.");
                // } else {
                //     for (Patient patient : patientList) {
                //         System.out.println("Patient ID: " + patient.getPatientId() + ", Name: " + patient.getContact().getName());
                //     }
                // }
                // System.out.println("Enter Patient ID to update record:");
                // String updatepatientId = sca.nextLine();
                // doctorController.updateSpecificPatientRecord(updatepatientId);
                //     break;

                case 3:
                    availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");
                    break;

                case 4:
                    TimeSlot.printAllTimeSlots();

                    Scanner scanner = new Scanner(System.in);
                    System.out.print("Enter the numbers of the time slots you want to add (separate with spaces): ");
                    String input = scanner.nextLine();
                    List<TimeSlot> selectedSlots = TimeSlot.getSlotsByIndices(input);
                    availabilitySlotController.addAvailabilitySlots(Authenticate.getLoggedInUser().getId(), selectedSlots);
                    availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");

                    break;

                case 5://Accept or Decline requests
                    break;


                case 6://view upcoming appointments
                    break;

                case 7://record appt outcome
                    break;

                default:
                    break;
            }

        }while(choice < 8);

    };
    
}
