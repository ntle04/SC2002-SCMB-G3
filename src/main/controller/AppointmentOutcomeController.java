package main.controller;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import main.csvUitls.AppointmentOutcomeCSVManager;
import main.model.Appointment;
import main.model.AppointmentOutcome;
import main.model.AvailabilitySlot;
import main.model.Prescription;
import main.util.TimeSlot;
import main.view.AppointmentOutcomeView;

public class AppointmentOutcomeController {
	private AppointmentOutcomeCSVManager csvManager = new AppointmentOutcomeCSVManager();
    private AppointmentOutcomeView view;
    private List<AppointmentOutcome> outcomes;

    public AppointmentOutcomeController() {
        this.view = new AppointmentOutcomeView();
        //this.outcomes = new ArrayList<AppointmentOutcome>();
        loadAppointmentOutcomes();
    }
    
    private void loadAppointmentOutcomes() {
        try {
        	outcomes = csvManager.loadAppointmentOutcomes();
        } catch (IOException e) {
            System.out.println("Error loading appointment outcomes: " + e.getMessage());
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
			 System.out.println("Error add appointment outcome: " + e.getMessage());
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
        for (AppointmentOutcome record : outcomes) {
            view.displayFullOutcome(record);
        }
    }

     
}
