package main.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.Patient;
import main.csvUitls.Config;
import main.view.PatientView;

public class PatientController {

    private PatientView view;
    
    private List<Patient> patientList = new ArrayList<>();
    private String filePath = Config.PATIENT_LIST_FILE_PATH;

    // Constructor
    public PatientController() {
    	this.view = new PatientView();
        this.patientList = loadAllPatientsFromFile();
    }

    // View the medical record 
    public void viewPatientRecord(Patient patient) { 
    	this.view.printPatientRecord(patient);
    }
    
    public void viewUpdatedPatientContact(Patient patient) {
    	this.view.printUpdatedPatientContact(patient.getContact(), patient.getId());
    }
 
    //load data from csv
    private List<Patient> loadAllPatientsFromFile() {
        List<Patient> patients = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;
            while ((line = reader.readLine()) != null) {
            	lineNum++;
            	if(lineNum == 1) continue;
            	Patient patient = Patient.fromCSV(line);
                if (patient != null) {
                	patients.add(patient);
                }          
            }
        } catch (IOException e) {
            System.out.println("Error reading file, returning empty list. " + e.getMessage());
            return new ArrayList<>();
        }
        return patients;
    }
    
    public List<Patient> getPatientList()
    {
    	return patientList;	
    }
    
    //create new
    public void createPatient(Patient patient) {
        patientList.add(patient);
        System.out.println("Created Patient");
        this.view.printPatientRecord(patient);
    }

}