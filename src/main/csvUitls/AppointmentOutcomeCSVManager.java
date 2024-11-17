package main.csvUitls;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;

import main.model.AppointmentOutcome;
import main.model.Prescription;
import main.util.ApptStatus;
import main.util.TimeSlot;

public class AppointmentOutcomeCSVManager {

    private static final String FILE_PATH = Config.APPOINTMENT_OUTCOME_FILE_PATH;
    private final String[] HEADER = {"appointmentId,appointmentDate,appointmentTime,serviceType,notes,patientId,doctorId"};


    public List<AppointmentOutcome> loadAppointmentOutcomes() throws IOException {
    	
    	try {
    		List<String[]> data = CSVHelper.readCSV(FILE_PATH);       
            List<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
            int i = 0;
            for (String[] row : data) {
            	
            	if(row.length > 1)
            	{
            		String appointmentId = row[0];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate appointmentDate = LocalDate.parse(row[1], formatter);
                    DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HHmm");
                    LocalTime appointmentTime = LocalTime.parse(row[2], formatterTime);
                    
                    String serviceType = row[3]; 
                    List<Prescription> prescriptions = new ArrayList<>();
                    String notes = row[4];
                    String doctorId = row[5];
                    String patientId = row[6];
                    
                    appointmentOutcomes.add(new AppointmentOutcome(appointmentId, appointmentDate, appointmentTime, serviceType, prescriptions, notes, doctorId, patientId));             
            	}
                
            }
            return appointmentOutcomes;
    		
    	} catch (Exception e) {
			 System.out.println("Error load appointment outcomes: " + e.getMessage());
		}
        
    	return null;
    }

    public void addAppointmentOutcome(AppointmentOutcome appointmentOutcome) throws IOException {
        List<String[]> data = new ArrayList<>();
        data.add(new String[]{
            String.valueOf(appointmentOutcome.getAppointmentId()),
            String.valueOf(appointmentOutcome.getAppointmentDate()),
            String.valueOf(appointmentOutcome.getAppointmentTime()),
            String.valueOf(appointmentOutcome.getServiceType()),
            String.valueOf(appointmentOutcome.getPrescriptions()),
            String.valueOf(appointmentOutcome.getNotes()),
            String.valueOf(appointmentOutcome.getDoctorId()),
            String.valueOf(appointmentOutcome.getPatientId()),
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAppointmentOutcome(AppointmentOutcome appointmentOutcome) throws IOException {

        System.out.println("in appt outcome csv");

        String[] updatedRecord = {
			 String.valueOf(appointmentOutcome.getAppointmentId()),
	         String.valueOf(appointmentOutcome.getAppointmentDate()),
	         String.valueOf(appointmentOutcome.getAppointmentTime()),
	         String.valueOf(appointmentOutcome.getServiceType()),
	         String.valueOf(appointmentOutcome.getPrescriptions()),
	         String.valueOf(appointmentOutcome.getNotes()),
	         String.valueOf(appointmentOutcome.getDoctorId()),
	         String.valueOf(appointmentOutcome.getPatientId()),
        };


        CSVHelper.updateCSVById(FILE_PATH, appointmentOutcome.getAppointmentId(), updatedRecord, HEADER);
    }
    
}
