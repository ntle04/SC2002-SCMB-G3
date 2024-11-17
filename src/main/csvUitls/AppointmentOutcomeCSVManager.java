package main.csvUitls;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.time.format.DateTimeFormatter;

import main.controller.PrescriptionController;
import main.model.AppointmentOutcome;
import main.model.Prescription;
import main.util.ApptStatus;
import main.util.TimeSlot;

public class AppointmentOutcomeCSVManager {

    private static final String FILE_PATH = Config.APPOINTMENT_OUTCOME_FILE_PATH;
    private final String[] HEADER = {"outcomeId,appointmentId,appointmentDate,appointmentTime,serviceType,notes,patientId,doctorId"};

    PrescriptionController prescriptionController = new PrescriptionController();


    public List<AppointmentOutcome> loadAppointmentOutcomes() throws IOException {
    	
    	try {
    		List<String[]> data = CSVHelper.readCSV(FILE_PATH);       
            List<AppointmentOutcome> appointmentOutcomes = new ArrayList<>();
            int i = 0;
            for (String[] row : data) {
            	
            	if(row.length > 1)
            	{
                    String outcomeId = row[1];
            		String appointmentId = row[1];
                    String appointmentDate = row[2];
                    TimeSlot appointmentTime = TimeSlot.getByTime(row[3]);
                    String serviceType = row[4]; 
                    String notes = row[5];
                    String doctorId = row[6];
                    String patientId = row[7];

                    List<Prescription> prescriptions = prescriptionController.findPrescriptionsByApptOutcomeId(outcomeId);
                    
                    appointmentOutcomes.add(new AppointmentOutcome(outcomeId,appointmentId, appointmentDate, appointmentTime, serviceType, prescriptions, notes, doctorId, patientId));             
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
            String.valueOf(appointmentOutcome.getOutcomeId()),
            String.valueOf(appointmentOutcome.getAppointmentId()),
            String.valueOf(appointmentOutcome.getAppointmentDate()),
            String.valueOf(appointmentOutcome.getAppointmentTime()),
            String.valueOf(appointmentOutcome.getServiceType()),
            String.valueOf(appointmentOutcome.getNotes()),
            String.valueOf(appointmentOutcome.getDoctorId()),
            String.valueOf(appointmentOutcome.getPatientId()),
        });
        CSVHelper.appendCSV(FILE_PATH, data);
    }

    public void updateAppointmentOutcome(AppointmentOutcome appointmentOutcome) throws IOException {

        System.out.println("in appt outcome csv");

        String[] updatedRecord = {
            String.valueOf(appointmentOutcome.getOutcomeId()),
			 String.valueOf(appointmentOutcome.getAppointmentId()),
	         String.valueOf(appointmentOutcome.getAppointmentDate()),
	         String.valueOf(appointmentOutcome.getAppointmentTime()),
	         String.valueOf(appointmentOutcome.getServiceType()),
	         String.valueOf(appointmentOutcome.getNotes()),
	         String.valueOf(appointmentOutcome.getDoctorId()),
	         String.valueOf(appointmentOutcome.getPatientId()),
        };


        CSVHelper.updateCSVById(FILE_PATH, appointmentOutcome.getAppointmentId(), updatedRecord, HEADER);
    }
    
}
