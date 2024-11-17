package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.Appointment;
import main.model.Patient;
import main.util.ApptStatus;
import main.util.Role;
import main.util.TimeSlot;

public class PatientCSVManager {

    private static final String FILE_PATH = Config.PATIENT_LIST_FILE_PATH;
    private static final String[] HEADER = {"Id,name,age,dob,gender,contactNumber,email,address,patientBloodType"};

    // public List<Appointment> readPatients() throws IOException {
    //     List<String[]> records = CSVHelper.readCSV(FILE_PATH);
    //     List<Appointment> patients = new ArrayList<>();
    //     for (String[] record : records) {
    //         patients.add(new Patient(record[0], record[1], record[2], TimeSlot.getByTime(record[3]), ApptStatus.valueOf(record[4]), record[5], record[6]));
    //     }
    //     return patients;
    // }

    public void addPatient(Patient patient) throws IOException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{
            String.valueOf(patient.getId()),
            String.valueOf(patient.getContact().getName()),
            String.valueOf(patient.getContact().getAge()),
            String.valueOf(patient.getContact().getGender()),
            String.valueOf(patient.getContact().getContactNumber()),
            String.valueOf(patient.getContact().getAddress()),
            String.valueOf(patient.getPatientBloodType()),
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    // public void updatePatient(String patientId, Patient updatedPatient) throws IOException {
    //     CSVHelper.updateCSVById(FILE_PATH, patientId, updatedPatient.toCSVRecord(), HEADER);
    // }

    public void deletePatient(String patientId) throws IOException {
        CSVHelper.deleteFromCSV(FILE_PATH, patientId, HEADER);
    }
    
}
