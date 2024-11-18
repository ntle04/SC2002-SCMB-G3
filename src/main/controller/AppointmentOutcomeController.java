package main.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;

import main.csvUitls.AppointmentOutcomeCSVManager;
import main.model.AppointmentOutcome;
import main.model.Prescription;
import main.util.TimeSlot;
import main.view.AppointmentOutcomeView;

public class AppointmentOutcomeController {
	private AppointmentOutcomeCSVManager csvManager = new AppointmentOutcomeCSVManager();
    private AppointmentOutcomeView view;
    private List<AppointmentOutcome> outcomes;

    public AppointmentOutcomeController() {
        this.view = new AppointmentOutcomeView();
        outcomes = new ArrayList<>();
        loadAppointmentOutcomes();
    }
    
    private void loadAppointmentOutcomes() {
        try {
        	outcomes = csvManager.loadAppointmentOutcomes();
        } catch (IOException e) {
        }

        System.out.println("Finish loading data");
    }
    
    // Doctor functions
    public void addOutcome(String appointmentId, String appointmentDate, TimeSlot appointmentTime, String serviceType, List<Prescription> prescriptions, String notes, String doctorId, String patientId) {
        try {
        	AppointmentOutcome outcome = new AppointmentOutcome(appointmentId, appointmentTime, serviceType, prescriptions, notes, doctorId, patientId); 	
        	System.out.println("Adding appointment outcome");
			csvManager.addAppointmentOutcome(outcome);
			//outcomes.add(outcome);
		} catch (IOException e) {
		}
    	
    }

    
    // View functions for the various user types
	public void viewOutcomeAsDoctor(String appointmentId) {
	     AppointmentOutcome outcome = getOutcomeByAppointmentId(appointmentId);
	     if (outcome != null) {
	         view.displayFullOutcome(outcome);
	     }
	}
    
    public void viewOutcomeAsPatient(String appointmentId, String patientId) {
         AppointmentOutcome outcome = getOutcomeByAppointmentId(appointmentId);
        if (outcome != null && outcome.getPatientId().equals(patientId)) {
             view.displayPatientOutcome(outcome);
         }
        else
        {
        	System.out.println("=== No Appointment Outcome ===");
        }
     }
    
    public void viewOutcomeAsPharmacist(String appointmentId) {
        AppointmentOutcome outcome = getOutcomeByAppointmentId(appointmentId);
        if (outcome != null) {
            view.displayPharmacistOutcome(outcome);
        }
    }
    
    public void viewOutcomeAsAdmin(String appointmentId) {
        AppointmentOutcome outcome = getOutcomeByAppointmentId(appointmentId);
        if (outcome != null) {
            view.displayFullOutcome(outcome);
        }
    }

    public AppointmentOutcome getOutcomeByAppointmentId(String appointmentId) {
         return outcomes.stream()
                       .filter(o -> o.getAppointmentId().equals(appointmentId))
                       .findFirst()
                       .orElse(null);
     }
     
    public void printPatientOutcome(String appointmentId){
         AppointmentOutcome selectedOutcome = getOutcomeByAppointmentId(appointmentId);
         view.displayPatientOutcome(selectedOutcome);
    }

    public void printAllAppointmentOutcomes(){
        loadAppointmentOutcomes();
        System.out.println("size: " + outcomes.size());
        for (AppointmentOutcome record : outcomes) {
            view.displayFullOutcome(record);
        }
    }

      public void printAllAdminOutcomes(){
        loadAppointmentOutcomes();
        for(AppointmentOutcome outcome : outcomes){
            System.out.println("=== Appointment Outcome Record ===");
            System.out.println("Appointment ID: " + outcome.getAppointmentId());
            System.out.println("Doctor ID: " + outcome.getDoctorId());
            System.out.println("Patient ID: " + outcome.getPatientId());
            System.out.println("Date: " + outcome.getAppointmentDate());
        }
      }
     
}
