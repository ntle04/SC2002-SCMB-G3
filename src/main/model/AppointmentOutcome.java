package main.model;

import java.util.ArrayList;
import java.util.List;

import main.model.Prescription;

public class AppointmentOutcome {
    private String serviceType;
    private List<Prescription> prescription;
    private String notes;
    //private Appointment appt

    // Constructor
    public AppointmentOutcome(/*Appointment appt */) {
        this.prescription = new ArrayList<>(); // Initialize the prescriptions list
        //this.appt = appt;
    }

    // Method to get the service type
    public String getServiceType() {
        return serviceType;
    }

    // Method to set the service type
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    // Method to get the prescriptions
    public List<Prescription> getPrescriptions() {
        return prescription;
    }

    // Method to set the prescriptions
    public void setPrescription(List<Prescription> prescription) {
        this.prescription = prescription;
    }

    // Method to get the notes
    public String getNotes() {
        return notes;
    }

    // Method to set the notes
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /*public Appointment getAppointment(){
      return appt;
    } */
}