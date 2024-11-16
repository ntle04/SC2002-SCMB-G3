package main.model;
import main.util.RequestStatus;
// import java.util.stream.Collectors;
import main.util.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;

public class Patient extends Person{ //extended to Person class

	private String patientId;
	private String patientBloodType;
    private List<Appointment> patientAppointment;
    private List<String> diagnosis;
    private List<String> treatment;

    public Patient(String id, Contact contact, Role role,
    		String patientId, String patientBloodType, List<Appointment> patientAppointment, 
    		List<String> diagnosis, List<String> treatment) {
		super(id, contact, Role.PATIENT);

		this.patientId = patientId;
		this.patientBloodType = patientBloodType;
		this.patientAppointment = patientAppointment;
		this.diagnosis = diagnosis;
		this.treatment = treatment;
	}


    public String getPatientId() {
		return patientId;
	}


	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}


	public String getPatientBloodType() {
		return patientBloodType;
	}


	public void setPatientBloodType(String patientBloodType) {
		this.patientBloodType = patientBloodType;
	}


	public List<Appointment> getPatientAppointment() {
		return patientAppointment;
	}

	// from tim's suggestion
	public void setPatientAppointment(Appointment appt){
		  this.patientAppointment.add(appt);
	}


	public List<String> getDiagnosis() {
		return diagnosis;
	}


	public void setDiagnosis(List<String> diagnosis) {
		this.diagnosis = diagnosis;
	}


	public List<String> getTreatment(String patientId) {
		return treatment;
	}


	public void setTreatment(List<String> treatment) {
		this.treatment = treatment;
	}

    
	public void updatePatientContact(Contact contact) {
		this.updateContact(contact);
	}
	
	
	public static Patient fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        
        if (values.length != 6) {
            return null; // If data doesn't match expected format, return null
        }
        
        try {
            // Parse values from CSV line
        	String id = values[0].trim();
            String name = values[1].trim();
            String dob = values[2].trim();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date parseDob = dateFormat.parse(dob);
            
            Character gender = values[3].trim().charAt(0);
            String contactNumber = values[4].trim();   
            String email = values[5].trim();
            String address = values[6].trim();
            String patientId = values[7].trim();
            String patientBloodType = values[8].trim();

            Contact contact = new Contact(name, dob, gender, contactNumber, email, address);
            List<Appointment> patientAppointment = new ArrayList<Appointment>();
            List<String> diagnosis = new ArrayList<String>();
            List<String> treatment = new ArrayList<String>();

            return new Patient(id, contact, Role.PATIENT, patientId, patientBloodType, patientAppointment, diagnosis, treatment);

        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
            return null;
        }
    }
	
}

