package main.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import main.model.AvailabilitySlot;
import main.model.Doctor;
import main.model.Patient;
import main.util.TimeSlot;
import main.view.DoctorView;
import main.controller.*;


public class DoctorController {
    private Doctor model;
    private DoctorView view;


    AvailabilitySlotController availSlotController = new AvailabilitySlotController();

    public DoctorController(Doctor model, DoctorView view) {
        this.model = model;
        this.view = view;
    }
   
    // public void viewSpecificPatientRecord(String patientId){
    //     List<Patient> patientlist = model.getPatients();


    //     for (Patient patient : patientlist){
    //         if (patient.getPatientId().equals(patientId)) {
    //             // If the ID matches, display the patient record
    //             System.out.println("Patient ID: " + patient.getPatientId());
    //             System.out.println("Patient Name: " + patient.getContact().getName());
    //             System.out.println("Patient Blood Type: " + patient.getBloodType());
    //             System.out.println("Patient Diagnoses: " + patient.getDiagnosis());
    //             System.out.println("Patient Treatments: " + patient.getTreatment());
    //             return; // Exit the method after displaying the record
    //         }
    //     }
    // }


//     public void updateSpecificPatientRecord(String patientId) {
//     List<Patient> patientlist = model.getPatients();
//     for (Patient patient : patientlist) {
//         if (patient.getPatientId().equals(patientId)) {
//             // Display current patient details
//             System.out.println("Patient found. Current details:");
//             System.out.println("Name: " + patient.getContact().getName());
//             System.out.println("DOB: " + patient.getContact().getDOB());
//             System.out.println("Gender: " + patient.getContact().getGender());
//             System.out.println("Contact Number: " + patient.getContact().getContactNumber());
//             System.out.println("Email: " + patient.getContact().getEmail());
//             System.out.println("Address: " + patient.getContact().getAddress());
           
//             // Ask user which field to update
//             Scanner sc = new Scanner(System.in);
//             System.out.println("Which field would you like to update?");
//             System.out.println("1. Name");
//             System.out.println("2. DOB");
//             System.out.println("3. Gender");
//             System.out.println("4. Contact Number");
//             System.out.println("5. Email");
//             System.out.println("6. Address");
//             System.out.print("Enter your choice: ");
//             int choice = sc.nextInt();
//             sc.nextLine(); // Consume newline


//             switch (choice) {
//                 case 1:
//                     System.out.print("Enter new Name: ");
//                     patient.getContact().setName(sc.nextLine());
//                     break;
//                 case 2:
//                     System.out.print("Enter new DOB (dd/mm/yyyy): ");
//                     patient.getContact().setDOB(sc.nextLine());
//                     break;
//                 case 3:
//                     System.out.print("Enter new Gender (M/F): ");
//                     patient.getContact().setGender(sc.nextLine().charAt(0));
//                     break;
//                 case 4:
//                     System.out.print("Enter new Contact Number: ");
//                     patient.getContact().setContactNumber(sc.nextLine());
//                     break;
//                 case 5:
//                     System.out.print("Enter new Email: ");
//                     patient.getContact().setEmail(sc.nextLine());
//                     break;
//                 case 6:
//                     System.out.print("Enter new Address: ");
//                     patient.getContact().setAddress(sc.nextLine());
//                     break;
//                 default:
//                     System.out.println("Invalid choice. No updates made.");
//                     return;
//             }
//             System.out.println("Patient record updated successfully.");
//             return; // Exit the method after updating
//         }
//     }
//     System.out.println("Patient ID not found.");
// }


    public void viewSchedule(){
        view.displaySchedule(model);
    }


    // public void printRemainingTimeSlots() {
    //     System.out.println("Available Time Slots:");
    //     List<AvailabilitySlot> taken = availSlotController.getAvailabilitySlotsByDoctor(model.getId());
    //     for (TimeSlot slot : TimeSlot.values()) {
    //         int index = slot.getIndex();
    //         for (AvailabilitySlot slotcheck : taken){
    //             if(index == slotcheck.getTimeSlot().getIndex()){
    //                     break;
    //             }
    //             System.out.println(slot.getIndex() + ": " + slot.getTime());
    //         }
           
    //     }
    // }
    public void printRemainingTimeSlots() {
        System.out.println("Available Time Slots:");
        List<AvailabilitySlot> taken = availSlotController.getAvailabilitySlotsByDoctorId(model.getId());
        for (TimeSlot slot : TimeSlot.values()) {
            int index = slot.getIndex();
            boolean isTaken = false;
    
            // Check if this slot is already taken
            for (AvailabilitySlot slotcheck : taken) {
                if (index == slotcheck.getTimeSlot().getIndex()) {
                    isTaken = true;
                    break; // Exit inner loop as we found a match
                }
            }
    
            // If the slot is not taken, print it
            if (!isTaken) {
                System.out.println(slot.getIndex() + ": " + slot.getTime());
            }
        }
    }
    
}
