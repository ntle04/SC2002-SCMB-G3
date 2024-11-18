package main.view;

import java.util.List;

import main.model.Contact;
import main.model.Staff;

public class StaffView {
    // Display the staff  record
    public void printStaffRecord(Staff staff) {    
    	System.out.println("Staff ID: " + staff.getId());
        System.out.println("Name: " + staff.getContact().getName());
        System.out.println("Role: " + staff.getRole());
        System.out.println("Age: " + staff.getContact().getAge());
        System.out.println("DOB: " + staff.getContact().getDOB()); 
        System.out.println("Gender: " + staff.getContact().getGender());
        System.out.println("Email: " + staff.getContact().getEmail());
    }
    
    public void printUpdatedStaffContact(Contact contact, String staffId) {
    	System.out.println("Staff ID: " + staffId);
        System.out.println("Email: " + contact.getEmail()); 
        System.out.println("Address: " + contact.getAddress());
    	
    }
    
    public String printUpdateConfirmation() {
    	return "Staff information updated successfully!";
    }

    public void printAllStaff(List<Staff> staffList) {
        for (Staff staff : staffList) {
            printStaffRecord(staff);
            System.out.println("-------------------------");
        }
    }


}
