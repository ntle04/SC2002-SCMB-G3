package main.menu;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//import main.controller.DoctorController;
import main.controller.PatientController;
import main.controller.AppointmentController;
import main.controller.AppointmentOutcomeController;
import main.controller.AppointmentSlotController;
import main.controller.Authenticate;
import main.controller.AvailabilitySlotController;
import main.controller.ContactController;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.model.Appointment;
import main.model.AppointmentOutcome;
import main.model.AppointmentSlot;
//import main.view.DoctorView;
import main.model.AvailabilitySlot;
import main.model.Contact;
import main.model.Doctor;
import main.model.Patient;
import main.model.Person;
import main.model.Prescription;


public class DoctorMenu extends Menu{


    AvailabilitySlotController availabilitySlotController = new AvailabilitySlotController();
    // DoctorController doctorController = new DoctorController(null, null);
    AppointmentSlotController apptSlotController = new AppointmentSlotController();
    AppointmentController apptController = new AppointmentController();


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
        Person loggedInUser = Authenticate.getLoggedInUser();
        Contact contact = loggedInUser.getContact();
        ContactController contactController = new ContactController(contact);
        // AppointmentOutcomeController outcomeCtrl = new AppointmentOutcomeController();
       
         //Doctor doctor = new Doctor(loggedInUser.getId(), loggedInUser.getContact(), loggedInUser.getRole(), new ArrayList<Patient>(), new ArrayList<AvailabilitySlot>(), new ArrayList<Appointment>() );


        //DoctorView docview = new DoctorView();
       
        //DoctorController doctorController  = new DoctorController(doctor, docview);
        int choice = -1;
        Scanner sc = new Scanner(System.in);
        Scanner sca = new Scanner(System.in);

        do{
            printMenu();
            System.out.println("Enter your choice: ");
            choice = sc.nextInt();
            //List<Patient> patientList = doctor.getPatients();
           
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
                    if (sc.hasNextLine()) {
                        sc.nextLine(); 
                    }
                    String input = sc.nextLine();
                    List<TimeSlot> selectedSlots = TimeSlot.getSlotsByIndices(input);
                    availabilitySlotController.addAvailabilitySlots(Authenticate.getLoggedInUser().getId(), selectedSlots);
                    availabilitySlotController.printAvailabilitySlotsByDoctor("D0001");
                    break;


                case 5://Accept or Decline requests
                    //int selectedSlot = sc.nextInt();
                    //List<AppointmentSlot> slots = apptSlotController.filterSlotsByDoctorId(loggedInUser.getId());
                    //AppointmentSlot chosen = slots.get(selectedSlot - 1);
                    AppointmentSlot chosen = selectAppointmentSlot();
                    System.out.print("Enter A to accept appt and D to decline");
                    String reqchoice = sc.nextLine();
                    char character = reqchoice.charAt(0);
                    if(character == 'A'){
                        apptSlotController.confirmAppointment(chosen.getAppointmentSlotId());
                    }
                    else if(character == 'D'){
                        //apptSlotController.cancelAppointmentSlot(chosen.getAppointmentSlotId());
                        //availabilitySlotController.cancelAvailabilitySlot(chosen.getAvailabilitySlotId());
                    }
                    else{
                        break;
                    }
                    break;

                case 6://view upcoming appointments
                    System.out.println("=== Upcoming Appointments ===");

                    // Retrieve upcoming appointments
                    //List<AppointmentSlot> upcomingAppointments = doctorController.getUpcomingAppointments();
                    //List<Patient> patients = doctor.getPatients();

                    // Use DoctorView to display the upcoming appointments
                    //docview.displayUpcomingAppointments(upcomingAppointments, patients,doctor);
                    break;

                    


                case 7://record appt outcome
                    createAppointmentOutcome();
                    break;


                default:
                    break;
            }


        }while(choice < 8);


    };

     /*private AppointmentSlot selectAppointment() {
        List<AppointmentSlot> slots = apptSlotController.filterSlotsByDoctorId(Authenticate.getLoggedInUser().getId());
       
        // Display available slots with indices
        apptSlotController.printPendingAppointmentSlots();


        // Get the user's choice
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the index of the slot you'd like to accept: ");
        int choice = scanner.nextInt();


        // Validate the choice and return the corresponding slot
        int index = 0;
        for (AppointmentSlot slot : slots) {
            if (index == choice) {
                System.out.println("Appointment Slot ID: " + slot.getAppointmentSlotId());
                System.out.println("Availability Slot ID: " + slot.getAvailabilitySlotId());
                return slot;
            }
            index++;
        }
       
        System.out.println("Invalid selection. Please try again.");
        return selectAppointment(); // Retry on invalid input
    }*/


    private AppointmentSlot selectAppointmentSlot() {
        List<AppointmentSlot> slots = apptSlotController.filterSlotsByDoctorIdandStatus(Authenticate.getLoggedInUser().getId());
   
        // Display available slots with indices
        apptSlotController.printAppointmentSlots(slots);
   
        Scanner scanner = new Scanner(System.in);
        int choice = -1; // Default invalid choice
   
        while (true) {
            System.out.println("Enter the index of the slot you'd like to accept: ");
   
            // Check if input is a valid integer
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
   
                // Validate if choice is within range
                if (choice >= 0 && choice <= slots.size()) {
                    AppointmentSlot selectedSlot = slots.get(choice - 1);
                    System.out.println("Appointment Slot ID: " + selectedSlot.getAppointmentSlotId());
                    System.out.println("Availability Slot ID: " + selectedSlot.getAvailabilitySlotId());
                    return selectedSlot;
                } else {
                    System.out.println("Invalid selection. Please select a valid index.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.next(); // Consume the invalid input
            }
        }
    }

    private Appointment selectAppointment() {
        List<Appointment> appts = apptController.getConfirmedAppointmentsByDoctorId(Authenticate.getLoggedInUser().getId());
   
        // Display available slots with indices
        apptController.printScheduledAppointments(appts);
   
        Scanner scanner = new Scanner(System.in);
   
        System.out.println("Enter Appoinment ID: ");

        String apptId = scanner.next();
        Appointment selectedAppt = apptController.getAppointmentById(apptId);

        return selectedAppt;
            
    }


    public void createAppointmentOutcome(){
        Scanner scanner = new Scanner(System.in);
        Appointment appointment = selectAppointment();
        appointment.setStatus(ApptStatus.COMPLETED);
        AppointmentOutcomeController outcomeCtrl = new AppointmentOutcomeController();

        //prompt for outcome details
        System.out.print("Enter type of service: ");
        String serviceType = scanner.next();
        System.out.print("Enter consultation notes: ");
        String notes = scanner.next();
        List<Prescription> prescriptions = new ArrayList<>();

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String outcomeDate = currentDate.format(formatter);

        //create outcome
        String outcomeId = outcomeCtrl.addOutcome(appointment.getAppointmentId(), outcomeDate, appointment.getTimeSlot(), 
        serviceType, prescriptions, notes, appointment.getDoctorId(), appointment.getPatientId());

        //prompt for prescriptions
        prescriptions = addPrescriptions(outcomeId);

        //update appt outcome with prescriptions
        AppointmentOutcome apptOutcome = outcomeCtrl.getOutcomeById(outcomeId);
        apptOutcome.setPrescription(prescriptions);
        outcomeCtrl.updateOutcome(apptOutcome);

    }



    public List<Prescription> addPrescriptions(String outcomeId){
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


            prescriptions.add(new Prescription(medId, dosage, quantity, dateAsString, outcomeId));
        }

        return prescriptions;
    }
   
}
