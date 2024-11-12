package main.view;
import java.time.LocalDateTime;
import java.util.List;

public class PatientView {

    // Display the patient medical record
    public void printPatientRecord(int patientId, String name, LocalDateTime dob, char gender, String email, String address, char patientBloodType, List<String> diagnosis, List<String> treatment) {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("DOB: " + dob); 
        System.out.println("Gender: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Blood Type: " + patientBloodType);
        System.out.println("Diagnoses: " + diagnosis);
        System.out.println("Treatments: " + treatment);
    }
    
    public void printUpdatedPatientContact(int patientId, String email, String address) {
    	System.out.println("Patient ID: " + patientId);
        System.out.println("Email: " + email); 
        System.out.println("Address: " + address);
    	
    }
 
    public void printUpdateInformation(String email) {
    	System.out.println("Email: " + email); 
    }
    
    public String printUpdateConfirmation() {
    	return "Patient information updated successfully!";
    }
    
    
}
    