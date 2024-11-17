package main.controller;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
//import java.util.Comparator;
import java.util.Scanner;
import main.csvUitls.*;

import main.model.Appointment;
import main.model.Contact;
import main.model.Person;
import main.controller.PersonController;
import main.model.Staff;
import main.util.Role;
import main.view.StaffView;

public class StaffController {
    Scanner sc = new Scanner(System.in);
    private static final String file_path = Config.STAFF_LIST_FILE_PATH;
    PersonController pc = new PersonController();


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
    
    public Person getStaffDetails(){
        System.out.println("Retrieving staff details...");
        System.out.println("Enter staff id:");
        String id = sc.nextLine();
        System.out.println("Enter role of staff:");
        String role = sc.nextLine();
        Role roleEnum = Role.valueOf(role);
        Person person = pc.getPersonById(id, roleEnum);
        return person;
    }

    public void createStaff() {
        String id = IdGenerator.generateNewId(file_path);
        System.out.println("Enter staff role in uppercase:");
        String role = sc.nextLine();
        Role roleEnum = Role.valueOf(role);
        System.out.println("Enter staff name:");
        String name = sc.nextLine();
        System.out.println("Enter staff age:");
        String age = sc.nextLine();
        System.out.println("Enter staff date of birth (dd/MM/yyyy):");
        String dob = sc.nextLine();
        System.out.println("Enter staff gender (M/F):");
        char gender = sc.next().charAt(0);
        System.out.println("Enter staff contact number:");
        String contactNum = sc.nextLine();
        System.out.println("Enter staff email:");
        String email = sc.nextLine();
        System.out.println("Enter staff address:");
        String address = sc.nextLine();


        Contact contact = new Contact(name, age, dob, gender, contactNum, email, address);
        Staff newStaff = new Staff(id, contact, roleEnum);
        
        staffList.add(newStaff);
        System.out.println("Created Staff");
        this.view.printStaffRecord(newStaff);
    }

    public void removeStaff(){
        Person person = getStaffDetails();
        staffList.remove(person);
        System.out.println("Removed Staff");
    }

    public void updateStaff(){
        Person person = getStaffDetails();
        String id = person.getId();
        Contact contact = person.getContact();
        ContactController conCon = new ContactController(contact);
        conCon.updateContact();
        view.printUpdatedStaffContact(contact, id);
        view.printUpdateConfirmation();
    }

    public List<Staff> filterByRole(String role){
        Role roleEnum = Role.valueOf(role.toUpperCase());
        return staffList.stream()
                        .filter(staff -> staff.getRole() == roleEnum)
                        .collect(Collectors.toList());
    }

    public List<Staff> filterByGender(char gender){
        return staffList.stream()
                        .filter(staff -> staff.getGender() == gender)
                        .collect(Collectors.toList());
    }
    
    public List<Staff> filterByAge(int minAge, int maxAge){
        return staffList.stream()
                        .filter(staff -> {
                            int age = staff.getIntAge();
                            return age >= minAge && age <= maxAge;
                        })
                        .collect(Collectors.toList());
    }

    /*
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
    */
}

    