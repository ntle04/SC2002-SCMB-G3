package main.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import main.controller.DoctorController;
import main.controller.PatientController;
import main.controller.Authenticate;
import main.controller.AvailabilitySlotController;
import main.controller.ContactController;
import main.util.ApptStatus;
import main.util.TimeSlot;
import main.view.DoctorView;
import main.model.AppointmentSlot;
import main.model.AvailabilitySlot;
import main.model.Contact;
import main.model.Doctor;
import main.model.Patient;
import main.model.Person;


public class DoctorMenu extends Menu{


    AvailabilitySlotController availabilitySlotController = new AvailabilitySlotController();
    DoctorController doctorController = new DoctorController(null, null);


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
       
        Doctor doctor = new Doctor(loggedInUser.getId(), loggedInUser.getContact(), loggedInUser.getRole(), new ArrayList<Patient>(), new ArrayList<AvailabilitySlot>(), new ArrayList<AppointmentSlot>() );


        DoctorView docview = new DoctorView();
       
        DoctorController doctorController  = new DoctorController(doctor, docview);
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
            
                System.out.println("List of Patients:");
                if (patientList.isEmpty()) {
                    System.out.println("No patients available.");
                } else {
                    for (Patient patient : patientList) {
                        System.out.println("Patient ID: " + patient.getId() + ", Name: " + patient.getContact().getName());
                    }
                }
                System.out.println("Enter Patient ID to view record:");
                String patientId = sca.nextLine();
                doctorController.viewSpecificPatientRecord(patientId);
                    break;
               
                case 2:
                System.out.println("List of Patients:");
                if (patientList.isEmpty()) {
                    System.out.println("No patients available.");
                } else {
                    for (Patient patient : patientList) {
                        System.out.println("Patient ID: " + patient.getId() + ", Name: " + patient.getContact().getName());
                    }
                }
                System.out.println("Enter Patient ID to update record:");
                String updatepatientId = sca.nextLine();
                doctorController.updateSpecificPatientRecord(updatepatientId);
                    break;


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
                    System.out.println("=== Pending Appointment Requests ===");

                    List<AppointmentSlot> pendingAppointments = new ArrayList<>();
                    for (AppointmentSlot slot : doctor.getAppointmentSlots()) {
                        if (slot.getStatus() == ApptStatus.PENDING) {
                            pendingAppointments.add(slot);
                        }
                    }

                    if (pendingAppointments.isEmpty()) {
                        System.out.println("No pending appointment requests.");
                    } else {
                        for (int i = 0; i < pendingAppointments.size(); i++) {
                            AppointmentSlot appointmentSlot = pendingAppointments.get(i);
                            Patient patient = doctor.getPatientById(appointmentSlot.getPatientId());
                            System.out.println((i + 1) + ". Appointment with Patient ID: " + patient.getId() +
                                    ", Patient Name: " + patient.getContact().getName() +
                                    ", Time: " + appointmentSlot.getAvailabilitySlot().getTimeSlot() +
                                    ", Status: " + appointmentSlot.getStatus());
                        }
                    }

                    System.out.println("Enter the number of the appointment to manage (or 0 to cancel):");
                    int selectedAppointment = sc.nextInt();
                    if (selectedAppointment > 0 && selectedAppointment <= pendingAppointments.size()) {
                        AppointmentSlot selectedSlot = pendingAppointments.get(selectedAppointment - 1);
                        System.out.println("Do you want to Accept or Decline this appointment? (A/D)");
                        String action = sc.next().toUpperCase();

                        if ("A".equals(action)) {
                            // Accept the appointment
                            doctorController.acceptAppointment(selectedSlot);
                        } else if ("D".equals(action)) {
                            // Decline the appointment
                            doctorController.rejectAppointment(selectedSlot);
                        } else {
                            System.out.println("Invalid choice. Please enter 'A' to accept or 'D' to decline.");
                        }
                    } else if (selectedAppointment == 0) {
                        System.out.println("No appointment selected.");
                    } else {
                        System.out.println("Invalid selection.");
                    }
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
   
}
