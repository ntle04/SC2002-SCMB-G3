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
            if (outcomes == null) {
                System.out.println("No records found.");
                outcomes = new ArrayList<>();
            }
        } catch (IOException e) {
            outcomes = new ArrayList<>();
        }
    }
    
    
    /** 
     * @param appointmentId
     * @param appointmentDate
     * @param appointmentTime
     * @param serviceType
     * @param prescriptions
     * @param notes
     * @param doctorId
     * @param patientId
     * @return String
     */
    // Doctor functions
    public String addOutcome(String appointmentId, String appointmentDate, TimeSlot appointmentTime, String serviceType, List<Prescription> prescriptions, String notes, String doctorId, String patientId) {
        try {
        	AppointmentOutcome outcome = new AppointmentOutcome(appointmentId, appointmentTime, serviceType, prescriptions, notes, doctorId, patientId); 	
			csvManager.addAppointmentOutcome(outcome);
			return outcome.getOutcomeId();
		} catch (IOException e) {
		}

        return null;
    	
    }

    public void updateOutcome(AppointmentOutcome outcome) {
        try {
			csvManager.updateAppointmentOutcome(outcome);
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

    public AppointmentOutcome getOutcomeById(String outcomeId){
        loadAppointmentOutcomes();
        for (AppointmentOutcome record : outcomes) {
            if(record.getOutcomeId().equals(outcomeId)){
                return record;
            }
        }
        System.out.println("Record not found.");
        return null;
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

      public void printAllAdminOutcomes(){
        loadAppointmentOutcomes();
        for(AppointmentOutcome outcome : outcomes){
            System.out.println("=== Appointment Outcome Record ===");
            System.out.println("Outcome ID: " + outcome.getOutcomeId());
            System.out.println("Appointment ID: " + outcome.getAppointmentId());
            System.out.println("Doctor ID: " + outcome.getDoctorId());
            System.out.println("Patient ID: " + outcome.getPatientId());
            System.out.println("Date: " + outcome.getAppointmentDate());
            System.out.println("=== Prescription List ===");

            for(Prescription prescription: outcome.getPrescriptions()){
                System.out.println("Med ID: " + prescription.getMedId());
                System.out.println("Dosage: " + prescription.getDosage());
                System.out.println("Quantity: " + prescription.getQuantity());
                System.out.println("----------------");
            }

        }
      }
     
}
