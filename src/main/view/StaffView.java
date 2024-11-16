package main.view;

import main.model.Contact;
import main.model.Staff;

public class StaffView {
    // Display the staff  record
    public void printStaffRecord(Staff staff) {    
    	System.out.println("Staff ID: " + staff.getId());
        System.out.println("Name: " + staff.getContact().getName());
        System.out.println("Name: " + staff.getContact().getAge());
        System.out.println("DOB: " + staff.getContact().getDOB()); 
        System.out.println("Gender: " + staff.getContact().getGender());
        System.out.println("Email: " + staff.getContact().getEmail());
    }
    
    public void printUpdatedStaffContact(Contact contact, String staffId) {
    	System.out.println("Staff ID: " + staffId);
        System.out.println("Email: " + contact.getEmail()); 
        System.out.println("Address: " + contact.getAddress());
    	
    }
 
    public void printUpdateInformation(String email) {
    	System.out.println("Email: " + email); 
    }
    
    public String printUpdateConfirmation() {
    	return "Staff information updated successfully!";
    }
}
