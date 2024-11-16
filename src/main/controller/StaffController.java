package main.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import main.model.Appointment;
import main.model.Contact;
import main.model.Staff;
import main.csvUitls.Config;
import main.util.Role;
import main.view.StaffView;

public class StaffController {
    //private Staff model;
    private StaffView view;
    
    private List<Staff> staffList = new ArrayList<>();
    private String filePath = Config.STAFF_LIST_FILE_PATH;

    // Constructor
    public StaffController(StaffView view) { //Staff model
        this.view = view;
        staffList = loadAllStaffFromFile();
    }
    
    public void viewUpdatedStaffContact(Staff staff) {
    	this.view.printUpdatedStaffContact(staff.getContact(), staff.getId());
    }
 
    //load data from csv
    private List<Staff> loadAllStaffFromFile() {
        List<Staff> allStaff = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	Staff staff = Staff.fromCSV(line);
                if (staff != null) {
                	allStaff.add(staff);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file, returning empty list.");
            return new ArrayList<>();
        }
        return allStaff;
    }
    
    public List<Staff> getStaffList()
    {
    	return staffList;	
    }
    
    public void createStaff(Staff staff) {
        staffList.add(staff);
        System.out.println("Created Staff");
        this.view.printStaffRecord(staff);
    }

    public void removeStaff(Staff staff){
        staffList.remove(staff);
        System.out.println("Removed Staff");
        this.view.printStaffRecord(staff);
    }

    public void sortByGender(){
        List<Staff> tempStaff = new ArrayList<>(staffList);
        tempStaff.sort(Comparator.comparing(staff -> staff.getContact().getGender()));
        System.out.println("Staff sorted by gender:");
        for(Staff staff : tempStaff){
            this.view.printStaffRecord(staff);
        }
    }

    public void sortByName(){
        List<Staff> tempStaff = new ArrayList<>(staffList);
        tempStaff.sort(Comparator.comparing(staff -> staff.getContact().getName()));
        System.out.println("Staff sorted by name:");
        for(Staff staff : tempStaff){
            this.view.printStaffRecord(staff);
        }
    }

    public void sortById(){
        List<Staff> tempStaff = new ArrayList<>(staffList);
        tempStaff.sort(Comparator.comparing(staff -> staff.getId()));
        System.out.println("Staff sorted by Id:");
        for(Staff staff : tempStaff){
            this.view.printStaffRecord(staff);
        }
    }

    public void sortByAge(){
        List<Staff> tempStaff = new ArrayList<>(staffList);
        tempStaff.sort(Comparator.comparing(staff -> staff.getContact().getAge()));
        System.out.println("Staff sorted by age:");
        for(Staff staff : tempStaff){
            this.view.printStaffRecord(staff);
        }
    }

    public void sortByRole(){
        List<Staff> tempStaff = new ArrayList<>(staffList);
        tempStaff.sort(Comparator.comparing(staff -> staff.getRole()));
        System.out.println("Staff sorted by role:");
        for(Staff staff : tempStaff){
            this.view.printStaffRecord(staff);
        }
    }
}

    