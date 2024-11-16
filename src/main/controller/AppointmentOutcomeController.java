package main.controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import main.model.AppointmentOutcome;
import main.model.Prescription;
import main.util.PrescriptionStatus;
import main.view.AppointmentOutcomeView;

public class AppointmentOutcomeController {
    private AppointmentOutcome model;
    private AppointmentOutcomeView view;
    private List<AppointmentOutcome> outcomes;

    public AppointmentOutcomeController(AppointmentOutcome model, AppointmentOutcomeView view) {
        this.model = model;
        this.view = view;
        
    }
    
    // Doctor functions
    public void addOutcome(String appointmentId, LocalDate appointmentDate, LocalTime appointmentTime, String serviceType, String doctorId, String patientId) {
        AppointmentOutcome outcome = new AppointmentOutcome(appointmentId, appointmentDate, appointmentTime, serviceType, doctorId, patientId); outcomes.add(outcome);
    }
    
 /*   public void addPrescription(String appointmentId, String prescriptionName) { 
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null) {
            outcome.addPrescription(new Prescription(prescriptionName));
        }
    } */
    
    public void addNotes(String appointmentId, String notes) {
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null) {
            outcome.setNotes(notes);
        }
    }
    
    
    // Pharmacist functions
    /*public void updatePrescriptionStatus(String appointmentId, String prescriptionName, PrescriptionStatus newStatus) {
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null) {
            for (Prescription prescription : outcome.getPrescriptions()) {
                if (prescription.getPrescriptionName().equals(prescriptionName)) {
                    prescription.setStatus(newStatus);
                    break;
                }
            }
        }
    }*/
    
    
    // View functions for the various user types
    public void viewOutcomeAsDoctor(String appointmentId) {
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null) {
            view.displayFullOutcome(outcome);
        }
    }
    
    public void viewOutcomeAsPatient(String appointmentId, String patientId) {
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null && outcome.getPatientId().equals(patientId)) {
            view.displayPatientOutcome(outcome);
        }
    }
    
    public void viewOutcomeAsPharmacist(String appointmentId) {
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null) {
            view.displayPharmacistOutcome(outcome);
        }
    }
    
    public void viewOutcomeAsAdmin(String appointmentId) {
        AppointmentOutcome outcome = findOutcome(appointmentId);
        if (outcome != null) {
            view.displayFullOutcome(outcome);
        }
    }
    
    private AppointmentOutcome findOutcome(String appointmentId) {
        return outcomes.stream()
                      .filter(o -> o.getAppointmentId().equals(appointmentId))
                      .findFirst()
                      .orElse(null);
    }
}
