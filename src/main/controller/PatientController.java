package main.controller;
import java.time.LocalDateTime;
import java.util.List;
import main.model.Patient;
import main.view.PatientView;

public class PatientController {
    private Patient model;
    private PatientView view;

    // Constructor
    public PatientController(Patient model, PatientView view) {
        this.model = model;
        this.view = view;
    }

    // View the medical record 
    public void viewPatientRecord() { 
    	this.view.printPatientRecord(this.model.getPatientId(), this.model.getName(), this.model.getDob(),
    			this.model.getGender(), this.model.getEmail(), this.model.getAddress(), this.model.getPatientBloodType(),
    			this.model.getDiagnosis(), this.model.getTreatment(this.model.getPatientId()));
    }
    
 
    public void viewUpdatedPatientContact() {
    	this.view.printUpdatedPatientContact(this.model.getPatientId(), this.model.getEmail(), this.model.getAddress());
    }
}
    