package main.model;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import java.time.LocalDate;

import main.csvUitls.Config;
import main.csvUitls.IdGenerator;
import main.model.Prescription;
import main.util.TimeSlot;

public class AppointmentOutcome {
    private static String idCounter;
    private String outcomeId;
    private String appointmentId;
    private String appointmentDate;
    private TimeSlot appointmentTime;
	private String serviceType;
    private List<Prescription> prescriptions;
    private String notes;
    private String patientId;
    private String doctorId;

    private static final String FILE_PATH = Config.APPOINTMENT_OUTCOME_FILE_PATH;

    //csv
    public AppointmentOutcome(String outcomeId, String appointmentId, String appointmentDate, TimeSlot appointmentTime, String serviceType, List<Prescription> prescriptions, String notes, String doctorId, String patientId) {
        this.outcomeId = outcomeId;
        this.appointmentId = appointmentId;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.serviceType = serviceType;
        this.prescriptions = prescriptions;
        this.notes = notes;
        this.doctorId = doctorId;
        this.patientId = patientId;
   }

    // new record
    public AppointmentOutcome(String appointmentId, TimeSlot appointmentTime, String serviceType, List<Prescription> prescriptions, String notes, String doctorId, String patientId) {

        LocalDate today = LocalDate.now();
        String dateAsString = today.toString();
        
        idCounter = IdGenerator.generateNewId(FILE_PATH);
        this.outcomeId = "AO" + idCounter;
        this.appointmentId = appointmentId;
        this.appointmentDate = dateAsString;
        this.appointmentTime = appointmentTime;
        this.serviceType = serviceType;
        this.prescriptions = prescriptions;
        this.notes = notes;
        this.doctorId = doctorId;
        this.patientId = patientId;
   }

   //Getters and setters

   public String getOutcomeId() {
    return outcomeId;
}

   public String getAppointmentId() {
	   return appointmentId;
   }
   
   public String getAppointmentDate() {
	   return appointmentDate;
   }

   public void setAppointmentDate(String appointmentDate) {
    this.appointmentDate = appointmentDate;
}

   public TimeSlot getAppointmentTime() {
    return appointmentTime;
}

public void setAppointmentTime(TimeSlot appointmentTime) {
    this.appointmentTime = appointmentTime;
}
   
   public String getServiceType() {
	   return serviceType;
   }

   public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
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

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }
   
       public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
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
    
}
