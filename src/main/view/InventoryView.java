package main.view;
import java.util.List;
import main.model.Inventory;

public class InventoryView {

    // Display the medicine inventory
    public void printInventory(Inventory inventory) {    
    	System.out.println("Medication ID: " + inventory.getId());
        System.out.println("Name: " + patient.getContact().getName());
        System.out.println("DOB: " + patient.getContact().getDOB()); 
        System.out.println("Gender: " + patient.getContact().getGender());
        System.out.println("Email: " + patient.getContact().getEmail());
        System.out.println("Blood Type: " + patient.getPatientBloodType());
        System.out.println("Diagnoses: " + patient.getDiagnosis());
        System.out.println("Treatments: " + patient.getTreatment(patient.getId()));
    }
    
    public void printUpdatedPatientContact(Contact contact, String patientId) {
    	System.out.println("Patient ID: " + patientId);
        System.out.println("Email: " + contact.getEmail()); 
        System.out.println("Address: " + contact.getAddress());
    	
    }
 
    public void printUpdateInformation(String email) {
    	System.out.println("Email: " + email); 
    }
    
    public String printUpdateConfirmation() {
    	return "Patient information updated successfully!";
    }
    
    
}
    