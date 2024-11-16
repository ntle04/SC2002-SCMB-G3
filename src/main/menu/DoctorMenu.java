package main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// import main.controller.DoctorController;
import main.controller.PatientController;
import main.controller.AppointmentSlotController;
import main.controller.Authenticate;
import main.controller.AvailabilitySlotController;
import main.controller.ContactController;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.model.AppointmentSlot;
// import main.view.DoctorView;
import main.model.AvailabilitySlot;
import main.model.Contact;
import main.model.Doctor;
import main.model.Patient;
import main.model.Person;


public class DoctorMenu extends Menu{


    AvailabilitySlotController availabilitySlotController = new AvailabilitySlotController();
    // DoctorController doctorController = new DoctorController(null, null);
    AppointmentSlotController apptSlotController = new AppointmentSlotController();


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
        Scanner sca = new Scanner(System.in);

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            List<Patient> patientList = doctor.getPatients();
           
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
                    break;
               
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
                    break;


                case 3:
                    availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");
                    break;


                case 4:
                    TimeSlot.printAllTimeSlots();
                    System.out.print("Enter the numbers of the time slots you want to add (separate with spaces): ");
                    String input = sc.nextLine();
                    List<TimeSlot> selectedSlots = TimeSlot.getSlotsByIndices(input);
                    availabilitySlotController.addAvailabilitySlots(Authenticate.getLoggedInUser().getId(), selectedSlots);
                    availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");


                    break;


                case 5://Accept or Decline requests
                    AppointmentSlot selectedSlot = acceptAppointment();
                    apptSlotController.confirmAppointment(selectedSlot.getAppointmentSlotId());
                    break;

                case 6://view upcoming appointments
                    System.out.println("=== Upcoming Appointments ===");

                    // Retrieve upcoming appointments
                    List<AppointmentSlot> upcomingAppointments = doctorController.getUpcomingAppointments();
                    List<Patient> patients = doctor.getPatients();

                    // Use DoctorView to display the upcoming appointments
                    docview.displayUpcomingAppointments(upcomingAppointments, patients,doctor);
                    break;

                    


                case 7://record appt outcome
                    break;


                default:
                    break;
            }


        }while(choice < 8);


    };

    private AppointmentSlot acceptAppointment() {
        List<AppointmentSlot> slots = apptSlotController.filterSlotsByDoctorId(Authenticate.getLoggedInUser().getId());
        
        // Display available slots with indices
        apptSlotController.printPendingAppointmentSlots();

        // Get the user's choice
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the slot you'd like to accept: ");
        int choice = scanner.nextInt();

        // Validate the choice and return the corresponding slot
        int index = 1;
        for (AppointmentSlot slot : slots) {
            if (slot.getStatus() == ApptStatus.PENDING) {
                if (index == choice) {
                    return slot;
                }
                index++;
            }
        }
        
        System.out.println("Invalid selection. Please try again.");
        return acceptAppointment(); // Retry on invalid input
    }
   
}
