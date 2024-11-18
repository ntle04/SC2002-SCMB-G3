package main.view;

import java.util.Scanner;
import main.controller.AppointmentOutcomeController;
import main.model.AppointmentOutcome;
import main.model.Prescription;

public class AppointmentOutcomeView {
     public void displayFullOutcome(AppointmentOutcome outcome) {
         System.out.println("=== Appointment Outcome Record ===");
         System.out.println("Appointment ID: " + outcome.getAppointmentId());
         System.out.println("Date: " + outcome.getAppointmentDate());
         System.out.println("Service Type: " + outcome.getServiceType());
         System.out.println("Doctor ID: " + outcome.getDoctorId());
         System.out.println("Patient ID: " + outcome.getPatientId());
         if(!outcome.getPrescriptions().isEmpty()){
            System.out.println("\nPrescriptions:");
            for (Prescription prescription : outcome.getPrescriptions()) {
                System.out.println("- " + prescription.getPrescriptionId() + 
                                 " (Status: " + prescription.getPrescriptionStatus() + ")");
            }
         }else{
            System.out.println("\nNo prescriptions prescribed.");
         }
         
         System.out.println("\nConsultation Notes:");
         System.out.println(outcome.getNotes());
     }
    
     public void displayPatientOutcome(AppointmentOutcome outcome) {
         System.out.println("=== Your Appointment Outcome ===");
         System.out.println("Date: " + outcome.getAppointmentDate());
         System.out.println("Service Type: " + outcome.getServiceType());
         System.out.println("\nPrescribed Medications:");
         for (Prescription prescription : outcome.getPrescriptions()) {
             System.out.println("- " + prescription.getPrescriptionId() + 
                              " (Status: " + prescription.getPrescriptionStatus() + ")");
         }
         System.out.println("\nConsultation Notes:");
         System.out.println(outcome.getNotes());
     }
    
     public void displayPharmacistOutcome(AppointmentOutcome outcome) {
         System.out.println("=== Prescription Order ===");
         System.out.println("Appointment ID: " + outcome.getAppointmentId());
         System.out.println("Date: " + outcome.getAppointmentDate());
         System.out.println("Patient ID: " + outcome.getPatientId());
         System.out.println("\nPrescriptions:");
         for (Prescription prescription : outcome.getPrescriptions()) {
             System.out.println("- " + prescription.getPrescriptionId() + 
                              " (Status: " + prescription.getPrescriptionStatus() + ")");
         }
     }

     public void displayAdminOutcome(AppointmentOutcome outcome){
        System.out.println("=== Appointment Outcome Record ===");
        System.out.println("Appointment ID: " + outcome.getAppointmentId());
        System.out.println("Doctor ID: " + outcome.getDoctorId());
        System.out.println("Patient ID: " + outcome.getPatientId());
        System.out.println("Date: " + outcome.getAppointmentDate());
     }

}
