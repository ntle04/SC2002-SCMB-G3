package main.controller;

import java.util.List;

import main.model.Patient;
import main.view.PatientView;

public class AppointmentOutcomeController {
    private int appointmentId;
    private AppointmentOutcome model;
    private AppointmentOutcomeView view;

    // Constructor
    public AppointmentOutcomeController(AppointmentOutcome model, AppointmentOutcomeView view) {
        this.model = model;
        this.view = view;
    }

    
    // Method to get the service type for a specific appointment outcome
    public String getServiceType(int id) {
        return model.getServiceType(id);
    }

    // Method to set the service type for a specific appointment outcome
    public void setServiceType(int id, String type) {
        model.setServiceType(id, type);
        updateView();
    }

    // Method to get the prescriptions for a specific appointment outcome
    public List<Prescriptions> getPrescriptions(int id) {
        return model.getPrescriptions(id);
    }

    // Method to set the prescriptions for a specific appointment outcome
    public void setPrescriptions(int id, List<Prescriptions> prescriptions) {
        model.setPrescriptions(id, prescriptions);
        updateView();
    }

    // Method to get notes for a specific appointment outcome
    public String getNotes(int id) {
        return model.getNotes(id);
    }

    // Method to set notes for a specific appointment outcome
    public void setNotes(int id, String notes) {
        model.setNotes(id, notes);
        updateView();
    }

    // Method to update the view
    public void updateView() {
        view.printAppointmentOutcome(model);
    }
}