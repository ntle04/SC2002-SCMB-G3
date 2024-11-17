package main.model;

import main.util.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.model.Contact;

public class Staff extends Person{
    private Contact contact;
    private String id;
    public Staff(String id, Contact contact, Role role) {
        super(id, contact, role);
        this.contact = contact;
        this.id = id;
    }

    public static Staff fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
        
        if (values.length != 9) {
            return null; // If data doesn't match expected format, return null
        }
        
        try {
            // Parse values from CSV line
        	String id = values[0].trim();
            String name = values[1].trim();
            String role = values[2].trim();
			String age = values[3].trim();
			String dob = values[4].trim();
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date parseDob = dateFormat.parse(dob);
            
            Character gender = values[5].trim().charAt(0);
            String contactNumber = values[6].trim();   
            String email = values[7].trim();
            String address = values[8].trim();

            Contact contact = new Contact(name, age, dob, gender, contactNumber, email, address);

            return new Staff(id, contact, Role.valueOf(role));

        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
            return null;
        }
    }

    public char getGender(){
        return contact.getGender();
    }

    public String getAge(){
        return contact.getAge();
    }

    public int getIntAge(){
        String age = contact.getAge();
        try{
            return Integer.parseInt(age);
        } catch(NumberFormatException e){
            return -1;
        }
    }

    public String toCSV() {
        String name = contact.getName();
        Role role = getRole();
        String roleString = role.name();
        String age = contact.getAge();
        String dob = contact.getDOB();
        char gender = contact.getGender();
        String contactNum = contact.getContactNumber();
        String email = contact.getEmail();
        String address = contact.getAddress();

        return id + "," + name + "," + roleString + "," + age + "," + dob + "," + gender + "," + contactNum + "," + email + "," + address;
    }
}
