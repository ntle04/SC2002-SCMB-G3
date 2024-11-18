package main.csvUitls;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.model.Patient;

public class PatientCSVManager {

    private static final String FILE_PATH = Config.PATIENT_LIST_FILE_PATH;
    private static final String[] HEADER = {"Id,name,age,dob,gender,contactNumber,email,address,patientBloodType"};

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

    public void deletePatient(String patientId) throws IOException {
        CSVHelper.deleteFromCSV(FILE_PATH, patientId, HEADER);
    }
    
}
