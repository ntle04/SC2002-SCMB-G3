package main.model;

import java.time.LocalDateTime;
import java.util.List;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.stream.Collectors;
import main.controller.Authenticate;
import main.util.ApptStatus;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import main.util.Role;
import main.util.TimeSlot;

import main.controller.Authenticate;

public class Doctor extends Person {
    private String doctorid;
    private List<Patient> patients;
    private List<AvailabilitySlot> availability;
    private List<Appointment> appointments;
   
    public Doctor(String id, Contact contact, Role role, List<Patient> patients, List<AvailabilitySlot> availability, List<Appointment> appointments)
    {
        super(Authenticate.getLoggedInUser().getId(), null, Role.DOCTOR);
        this.patients = patients;
        this.availability = availability != null ? availability : new ArrayList<>();
        // this.appointments = appointments != null ? list : new ArrayList<>();
    }
   
   


    public List<Patient> getPatients() {
        return patients;
    }


    public void addPatient(Patient patient) {
        if (!patients.contains(patient)) {
            patients.add(patient);
        }
    }
   
    public void removePatient(Patient patient) {
        patients.remove(patient);
    }
       
    public List<Appointment> getAppointments(){
        return appointments;
    }


    public void setAppointments(List<Appointment> appt)
    {
        appointments = appt;
    }
       


   
    /*public static Doctor fromCSV(String csvLine) {
        String[] values = csvLine.split(",");
       
        if (values.length != 6) {
            return null; // If data doesn't match expected format, return null
        }
       
        try {
            // Parse values from CSV line
            String id = values[0].trim();
            String name = values[1].trim();
            String dob = values[2].trim();
           
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
            Date parseDob = dateFormat.parse(dob);
           
            Character gender = values[3].trim().charAt(0);
            String contactNumber = values[4].trim();  
            String email = values[5].trim();
            String address = values[6].trim();


            Contact contact = new Contact(name, dob, gender, contactNumber, email, address);
            List<AvailabilitySlot> availability = new ArrayList<AvailabilitySlot>();        
            //List<Appointment> appointments = new ArrayList<String>();


            //return new Doctor(id, contact, Role.DOCTOR, availability, appointments);


        } catch (NumberFormatException | ParseException e) {
            System.out.println("Error parsing CSV line: " + csvLine);
            return null;
        }
    }
   
*/
   


}

