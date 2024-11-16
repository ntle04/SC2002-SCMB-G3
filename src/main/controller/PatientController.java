package main.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.Appointment;
import main.model.Contact;
import main.model.Patient;
import main.util.Config;
import main.util.Role;
import main.view.PatientView;

public class PatientController {
    private Patient model;
    private PatientView view;
    
    private List<Patient> patientList = new ArrayList<>();
    private String filePath = Config.PATIENT_LIST_FILE_PATH;

    // Constructor
    public PatientController(Patient model) {
        this.model = model;
        patientList = loadAllPatientsFromFile();
    }

    // View the medical record 
    public void viewPatientRecord() { 
    	this.view.printPatientRecord(this.model);
    }
    
    public void viewUpdatedPatientContact() {
    	this.view.printUpdatedPatientContact(this.model.getContact(), this.model.getPatientId());
    }
 
    //load data from csv
    private List<Patient> loadAllPatientsFromFile() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	Patient patient = Patient.fromCSV(line);
                if (patient != null) {
                	patients.add(patient);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file, returning empty list.");
            return new ArrayList<>();
        }
        return patients;
    }
    
    //create new
    public void createPatient(String id, Contact contact, Role role,
    		String patientId, String patientBloodType, List<Appointment> patientAppointment, 
    		List<String> diagnosis, List<String> treatment) {
    	//Patient new Patient(
    	
    	Patient patient = new Patient(id, contact, Role.PATIENT, patientId, patientBloodType, patientAppointment, diagnosis, treatment);
     
        patientList.add(patient);
        this.view.printPatientRecord(patient);
    }

}
    