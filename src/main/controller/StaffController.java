package main.controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.*;
import java.util.Scanner;
import main.csvUitls.*;

import main.model.Contact;
import main.model.Staff;
import main.util.Role;
import main.util.AgeCalc;
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
    
    
    /** 
     * @param staff
     */
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

    public void createStaff() {
        AgeCalc calc = new AgeCalc();
        System.out.println("Enter staff role (DOCTOR, PHARMACIST, ADMINISTRATOR):");
        String role = sc.nextLine().toUpperCase();
        char x = role.charAt(0);
        Role roleEnum = Role.valueOf(role);        
        String id = x + IdGenerator.generateNewId(file_path);
        System.out.println("Enter staff name:");
        String name = sc.nextLine();
        System.out.println("Enter staff date of birth (dd/MM/yyyy):");
        String dob = sc.nextLine();
        String age = calc.calculateAge(dob);
        System.out.println("Enter staff gender (M/F):");
        char gender = sc.next().charAt(0);
        sc.nextLine();
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
        saveAllChanges();
        this.view.printStaffRecord(newStaff);
    }

    public void removeStaff(){
        System.out.println("Enter staff ID: ");
        String id = sc.nextLine();
        for(Staff staff : staffList){
            if(staff.getId().equals(id)){
                staffList.remove(staff);
                System.out.println("Removed staff.");
                saveAllChanges();
                return;
            }
        }
        System.out.println("Staff does not exist.");
    }

    public void updateStaff(){
        int choice = -1;
        System.out.println("Enter staff ID: ");
        String id = sc.nextLine();
        for(Staff staff : staffList){
            if(staff.getId().equals(id)){
                do{
                    printUpdateMenu();

                    choice = sc.nextInt();
                    sc.nextLine();

                    switch(choice){
                        case 1:
                            System.out.printf("Enter new name: ");
                            String name = sc.nextLine();
                            staff.getContact().setName(name);
                            System.out.println("Updated staff name");
                            break;
                        case 2:
                            System.out.printf("Enter new role (DOCTOR, ADMINISTRATOR, PHARMACIST): ");
                            String role = sc.nextLine().toUpperCase();
                            staff.setRole(Role.valueOf(role));
                            System.out.println("Updated staff role");
                            break;
                        case 3:
                            AgeCalc calc = new AgeCalc();
                            System.out.printf("Enter new date of birth (dd/MM/yyyy): ");
                            String dob = sc.nextLine();
                            staff.getContact().setDOB(dob);
                            String age = calc.calculateAge(dob);
                            staff.getContact().setAge(age);
                            System.out.println("Updated staff date of birth and age");
                            break;
                        case 4:
                            System.out.printf("Enter new gender: ");
                            char gender = sc.next().charAt(0);
                            sc.nextLine();
                            staff.getContact().setGender(gender);
                            System.out.println("Updated staff gender");
                            break;
                        case 5:
                            System.out.println("Enter new phone number");
                            String num = sc.nextLine();
                            staff.getContact().setContactNumber(num);
                            System.out.println("Updated staff contact number");
                            break;
                        case 6:
                            System.out.printf("Enter new email address: ");
                            String email = sc.nextLine();
                            staff.getContact().setEmail(email);
                            System.out.println("Updated staff email address");
                            break;
                        case 7:
                            System.out.printf("Enter new address: ");
                            String add = sc.nextLine();
                            staff.getContact().setAddress(add);
                            System.out.println("Updated staff address");
                            break;
                    }
                } while(choice < 8);
                saveAllChanges();
                System.out.println("Staff information has been updated! \n");
                view.printAllStaff(staffList);
                return;
            }
        } 
        System.out.println("Staff does not exist.");
    }

    public void printUpdateMenu(){
        System.out.println("Enter your choice: ");
        System.out.println("1. Update name");
        System.out.println("2. Update role");
        System.out.println("3. Update date of birth");
        System.out.println("4. Update gender");
        System.out.println("5. Update contact number");
        System.out.println("6. Update email address");
        System.out.println("7. Update address");
        System.out.println("8: Return");
    }

    //save to csv
    public void saveAllChanges() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file_path))) {
            for (Staff staff : staffList) {
                writer.write(staff.toCSV());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Staff> filterByRole(String role){
        Role roleEnum = Role.valueOf(role.trim());
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

}

    