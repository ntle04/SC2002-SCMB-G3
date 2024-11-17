package main.csvUitls;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.controller.Authenticate;
import main.model.AvailabilitySlot;
import main.model.Contact;
import main.model.Person;
import main.util.Role;

public class ContactCSVManager {

    public List<Person> loadPersonsFromCSV(String filePath, Role role) throws IOException {
        List<Person> persons = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                String id, name, age, dob, contactNumber, email, address;
                Contact contact;
                char gender;

                if(role == Role.PATIENT){
                    id = fields[0];
                    name = fields[1];
                    age = fields[2];
                    dob = fields[3];
                    gender = fields[4].charAt(0);
                    contactNumber = fields[5];
                    email = fields[6];
                    address = fields[7];
                    // patientBloodType = fields[8];

                    contact = new Contact(name, age, dob, gender, contactNumber, email, address);
                }else{
                    id = fields[0];
                    name = fields[1];
                    age = fields[3];
                    dob = fields[4];
                    gender = fields[5].charAt(0);
                    contactNumber = fields[6];
                    email = fields[7];
                    address = fields[8];

                    contact = new Contact(name, age, dob, gender, contactNumber, email, address);
                }

                persons.add(new Person(id, contact, role));
            }
        }
        return persons;
    }

    public boolean updateContactInCSV(String filePath, String personId, Role role, Contact updatedContact) throws IOException {
        List<String> lines = new ArrayList<>();
        boolean updated = false;

        // Read the file line by line
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                if (fields[0].equals(personId)) {
                    if(role == Role.PATIENT){
                        System.out.println("updating patient contact");
                        fields[1] = updatedContact.getName();
                        fields[2] = updatedContact.getAge();
                        fields[3] = updatedContact.getDOB();
                        fields[4] = String.valueOf(updatedContact.getGender());
                        fields[5] = updatedContact.getContactNumber();
                        fields[6] = updatedContact.getEmail();
                        fields[7] = updatedContact.getAddress();
                        line = String.join(",", fields);
                    }else{
                        System.out.println("updating staff contact");
                        fields[1] = updatedContact.getName();
                        fields[3] = updatedContact.getAge();
                        fields[4] = updatedContact.getDOB();
                        fields[5] = String.valueOf(updatedContact.getGender());
                        fields[6] = updatedContact.getContactNumber();
                        fields[7] = updatedContact.getEmail();
                        fields[8] = updatedContact.getAddress();
                        line = String.join(",", fields);
                    }
                    
                    updated = true;
                }
                lines.add(line); // Keep all lines (updated or not)
            }
        }

        // Write the updated lines back to the file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String updatedLine : lines) {
                bw.write(updatedLine);
                bw.newLine();
            }
        }

        return updated;
    }
    
}
