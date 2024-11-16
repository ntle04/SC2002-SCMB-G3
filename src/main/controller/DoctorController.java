package main.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.model.AppointmentSlot;
import main.model.Doctor;
import main.view.DoctorView;
import main.model.Patient;
import main.util.ApptStatus;


public class DoctorController {
    private Doctor model;
    private DoctorView view;


    public DoctorController(Doctor model, DoctorView view) {
        this.model = model;
        this.view = view;
    }
   
    public void viewSpecificPatientRecord(String patientId){
        List<Patient> patientlist = model.getPatients();


        for (Patient patient : patientlist){
            if (patient.getId().equals(patientId)) {
                // If the ID matches, display the patient record
                System.out.println("Patient ID: " + patient.getId());
                System.out.println("Patient Name: " + patient.getContact().getName());
                System.out.println("Patient Blood Type: " + patient.getPatientBloodType());
                System.out.println("Patient Diagnoses: " + patient.getDiagnosis());
                System.out.println("Patient Treatments: " + patient.getTreatment(patientId));
                return; // Exit the method after displaying the record
            }
        }
    }


    public void updateSpecificPatientRecord(String patientId) {
    List<Patient> patientlist = model.getPatients();
    for (Patient patient : patientlist) {
        if (patient.getId().equals(patientId)) {
            // Display current patient details
            System.out.println("Patient found. Current details:");
            System.out.println("Name: " + patient.getContact().getName());
            System.out.println("DOB: " + patient.getContact().getDOB());
            System.out.println("Gender: " + patient.getContact().getGender());
            System.out.println("Contact Number: " + patient.getContact().getContactNumber());
            System.out.println("Email: " + patient.getContact().getEmail());
            System.out.println("Address: " + patient.getContact().getAddress());
           
            // Ask user which field to update
            Scanner sc = new Scanner(System.in);
            System.out.println("Which field would you like to update?");
            System.out.println("1. Name");
            System.out.println("2. DOB");
            System.out.println("3. Gender");
            System.out.println("4. Contact Number");
            System.out.println("5. Email");
            System.out.println("6. Address");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline


            switch (choice) {
                case 1:
                    System.out.print("Enter new Name: ");
                    patient.getContact().setName(sc.nextLine());
                    break;
                case 2:
                    System.out.print("Enter new DOB (dd/mm/yyyy): ");
                    patient.getContact().setDOB(sc.nextLine());
                    break;
                case 3:
                    System.out.print("Enter new Gender (M/F): ");
                    patient.getContact().setGender(sc.nextLine().charAt(0));
                    break;
                case 4:
                    System.out.print("Enter new Contact Number: ");
                    patient.getContact().setContactNumber(sc.nextLine());
                    break;
                case 5:
                    System.out.print("Enter new Email: ");
                    patient.getContact().setEmail(sc.nextLine());
                    break;
                case 6:
                    System.out.print("Enter new Address: ");
                    patient.getContact().setAddress(sc.nextLine());
                    break;
                default:
                    System.out.println("Invalid choice. No updates made.");
                    sc.close();
                    return;
            }
            System.out.println("Patient record updated successfully.");
            sc.close();
            return; // Exit the method after updating
        }
    }
    System.out.println("Patient ID not found.");
}


    public void viewSchedule(){
        view.displaySchedule(model);
    }

    public void acceptAppointment(AppointmentSlot appointmentSlot) {
        // Check if the appointment is pending before confirming it
        if (appointmentSlot.getStatus() == ApptStatus.PENDING) {
            appointmentSlot.confirmAppointment();  // Confirm the appointment
            System.out.println("Appointment with Patient ID " + appointmentSlot.getPatientId() + " has been accepted.");
        } else {
            System.out.println("Appointment cannot be accepted as it is not pending.");
        }
    }

    public void rejectAppointment(AppointmentSlot appointmentSlot) {
        // Check if the appointment is pending before canceling it
        if (appointmentSlot.getStatus() == ApptStatus.PENDING) {
            appointmentSlot.cancelAppointment();  // Cancel the appointment
            System.out.println("Appointment with Patient ID " + appointmentSlot.getPatientId() + " has been declined.");
        } else {
            System.out.println("Appointment cannot be declined as it is not pending.");
        }
    }

    public List<AppointmentSlot> getUpcomingAppointments() {
        List<AppointmentSlot> upcomingAppointments = new ArrayList<>();
        
        // Iterate through all the appointment slots and filter out the ones with CONFIRMED status
        for (AppointmentSlot slot : model.getAppointmentSlots()) {
            if (slot.getStatus() == ApptStatus.CONFIRMED) {
                upcomingAppointments.add(slot);
            }
        }
        return upcomingAppointments;
    }

}
