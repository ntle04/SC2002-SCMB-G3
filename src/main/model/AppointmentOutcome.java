package main.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;
import main.model.Prescription;

public class AppointmentOutcome {
    private String appointmentId;
    private LocalDate appointmentDate;
    private LocalTime appointmentTime;
	private String serviceType;
    private List<Prescription> prescriptions;
    private String notes;
    private String patientId;
    private String doctorId;

    // Constructor
   public AppointmentOutcome(String appointmentId, LocalDate appointmentDate, LocalTime appointmentTime,
		   String serviceType, List<Prescription> prescriptions, String notes, String doctorId, String patientId) {
	   this.appointmentId = appointmentId;
	   this.appointmentDate = appointmentDate;
	   this.serviceType = serviceType;
	   this.prescriptions = new ArrayList<>(); //initialise prescription list
	   this.notes = notes;
	   this.doctorId = doctorId;
	   this.patientId = patientId;
   }

   //Getters and setters
   public String getAppointmentId() {
	   return appointmentId;
   }
   
   public LocalDate getAppointmentDate() {
	   return appointmentDate;
   }
   
   public LocalTime getAppointmentTime() {
	   return appointmentTime;
   }
   
   public String getServiceType() {
	   return serviceType;
   }
   

   public void setServiceType(String serviceType) {
       this.serviceType = serviceType;
   }
 
   public List<Prescription> getPrescriptions() {
       return prescriptions;
   }
   

   public void addPrescription(Prescription prescription) {
	   prescriptions.add(prescription);
   }
   

   public void setPrescription(List<Prescription> prescription) {
       this.prescriptions = prescription;
   }


    public String getNotes() {
        return notes;
    }


    public void setNotes(String notes) {
        this.notes = notes;
    }
    
    public String getPatientId() {
    	return patientId;
    }
    
    public String getDoctorId() {
    	return doctorId;
    }
    
}
